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
                    rs.getString("Ma_Dat"), 
                    rs.getString("Ma_Phong"), 
                    rs.getString("Ma_KH"),
                    rs.getString("Ma_NV"),
                    rs.getDate("Ngay_Vao"), 
                    rs.getDate("Ngay_Ra"),
                    rs.getDouble("Tien_Coc"),
                    rs.getString("Trang_Thai")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return ds;
    }

    public boolean Them(DatPhong dp) { 
        String sql = "INSERT INTO dat_phong(Ma_Dat, Ma_Phong, Ma_KH, Ma_NV, Ngay_Vao, Ngay_Ra, Tien_Coc, Trang_Thai) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dp.getMaDat());
            ps.setString(2, dp.getMaPhong());
            ps.setString(3, dp.getMaKH());
            ps.setString(4, dp.getMaNV());
            ps.setDate(5, dp.getNgayCheckIn());
            ps.setDate(6, dp.getNgayCheckOut());
            ps.setDouble(7, dp.getTienCoc());
            ps.setString(8, dp.getTrangThai());
            
            if (ps.executeUpdate() > 0) {
                CapNhatTrangThaiPhong(dp.getMaPhong(), "Đang sử dụng");
                return true;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean Sua(DatPhong dp) { 
        String sql = "UPDATE dat_phong SET Ma_Phong=?, Ma_KH=?, Ma_NV=?, Ngay_Vao=?, Ngay_Ra=?, Tien_Coc=?, Trang_Thai=? WHERE Ma_Dat=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dp.getMaPhong());
            ps.setString(2, dp.getMaKH());
            ps.setString(3, dp.getMaNV());
            ps.setDate(4, dp.getNgayCheckIn());
            ps.setDate(5, dp.getNgayCheckOut());
            ps.setDouble(6, dp.getTienCoc());
            ps.setString(7, dp.getTrangThai());
            ps.setString(8, dp.getMaDat());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public boolean Xoa(String id) { 
        String sqlSelect = "SELECT Ma_Phong FROM dat_phong WHERE Ma_Dat=?";
        String maPhong = "";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement psSelect = conn.prepareStatement(sqlSelect)) {
             psSelect.setString(1, id);
             ResultSet rs = psSelect.executeQuery();
             if (rs.next()) maPhong = rs.getString("Ma_Phong");
        } catch (Exception e) { e.printStackTrace(); }

        String sqlDelete = "DELETE FROM dat_phong WHERE Ma_Dat=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlDelete)) {
            ps.setString(1, id);
            if (ps.executeUpdate() > 0) {
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
        String sql = "SELECT * FROM dat_phong WHERE Ma_Dat LIKE ? OR Ma_KH LIKE ? OR Ma_Phong LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ds.add(new DatPhong(
                        rs.getString("Ma_Dat"), 
                        rs.getString("Ma_Phong"), 
                        rs.getString("Ma_KH"), 
                        rs.getString("Ma_NV"), 
                        rs.getDate("Ngay_Vao"), 
                        rs.getDate("Ngay_Ra"),
                        rs.getDouble("Tien_Coc"),
                        rs.getString("Trang_Thai")
                    ));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return ds;
    }

    private void CapNhatTrangThaiPhong(String maPhong, String trangThai) {
        String sql = "UPDATE phong SET Trang_Thai=? WHERE Ma_Phong=?"; // Chỉnh lại theo tên cột của bảng Phong
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, trangThai);
            ps.setString(2, maPhong);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Lỗi cập nhật trạng thái phòng");
        }
    }
}