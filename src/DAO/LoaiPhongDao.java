package DAO;

import Model.LoaiPhong;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Logger;

public class LoaiPhongDao {
    private static final Logger LOGGER = Logger.getLogger(LoaiPhongDao.class.getName());


    public boolean Them(LoaiPhong lp) { 
        String sql = "INSERT INTO LoaiPhong (MaLoai, TenLoai, GiaTheoNgay, SoNguoiToiDa) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, lp.getMaLoai());
            ps.setString(2, lp.getTenLoai());
            ps.setDouble(3, lp.getGiaTheoNgay());
            ps.setInt(4, lp.getSoNguoiToiDa());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { 
            LOGGER.severe("Error adding LoaiPhong: " + e.getMessage()); 
            return false; 
        } catch (Exception e) { 
            LOGGER.severe("Unexpected error adding LoaiPhong: " + e.getMessage()); 
            return false; 
        }
    }


    public boolean Sua(LoaiPhong lp) {
        String sql = "UPDATE LoaiPhong SET TenLoai=?, GiaTheoNgay=?, SoNguoiToiDa=? WHERE MaLoai=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, lp.getTenLoai());
            ps.setDouble(2, lp.getGiaTheoNgay());
            ps.setInt(3, lp.getSoNguoiToiDa());
            ps.setInt(4, lp.getMaLoai());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { 
            LOGGER.severe("Error updating LoaiPhong: " + e.getMessage()); 
            return false; 
        } catch (Exception e) { 
            LOGGER.severe("Unexpected error updating LoaiPhong: " + e.getMessage()); 
            return false; 
        }
    }


    public boolean Xoa(int maLoai) {
        String sql = "DELETE FROM LoaiPhong WHERE MaLoai=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maLoai);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { 
            LOGGER.severe("Error deleting LoaiPhong: " + e.getMessage()); 
            return false; 
        } catch (Exception e) { 
            LOGGER.severe("Unexpected error deleting LoaiPhong: " + e.getMessage()); 
            return false; 
        }
    }


    public LoaiPhong timKiemTheoMa(int ma) {
        String sql = "SELECT MaLoai, TenLoai, GiaTheoNgay, SoNguoiToiDa FROM LoaiPhong WHERE MaLoai = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ma);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new LoaiPhong(
                        rs.getInt("MaLoai"),
                        rs.getString("TenLoai"),
                        rs.getDouble("GiaTheoNgay"),
                        rs.getInt("SoNguoiToiDa")
                    );
                }
            }
        } catch (SQLException e) { 
            LOGGER.severe("Error finding LoaiPhong by MaLoai: " + e.getMessage()); 
        } catch (Exception e) { 
            LOGGER.severe("Unexpected error finding LoaiPhong by MaLoai: " + e.getMessage()); 
        }
        return null;
    }

    
    public List<LoaiPhong> timKiemTheoTen(String ten) {
        List<LoaiPhong> ketQua = new ArrayList<>();
        String sql = "SELECT MaLoai, TenLoai, GiaTheoNgay, SoNguoiToiDa FROM LoaiPhong WHERE TenLoai LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + ten + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ketQua.add(new LoaiPhong(
                        rs.getInt("MaLoai"),
                        rs.getString("TenLoai"),
                        rs.getDouble("GiaTheoNgay"),
                        rs.getInt("SoNguoiToiDa")
                    ));
                }
            }
        } catch (SQLException e) { 
            LOGGER.severe("Error finding LoaiPhong by TenLoai: " + e.getMessage()); 
        } catch (Exception e) { 
            LOGGER.severe("Unexpected error finding LoaiPhong by TenLoai: " + e.getMessage()); 
        }
        return ketQua; 
    }
}