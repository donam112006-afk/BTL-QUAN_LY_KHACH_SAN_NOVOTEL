package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.NhanVien;

public class NhanVienDAO {

    // 1. Lấy tất cả danh sách Nhân Viên
    public List<NhanVien> LayTatCa() {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM Nhan_Vien";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                NhanVien nv = new NhanVien(
                    rs.getString("Ma_NV"),
                    rs.getString("Ho_Ten"),
                    rs.getString("SDT"),
                    rs.getString("Gioi_Tinh"),
                    rs.getString("Chuc_Vu")
                );
                list.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. Thêm mới Nhân Viên
    public boolean Them(NhanVien nv) {
        String sql = "INSERT INTO Nhan_Vien (Ma_NV, Ho_Ten, SDT, Gioi_Tinh, Chuc_Vu) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, nv.getMaNV());
            ps.setString(2, nv.getHoTen());
            ps.setString(3, nv.getSdt());
            ps.setString(4, nv.getGioiTinh());
            ps.setString(5, nv.getChucVu());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3. Sửa thông tin Nhân Viên
    public boolean Sua(NhanVien nv) {
        String sql = "UPDATE Nhan_Vien SET Ho_Ten = ?, SDT = ?, Gioi_Tinh = ?, Chuc_Vu = ? WHERE Ma_NV = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, nv.getHoTen());
            ps.setString(2, nv.getSdt());
            ps.setString(3, nv.getGioiTinh());
            ps.setString(4, nv.getChucVu());
            ps.setString(5, nv.getMaNV());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4. Xóa Nhân Viên theo Mã
    public boolean Xoa(String id) {
        String sql = "DELETE FROM Nhan_Vien WHERE Ma_NV = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 5. Tìm kiếm theo Mã Nhân Viên
    public List<NhanVien> TimKiemTheoMa(String ma) {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM Nhan_Vien WHERE Ma_NV LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, "%" + ma + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    NhanVien nv = new NhanVien(
                        rs.getString("Ma_NV"),
                        rs.getString("Ho_Ten"),
                        rs.getString("SDT"),
                        rs.getString("Gioi_Tinh"),
                        rs.getString("Chuc_Vu")
                    );
                    list.add(nv);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 6. Tìm kiếm theo Tên Nhân Viên
    public List<NhanVien> TimKiemTheoTen(String ten) {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM Nhan_Vien WHERE Ho_Ten LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, "%" + ten + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    NhanVien nv = new NhanVien(
                        rs.getString("Ma_NV"),
                        rs.getString("Ho_Ten"),
                        rs.getString("SDT"),
                        rs.getString("Gioi_Tinh"),
                        rs.getString("Chuc_Vu")
                    );
                    list.add(nv);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}