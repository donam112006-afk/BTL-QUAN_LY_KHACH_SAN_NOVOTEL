package dao; 

import model.DichVu;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DichVuDAO {
    public List<DichVu> LayTatCa() { 
        List<DichVu> ds = new ArrayList<>();
        String sql = "SELECT * FROM dich_vu";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ds.add(new DichVu(
                    rs.getString("Ma_DV"),
                    rs.getString("Ten_DV"), // Sửa Tean_DV thành Ten_DV
                    rs.getDouble("Gia_DV"), // Đổi thành getDouble
                    rs.getString("Don_Vi_Tinh") 
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return ds;
    }

    public boolean Them(DichVu dv) { 
        String sql = "INSERT INTO dich_vu(Ma_DV, Ten_DV, Gia_DV, Don_Vi_Tinh) VALUES (?, ?, ?, ?)"; // Sửa Tean_DV thành Ten_DV
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dv.getMaDV());
            ps.setString(2, dv.getTenDV());
            ps.setDouble(3, dv.getGiaDV()); // Đổi thành setDouble
            ps.setString(4, dv.getDonViTinh());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public boolean Sua(DichVu dv) { 
        String sql = "UPDATE dich_vu SET Ten_DV=?, Gia_DV=?, Don_Vi_Tinh=? WHERE Ma_DV=?"; // Sửa Tean_DV thành Ten_DV
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dv.getTenDV());
            ps.setDouble(2, dv.getGiaDV()); // Đổi thành setDouble
            ps.setString(3, dv.getDonViTinh());
            ps.setString(4, dv.getMaDV());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public boolean Xoa(String id) { 
        String sql = "DELETE FROM dich_vu WHERE Ma_DV=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public List<DichVu> TimKiem(String keyword) { 
        List<DichVu> ds = new ArrayList<>();
        String sql = "SELECT * FROM dich_vu WHERE Ma_DV LIKE ? OR Ten_DV LIKE ?"; // Sửa Tean_DV thành Ten_DV
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%"); 
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ds.add(new DichVu(
                        rs.getString("Ma_DV"), 
                        rs.getString("Ten_DV"), // Sửa Tean_DV thành Ten_DV
                        rs.getDouble("Gia_DV"), // Đổi thành getDouble
                        rs.getString("Don_Vi_Tinh")
                    ));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return ds;
    }
}