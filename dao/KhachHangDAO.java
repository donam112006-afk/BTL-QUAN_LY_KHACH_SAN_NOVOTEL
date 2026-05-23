package dao;

import model.KhachHang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {

    public List<KhachHang> LayTatCa() {
        List<KhachHang> ds = new ArrayList<>();
        String sql = "SELECT * FROM Khach_Hang";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ds.add(new KhachHang(
                    rs.getString("Ma_KH"),
                    rs.getString("Ho_Ten"),
                    rs.getString("SDT"),
                    rs.getString("Dia_Chi"),
                    rs.getString("So_CCCD")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return ds;
    }

    public boolean Them(KhachHang kh) {
        String sql = "INSERT INTO Khach_Hang(Ma_KH, Ho_Ten, SDT, Dia_Chi, So_CCCD) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kh.getMaKH());
            ps.setString(2, kh.getHoTen());
            ps.setString(3, kh.getSdt());
            ps.setString(4, kh.getDiaChi());
            ps.setString(5, kh.getSoCCCD());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public boolean Sua(KhachHang kh) {
        String sql = "UPDATE Khach_Hang SET Ho_Ten=?, SDT=?, Dia_Chi=?, So_CCCD=? WHERE Ma_KH=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kh.getHoTen());
            ps.setString(2, kh.getSdt());
            ps.setString(3, kh.getDiaChi());
            ps.setString(4, kh.getSoCCCD());
            ps.setString(5, kh.getMaKH());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public boolean Xoa(String id) {
        String sql = "DELETE FROM Khach_Hang WHERE Ma_KH=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public List<KhachHang> TimKiemTheoMa(String MaKH) {
        List<KhachHang> ds = new ArrayList<>();
        String sql = "SELECT * FROM Khach_Hang WHERE Ma_KH LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + MaKH + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ds.add(new KhachHang(
                        rs.getString("Ma_KH"), rs.getString("Ho_Ten"),
                        rs.getString("SDT"), rs.getString("Dia_Chi"), rs.getString("So_CCCD")
                    ));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return ds;
    }

    public List<KhachHang> TimKiemTheoTen(String HoTen) {
        List<KhachHang> ds = new ArrayList<>();
        String sql = "SELECT * FROM Khach_Hang WHERE Ho_Ten LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + HoTen + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ds.add(new KhachHang(
                        rs.getString("Ma_KH"), rs.getString("Ho_Ten"),
                        rs.getString("SDT"), rs.getString("Dia_Chi"), rs.getString("So_CCCD")
                    ));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return ds;
    }
}