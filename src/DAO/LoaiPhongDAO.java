package DAO;

import model.LoaiPhong;
import java.sql.*;

public class LoaiPhongDAO {
    // Thêm mới
    public boolean Them(LoaiPhong lp) { 
        String sql = "INSERT INTO LoaiPhong (MaLoai,TenLoai, giaTheoNgay, SoNguoiToiDa) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, lp.getMaLoai());
            ps.setString(2, lp.getTenLoai());
            ps.setDouble(3, lp.getGiaTheoNgay());
            ps.setInt(4, lp.getSoNguoiToiDa());
            int ketQua = ps.executeUpdate();
            return ketQua > 0;
        } catch (Exception e) { return false; }
    }

    // Sửa: Xác định bản ghi qua ID thủ công
    public boolean Sua(LoaiPhong lp) {
        String sql = "UPDATE LoaiPhong SET TenLoai=?, giaTheoNgay=?, SoNguoiToiDa=? WHERE MaLoai=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, lp.getTenLoai());
            ps.setDouble(2, lp.getGiaTheoNgay());
            ps.setInt(3, lp.getSoNguoiToiDa());
            ps.setInt(4, lp.getMaLoai());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { return false; }
    }

    // Xóa
    public boolean Xoa(int maLoai) {
        String sql = "DELETE FROM LoaiPhong WHERE MaLoai=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maLoai);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { return false; }
    }

    // Tìm kiếm theo Mã
    public LoaiPhong timKiemTheoMa(int ma) {
        String sql = "SELECT * FROM LoaiPhong WHERE MaLoai = " + ma;
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                LoaiPhong lp = new LoaiPhong();
                lp.setMaLoai(rs.getInt("MaLoai"));
                lp.setTenLoai(rs.getString("TenLoai"));
                lp.setGiaTheoNgay(rs.getDouble("GiaTheoNgay"));
                lp.setSoNguoiToiDa(rs.getInt("SoNguoiToiDa"));
                return lp;
            }
        } catch (Exception e) { }
        return null;
    }

    // Tìm kiếm theo Tên
    public int timKiemTheoTen(String ten, LoaiPhong[] mangKetQua) {
        String sql = "SELECT * FROM LoaiPhong WHERE TenLoai LIKE '%" + ten + "%'";
        int chiSo = 0;
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next() && chiSo < mangKetQua.length) {
                LoaiPhong lp = new LoaiPhong();
                lp.setMaLoai(rs.getInt(1));
                lp.setTenLoai(rs.getString(2));
                lp.setGiaTheoNgay(rs.getDouble(3));
                lp.setSoNguoiToiDa(rs.getInt(4));
                mangKetQua[chiSo] = lp;
                chiSo++;
            }
        } catch (Exception e) { }
        return chiSo; 
    }
}