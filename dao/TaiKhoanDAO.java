package dao;

import model.TaiKhoan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO {

    public List<TaiKhoan> LayTatCa() {
        List<TaiKhoan> ds = new ArrayList<>();
        String sql = "SELECT * FROM Tai_Khoan";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ds.add(new TaiKhoan(
                    rs.getString("Ma_TK"),
                    rs.getString("Ten_Dang_Nhap"),
                    rs.getString("Mat_Khau"),
                    rs.getString("Vai_Tro"),
                    rs.getString("Ma_NV")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return ds;
    }

    public boolean Them(TaiKhoan tk) {
        String sql = "INSERT INTO Tai_Khoan(Ma_TK, Ten_Dang_Nhap, Mat_Khau, Vai_Tro, Ma_NV) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tk.getMaTK());
            ps.setString(2, tk.getTenDangNhap());
            ps.setString(3, tk.getMatKhau());
            ps.setString(4, tk.getVaiTro());
            ps.setString(5, tk.getMaNV());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public boolean Sua(TaiKhoan tk) {
        String sql = "UPDATE Tai_Khoan SET Ten_Dang_Nhap=?, Mat_Khau=?, Vai_Tro=?, Ma_NV=? WHERE Ma_TK=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tk.getTenDangNhap());
            ps.setString(2, tk.getMatKhau());
            ps.setString(3, tk.getVaiTro());
            ps.setString(4, tk.getMaNV());
            ps.setString(5, tk.getMaTK());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public boolean Xoa(String id) {
        String sql = "DELETE FROM Tai_Khoan WHERE Ma_TK=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public List<TaiKhoan> TimKiemTheoMa(String MaTK) {
        List<TaiKhoan> ds = new ArrayList<>();
        String sql = "SELECT * FROM Tai_Khoan WHERE Ma_TK LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + MaTK + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ds.add(new TaiKhoan(
                        rs.getString("Ma_TK"), rs.getString("Ten_Dang_Nhap"),
                        rs.getString("Mat_Khau"), rs.getString("Vai_Tro"), rs.getString("Ma_NV")
                    ));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return ds;
    }

    public List<TaiKhoan> TimKiemTheoTen(String TenDangNhap) {
        List<TaiKhoan> ds = new ArrayList<>();
        String sql = "SELECT * FROM Tai_Khoan WHERE Ten_Dang_Nhap LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + TenDangNhap + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ds.add(new TaiKhoan(
                        rs.getString("Ma_TK"), rs.getString("Ten_Dang_Nhap"),
                        rs.getString("Mat_Khau"), rs.getString("Vai_Tro"), rs.getString("Ma_NV")
                    ));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return ds;
    }
}