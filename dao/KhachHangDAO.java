package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.KhachHang;

public class KhachHangDAO {

    // 1. Lấy tất cả danh sách Khách Hàng
    public List<KhachHang> LayTatCa() {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM Khach_Hang";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                KhachHang kh = new KhachHang(
                    rs.getString("Ma_KH"),
                    rs.getString("Ho_Ten"),
                    rs.getString("SDT"),
                    rs.getString("CCCD"),
                    rs.getString("Dia_Chi")
                );
                list.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. Thêm mới Khách Hàng
    public boolean Them(KhachHang kh) {
        String sql = "INSERT INTO Khach_Hang (Ma_KH, Ho_Ten, SDT, CCCD, Dia_Chi) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, kh.getMaKH());
            ps.setString(2, kh.getHoTen());
            ps.setString(3, kh.getSdt());
            ps.setString(4, kh.getCccd());
            ps.setString(5, kh.getDiaChi());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3. Sửa thông tin Khách Hàng
    public boolean Sua(KhachHang kh) {
        String sql = "UPDATE Khach_Hang SET Ho_Ten = ?, SDT = ?, CCCD = ?, Dia_Chi = ? WHERE Ma_KH = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, kh.getHoTen());
            ps.setString(2, kh.getSdt());
            ps.setString(3, kh.getCccd());
            ps.setString(4, kh.getDiaChi());
            ps.setString(5, kh.getMaKH());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4. Xóa Khách Hàng theo Mã
    public boolean Xoa(String id) {
        String sql = "DELETE FROM Khach_Hang WHERE Ma_KH = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 5. Tìm kiếm theo Mã Khách Hàng
    public List<KhachHang> TimKiemTheoMa(String ma) {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM Khach_Hang WHERE Ma_KH LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, "%" + ma + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    KhachHang kh = new KhachHang(
                        rs.getString("Ma_KH"),
                        rs.getString("Ho_Ten"),
                        rs.getString("SDT"),
                        rs.getString("CCCD"),
                        rs.getString("Dia_Chi")
                    );
                    list.add(kh);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 6. Tìm kiếm theo Tên Khách Hàng
    public List<KhachHang> TimKiemTheoTen(String ten) {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM Khach_Hang WHERE Ho_Ten LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, "%" + ten + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    KhachHang kh = new KhachHang(
                        rs.getString("Ma_KH"),
                        rs.getString("Ho_Ten"),
                        rs.getString("SDT"),
                        rs.getString("CCCD"),
                        rs.getString("Dia_Chi")
                    );
                    list.add(kh);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}