package dao;

import model.DatPhong;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatPhongDAO {

    public List<DatPhong> LayTatCa() { 
        List<DatPhong> ds = new ArrayList<>();
        String sql = "SELECT * FROM dat_phong";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ds.add(new DatPhong(
                    rs.getInt("Ma_Dat"), 
                    rs.getString("Ma_Phong"), // Lấy thêm Mã phòng
                    rs.getString("Ma_KH"),
                    rs.getString("Ma_NV"),
                    rs.getDate("Ngay_Vao"), 
                    rs.getDate("Ngay_Ra")   
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return ds;
    }

    public boolean Them(DatPhong dp) { 
        // Khi thêm đặt phòng, cần insert cả Ma_Phong
        String sql = "INSERT INTO dat_phong(Ma_Dat, Ma_Phong, Ma_KH, Ma_NV, Ngay_Vao, Ngay_Ra) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, dp.getMaDat());
            ps.setString(2, dp.getMaPhong());
            ps.setString(3, dp.getMaKH());
            ps.setString(4, dp.getMaNV());
            ps.setDate(5, dp.getNgayCheckIn());
            ps.setDate(6, dp.getNgayCheckOut());
            
            if (ps.executeUpdate() > 0) {
                // Đặt phòng thành công thì đổi trạng thái phòng bên bảng Phong thành 'Đang sử dụng'
                CapNhatTrangThaiPhong(dp.getMaPhong(), "Đang sử dụng");
                return true;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean Sua(DatPhong dp) { 
        String sql = "UPDATE dat_phong SET Ma_Phong=?, Ma_KH=?, Ma_NV=?, Ngay_Vao=?, Ngay_Ra=? WHERE Ma_Dat=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dp.getMaPhong());
            ps.setString(2, dp.getMaKH());
            ps.setString(3, dp.getMaNV());
            ps.setDate(4, dp.getNgayCheckIn());
            ps.setDate(5, dp.getNgayCheckOut());
            ps.setInt(6, dp.getMaDat());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public boolean Xoa(int id) { 
        // Trước khi xóa lịch sử đặt phòng, lấy mã phòng để set lại trạng thái trống
        String sqlSelect = "SELECT Ma_Phong FROM dat_phong WHERE Ma_Dat=?";
        String maPhong = "";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement psSelect = conn.prepareStatement(sqlSelect)) {
             psSelect.setInt(1, id);
             ResultSet rs = psSelect.executeQuery();
             if (rs.next()) maPhong = rs.getString("Ma_Phong");
        } catch (Exception e) { e.printStackTrace(); }

        String sqlDelete = "DELETE FROM dat_phong WHERE Ma_Dat=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlDelete)) {
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                // Xóa thành công, trả lại trạng thái trống cho phòng
                if (!maPhong.isEmpty()) {
                    CapNhatTrangThaiPhong(maPhong, "Trống");
                }
                return true;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public List<DatPhong> TimKiem(String keyword) { 
        List<DatPhong> ds = new ArrayList<>();
        // Tìm theo cả mã đặt, mã khách, hoặc MÃ PHÒNG
        String sql = "SELECT * FROM dat_phong WHERE Ma_Dat LIKE ? OR Ma_KH LIKE ? OR Ma_Phong LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ds.add(new DatPhong(rs.getInt("Ma_Dat"), rs.getString("Ma_Phong"), rs.getString("Ma_KH"), rs.getString("Ma_NV"), rs.getDate("Ngay_Vao"), rs.getDate("Ngay_Ra")));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return ds;
    }

    // --- HÀM HỖ TRỢ ĐỂ LÀM GIAO DIỆN XANH/ĐỎ NHƯ TRONG VIDEO ---
    private void CapNhatTrangThaiPhong(String maPhong, String trangThai) {
        // Giả sử bạn có bảng 'phong' với cột 'TrangThai'
        String sql = "UPDATE phong SET TrangThai=? WHERE Ma_Phong=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, trangThai);
            ps.setString(2, maPhong);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lưu ý: Bạn cần tạo bảng 'phong' để cập nhật trạng thái");
        }
    }
}