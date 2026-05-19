package DAO;

import java.sql.*;
import java.util.ArrayList; 
import java.util.List;
import java.time.LocalDate;

import model.NhanVien;

public class NhanVienDAO {
    //Phương thức tạo bảng nhân viên trong mysql
    public void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS Nhan_Vien (
                Ma_NV       VARCHAR(10) PRIMARY KEY,
                Ho_Ten      VARCHAR(100) NOT NULL,
                SDT         VARCHAR(15),
                Gioi_Tinh   VARCHAR(10),
                Chuc_Vu     VARCHAR(50),
                Ngay_Sinh   DATE
            )
        """;
        try(Statement stmt = DBConnection.getConnection().createStatement()) {
            stmt.execute(sql);
            System.out.println("Tạo bảng nhân viên thành công!");
        }
        catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tạo bảng nhân viên " + e.getMessage(), e);
        }
    }

    //Phương thức thêm nhân viên vào bảng nhan_vien trong mysql
    public void themNhanVien(NhanVien nv) {
        if (nv == null || nv.getMaNV() == null || nv.getTenNV() == null) {
            throw new IllegalArgumentException("Nhân viên hoặc các trường bắt buộc không được để trống");
        }
        String sql = "INSERT INTO Nhan_Vien (Ma_NV, Ho_Ten, SDT, Gioi_Tinh, Chuc_Vu, Ngay_Sinh) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nv.getMaNV());
            stmt.setString(2, nv.getTenNV());
            stmt.setString(3, nv.getSoDienThoai());
            stmt.setString(4, nv.getGioiTinh());
            stmt.setString(5, nv.getChucVu());
            if (nv.getNgaySinh() != null) {
                stmt.setDate(6, Date.valueOf(nv.getNgaySinh()));
            } else {
                stmt.setNull(6, Types.DATE);
            }
            stmt.executeUpdate();

            System.out.println("Thêm nhân viên thành công!");
        } 
        catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm nhân viên: " + e.getMessage(), e);
        }
    }

    //Phương thức xóa nhân viên bằng mã nhân viên trong mysql
    public void xoaNhanVien(String maNV) {
        if(maNV == null || maNV.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã nhân viên không được để trống!");
        }
        String sql = "DELETE FROM Nhan_Vien WHERE Ma_NV = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, maNV);
            stmt.executeUpdate();

            System.out.println("Xóa nhân viên thành công!");
        } 
        catch(Exception e) {
            throw new RuntimeException("Lỗi khi xóa nhân viên " + e.getMessage(), e);
        }
    }

    //Phương thức cập nhật nhân viên bằng mã nhân viên trong mysql
    public void capNhatNhanVien(NhanVien nv) {
        if(nv == null || nv.getMaNV() == null || nv.getTenNV() == null) {
            throw new IllegalArgumentException("Nhân viên hoặc các trường bắt buộc không được để trống");
        }
        String sql = "UPDATE Nhan_Vien SET Ho_Ten = ?, SDT = ?, Gioi_Tinh = ?, Chuc_Vu = ?, Ngay_Sinh = ? WHERE Ma_NV = ?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nv.getTenNV());
            stmt.setString(2, nv.getSoDienThoai());
            stmt.setString(3, nv.getGioiTinh());
            stmt.setString(4, nv.getChucVu());
            if (nv.getNgaySinh() != null) {
                stmt.setDate(5, Date.valueOf(nv.getNgaySinh()));
            } else {
                stmt.setNull(5, Types.DATE);
            }
            stmt.setString(6, nv.getMaNV());
            stmt.executeUpdate();

            System.out.println("Cập nhật nhân viên thành công!");
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi cập nhật nhân viên " + e.getMessage(), e);
        }
    }
    
    //Phương thức lấy tất cả nhân viên
    public List<NhanVien> layTatCaNhanVien() {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM Nhan_Vien";

        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("Ma_NV"));
                nv.setTenNV(rs.getString("Ho_Ten"));
                nv.setSoDienThoai(rs.getString("SDT"));
                nv.setGioiTinh(rs.getString("Gioi_Tinh"));
                nv.setChucVu(rs.getString("Chuc_Vu"));
                nv.setNgaySinh(rs.getDate("Ngay_Sinh") != null ? rs.getDate("Ngay_Sinh").toLocalDate() : null);
                list.add(nv);
            }
        } 
        catch(SQLException e) {
            throw new RuntimeException("Lỗi khi lấy danh sách nhân viên: " + e.getMessage(), e);
        }
        return list;
    }

    //Phương thức tìm nhân viên theo mã nhân viên
    public NhanVien timTheoMaNhanVien(String maNV) {
        if(maNV == null || maNV.trim().isEmpty()) {
            throw new RuntimeException("Mã nhân viên không được để trống!");
        }
        String sql = "SELECT * FROM Nhan_Vien WHERE Ma_NV = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maNV);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                   return new NhanVien(
                    rs.getString("Ma_NV"),
                    rs.getString("Ho_Ten"),
                    rs.getString("SDT"),
                    rs.getString("Gioi_Tinh"),
                    rs.getString("Chuc_Vu"),
                    rs.getDate("Ngay_Sinh") != null ? rs.getDate("Ngay_Sinh").toLocalDate() : null
                   );
                }
            }
        } 
        catch (Exception e) {
            throw new RuntimeException("Lỗi khi tìm theo mã nhân viên " + e.getMessage(), e);
        }
        return null;
    }

    public List<NhanVien> timKiem(String keyword) {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM Nhan_Vien WHERE Ma_NV LIKE ? OR Ho_Ten LIKE ?";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            String likeKeyword = "%" + (keyword == null ? "" : keyword) + "%";
            stmt.setString(1, likeKeyword);
            stmt.setString(2, likeKeyword);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    NhanVien nv = new NhanVien();
                    nv.setMaNV(rs.getString("Ma_NV"));
                    nv.setTenNV(rs.getString("Ho_Ten"));
                    nv.setSoDienThoai(rs.getString("SDT"));
                    nv.setGioiTinh(rs.getString("Gioi_Tinh"));
                    nv.setChucVu(rs.getString("Chuc_Vu"));
                    nv.setNgaySinh(rs.getDate("Ngay_Sinh") != null ? rs.getDate("Ngay_Sinh").toLocalDate() : null);
                    list.add(nv);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tìm kiếm nhân viên: " + e.getMessage(), e);
        }
        return list;
    }

    public static void main(String[] args) {
        NhanVienDAO dao = new NhanVienDAO();
        dao.createTable();

        // Thêm nhân viên
        // NhanVien nv1 = new NhanVien("NV001", "Nguyen Van A", "0123456789", "Nam", "Quản lý", LocalDate.of(1990, 1, 1));
        // dao.themNhanVien(nv1);
        // dao.xoaNhanVien("NV001");

        // // Lấy tất cả nhân viên
        // List<NhanVien> nhanViens = dao.layTatCaNhanVien();
        // nhanViens.forEach(System.out::println);

        // // Tìm nhân viên theo mã
        // NhanVien foundNV = dao.timTheoMaNhanVien("NV001");
        // System.out.println("Tìm thấy: " + foundNV);

        // // Cập nhật nhân viên
        // nv1.setTenNV("Nguyen Van B");
        // dao.capNhatNhanVien(nv1);

        // // Xóa nhân viên
        // dao.xoaNhanVien("NV001");
    }
}