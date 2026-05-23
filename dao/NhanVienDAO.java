package dao;

import model.NhanVien;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    
    public List<NhanVien> LayTatCa() {
        List<NhanVien> ds = new ArrayList<>();
        String sql = "SELECT * FROM Nhan_Vien";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ds.add(new NhanVien(
                    rs.getString("Ma_NV"),
                    rs.getString("Ho_Ten"),
                    rs.getString("SDT"),
                    rs.getString("Gioi_Tinh"),
                    rs.getString("Chuc_Vu"),
                    rs.getDate("Ngay_Sinh")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return ds;
    }

    public boolean Them(NhanVien nv) {
        String sql = "INSERT INTO Nhan_Vien(Ma_NV, Ho_Ten, SDT, Gioi_Tinh, Chuc_Vu, Ngay_Sinh) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nv.getMaNV());
            ps.setString(2, nv.getHoTen());
            ps.setString(3, nv.getSdt());
            ps.setString(4, nv.getGioiTinh());
            ps.setString(5, nv.getChucVu());
            ps.setDate(6, nv.getNgaySinh() != null ? new java.sql.Date(nv.getNgaySinh().getTime()) : null);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public boolean Sua(NhanVien nv) {
        String sql = "UPDATE Nhan_Vien SET Ho_Ten=?, SDT=?, Gioi_Tinh=?, Chuc_Vu=?, Ngay_Sinh=? WHERE Ma_NV=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nv.getHoTen());
            ps.setString(2, nv.getSdt());
            ps.setString(3, nv.getGioiTinh());
            ps.setString(4, nv.getChucVu());
            ps.setDate(5, nv.getNgaySinh() != null ? new java.sql.Date(nv.getNgaySinh().getTime()) : null);
            ps.setString(6, nv.getMaNV());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public boolean Xoa(String id) {
        String sql = "DELETE FROM Nhan_Vien WHERE Ma_NV=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public List<NhanVien> TimKiemTheoMa(String MaNV) {
        List<NhanVien> ds = new ArrayList<>();
        String sql = "SELECT * FROM Nhan_Vien WHERE Ma_NV LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + MaNV + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ds.add(new NhanVien(
                        rs.getString("Ma_NV"), rs.getString("Ho_Ten"), rs.getString("SDT"),
                        rs.getString("Gioi_Tinh"), rs.getString("Chuc_Vu"), rs.getDate("Ngay_Sinh")
                    ));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return ds;
    }

    public List<NhanVien> TimKiemTheoTen(String HoTen) {
        List<NhanVien> ds = new ArrayList<>();
        String sql = "SELECT * FROM Nhan_Vien WHERE Ho_Ten LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + HoTen + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ds.add(new NhanVien(
                        rs.getString("Ma_NV"), rs.getString("Ho_Ten"), rs.getString("SDT"),
                        rs.getString("Gioi_Tinh"), rs.getString("Chuc_Vu"), rs.getDate("Ngay_Sinh")
                    ));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return ds;
    }
}