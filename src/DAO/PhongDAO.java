package DAO;

import model.Phong;
import java.sql.*;

public class PhongDAO {
    public boolean Them(Phong p) {
        String sql = "INSERT INTO Phong (MaPhong, MaLoai, TrangThai) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getMaPhong());
            ps.setInt(2, p.getMaLoai());
            ps.setString(3, p.getTrangThai());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { return false; }
    }

    public boolean Sua(Phong p) {
        String sql = "UPDATE Phong SET MaPhong=?, MaLoai=?, TrangThai=? WHERE MaPhong=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getMaPhong());
            ps.setInt(2, p.getMaLoai());
            ps.setString(3, p.getTrangThai());
            ps.setInt(4, p.getMaPhong());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { return false; }
    }

    public boolean Xoa(int ma) {
        String sql = "DELETE FROM Phong WHERE MaPhong = " + ma;
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement()) {
            return st.executeUpdate(sql) > 0;
        } catch (Exception e) { return false; }
    }

    // Tìm kiếm theo Mã Phòng
    public Phong timKiemTheoMa(int ma) {
        String sql = "SELECT * FROM Phong WHERE MaPhong = " + ma;
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return new Phong(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
            }
        } catch (Exception e) { }
        return null;
    }

    // Tìm kiếm theo Số Phòng 
    public int timKiemTheoSoPhong(String soPhong, Phong[] ketQua) {
        String sql = "SELECT * FROM Phong WHERE SoPhong LIKE '%" + soPhong + "%'";
        int dem = 0;
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next() && dem < ketQua.length) {
                ketQua[dem] = new Phong(
                    rs.getInt("MaPhong"),
                    rs.getString("SoPhong"),
                    rs.getInt("MaLoai"),
                    rs.getString("TrangThai")
                );
                dem++;
            }
        } catch (Exception e) { }
        return dem; 
    }
}