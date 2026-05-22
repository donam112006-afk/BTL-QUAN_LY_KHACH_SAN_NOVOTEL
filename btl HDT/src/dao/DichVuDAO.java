package dao; // Chữ thường theo đúng cấu trúc thư mục[cite: 2]

import model.DichVu;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DichVuDAO {
    public List<DichVu> LayTatCa() { // Hàm chuẩn theo Doc[cite: 2]
        List<DichVu> ds = new ArrayList<>();
        String sql = "SELECT * FROM dich_vu";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ds.add(new DichVu(
                    rs.getString("Ma_DV"),
                    rs.getString("Tean_DV"), // Gọi đúng tên cột DB
                    rs.getInt("Gia_DV"),     // Ép về int theo doc
                    rs.getString("Don_Vi_Tinh") 
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return ds;
    }

    public boolean Them(DichVu dv) { // Hàm chuẩn theo Doc[cite: 2]
        String sql = "INSERT INTO dich_vu(Ma_DV, Tean_DV, Gia_DV, Don_Vi_Tinh) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dv.getMaDV());
            ps.setString(2, dv.getTenDV());
            ps.setInt(3, dv.getGiaDV());
            ps.setString(4, dv.getDonViTinh());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public boolean Sua(DichVu dv) { // Dùng đúng chữ "Sua"[cite: 2]
        String sql = "UPDATE dich_vu SET Tean_DV=?, Gia_DV=?, Don_Vi_Tinh=? WHERE Ma_DV=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dv.getTenDV());
            ps.setInt(2, dv.getGiaDV());
            ps.setString(3, dv.getDonViTinh());
            ps.setString(4, dv.getMaDV());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    // Vì Ma_DV là chuỗi nên mình để String cho hợp logic, nhưng vẫn giữ nguyên tên hàm Xoa
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
        // Dùng OR để tìm theo cả Mã và Tên
        String sql = "SELECT * FROM dich_vu WHERE Ma_DV LIKE ? OR Tean_DV LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%"); // Gán từ khóa cho cả 2 dấu ?
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ds.add(new DichVu(rs.getString("Ma_DV"), rs.getString("Tean_DV"), rs.getInt("Gia_DV"), rs.getString("Don_Vi_Tinh")));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return ds;
    }
}