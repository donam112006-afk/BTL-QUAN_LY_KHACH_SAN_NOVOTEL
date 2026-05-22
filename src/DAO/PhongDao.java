package DAO;

import Model.Phong;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Logger;

public class PhongDao {
    private static final Logger LOGGER = Logger.getLogger(PhongDao.class.getName());

    public boolean Them(Phong p) {
        String sql = "INSERT INTO Phong (SoPhong, MaLoai, TrangThai) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, p.getSoPhong());
                ps.setInt(2, p.getMaLoai());
                ps.setString(3, p.getTrangThai());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { 
            LOGGER.severe("Error adding Phong: " + e.getMessage()); 
            return false; 
        } catch (Exception e) { 
            LOGGER.severe("Unexpected error adding Phong: " + e.getMessage()); 
            return false; 
        }
    }

    public boolean Sua(Phong p) {
        String sql = "UPDATE Phong SET SoPhong=?, MaLoai=?, TrangThai=? WHERE MaPhong=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getSoPhong());
            ps.setInt(2, p.getMaLoai());
            ps.setString(3, p.getTrangThai());
            ps.setInt(4, p.getMaPhong());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { 
            LOGGER.severe("Error updating Phong: " + e.getMessage()); 
            return false; 
        } catch (Exception e) { 
            LOGGER.severe("Unexpected error updating Phong: " + e.getMessage()); 
            return false; 
        }
    }

    public boolean Xoa(int ma) {
        String sql = "DELETE FROM Phong WHERE MaPhong = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ma);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { 
            LOGGER.severe("Error deleting Phong: " + e.getMessage()); 
            return false; 
        } catch (Exception e) { 
            LOGGER.severe("Unexpected error deleting Phong: " + e.getMessage()); 
            return false; 
        }
    }

    // Tìm kiếm theo Mã Phòng
    public Phong timKiemTheoMa(int ma) {
        String sql = "SELECT MaPhong, SoPhong, MaLoai, TrangThai FROM Phong WHERE MaPhong = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ma);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Phong(
                        rs.getInt("MaPhong"),
                        rs.getString("SoPhong"),
                        rs.getInt("MaLoai"),
                        rs.getString("TrangThai")
                    );
                }
            }
        } catch (SQLException e) { 
            LOGGER.severe("Error finding Phong by MaPhong: " + e.getMessage()); 
        } catch (Exception e) { 
            LOGGER.severe("Unexpected error finding Phong by MaPhong: " + e.getMessage()); 
        }
        return null;
    }

    // Tìm kiếm theo Số Phòng 
    public List<Phong> timKiemTheoSoPhong(String soPhong) {
        List<Phong> ketQua = new ArrayList<>();
        String sql = "SELECT MaPhong, SoPhong, MaLoai, TrangThai FROM Phong WHERE SoPhong LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + soPhong + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ketQua.add(new Phong(
                        rs.getInt("MaPhong"),
                        rs.getString("SoPhong"),
                        rs.getInt("MaLoai"),
                        rs.getString("TrangThai")
                    ));
                }
            }
        } catch (SQLException e) { 
            LOGGER.severe("Error finding Phong by SoPhong: " + e.getMessage()); 
        } catch (Exception e) { 
            LOGGER.severe("Unexpected error finding Phong by SoPhong: " + e.getMessage()); 
        }
        return ketQua; 
    }
}