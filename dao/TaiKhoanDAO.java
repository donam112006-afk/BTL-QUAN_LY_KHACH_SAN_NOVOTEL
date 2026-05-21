package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.TaiKhoan;

public class TaiKhoanDAO {

    // 1. Lấy tất cả danh sách Tài Khoản
    public List<TaiKhoan> LayTatCa() {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM Tai_Khoan";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(
                    rs.getString("Ma_TK"),
                    rs.getString("Ten_Dang_Nhap"),
                    rs.getString("Mat_Khau"),
                    rs.getString("Vai_Tro"),
                    rs.getString("Ma_NV")
                );
                list.add(tk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. Thêm mới Tài Khoản
    public boolean Them(TaiKhoan tk) {
        String sql = "INSERT INTO Tai_Khoan (Ma_TK, Ten_Dang_Nhap, Mat_Khau, Vai_Tro, Ma_NV) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, tk.getMaTK());
            ps.setString(2, tk.getTenDangNhap());
            ps.setString(3, tk.getMatKhau());
            ps.setString(4, tk.getVaiTro());
            ps.setString(5, tk.getMaNV());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3. Sửa thông tin Tài Khoản
    public boolean Sua(TaiKhoan tk) {
        String sql = "UPDATE Tai_Khoan SET Ten_Dang_Nhap = ?, Mat_Khau = ?, Vai_Tro = ?, Ma_NV = ? WHERE Ma_TK = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, tk.getTenDangNhap());
            ps.setString(2, tk.getMatKhau());
            ps.setString(3, tk.getVaiTro());
            ps.setString(4, tk.getMaNV());
            ps.setString(5, tk.getMaTK());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4. Xóa Tài Khoản theo Mã
    public boolean Xoa(String id) {
        String sql = "DELETE FROM Tai_Khoan WHERE Ma_TK = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 5. Tìm kiếm theo Mã Tài Khoản
    public List<TaiKhoan> TimKiemTheoMa(String ma) {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM Tai_Khoan WHERE Ma_TK LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, "%" + ma + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TaiKhoan tk = new TaiKhoan(
                        rs.getString("Ma_TK"),
                        rs.getString("Ten_Dang_Nhap"),
                        rs.getString("Mat_Khau"),
                        rs.getString("Vai_Tro"),
                        rs.getString("Ma_NV")
                    );
                    list.add(tk);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 6. Tìm kiếm theo Tên Đăng Nhập
    public List<TaiKhoan> TimKiemTheoTen(String ten) {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM Tai_Khoan WHERE Ten_Dang_Nhap LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, "%" + ten + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TaiKhoan tk = new TaiKhoan(
                        rs.getString("Ma_TK"),
                        rs.getString("Ten_Dang_Nhap"),
                        rs.getString("Mat_Khau"),
                        rs.getString("Vai_Tro"),
                        rs.getString("Ma_NV")
                    );
                    list.add(tk);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}