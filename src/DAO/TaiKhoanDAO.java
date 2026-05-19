package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.TaiKhoan;

public class TaiKhoanDAO {
    //Phương thức tạo bảng tài khoản trong mysql
    public void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS tai_khoan (
                Ma_TK           VARCHAR(10) PRIMARY KEY,
                Ten_Dang_Nhap   VARCHAR(50) NOT NULL,
                Mat_Khau        VARCHAR(100) NOT NULL,
                Vai_Tro         VARCHAR(10) NOT NULL DEFAULT 'NHANVIEN',
                Ma_NV           VARCHAR(50),
                FOREIGN KEY (Ma_NV) REFERENCES Nhan_Vien(Ma_NV)
            )
        """;
        try(Statement stmt = DBConnection.getConnection().createStatement()) {
            stmt.execute(sql);
            System.out.println("Tạo bảng tài khoản thành công!");
        }
        catch (SQLException e) {
            throw new RuntimeException("Lỗi khi tạo bảng tài khoản " + e.getMessage(), e);
        }
    }

    //Tạo admin mặc định
    public void taoAdminMacDinh() {
        String kiemtra = "SELECT COUNT(*) FROM tai_khoan WHERE Vai_Tro = 'ADMIN'";
        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(kiemtra)) {
            if(rs.next() && rs.getInt(1) == 0) {
                String sql = "INSERT INTO tai_khoan (Ma_TK, Ten_Dang_Nhap, Mat_Khau, Vai_Tro, Ma_NV) VALUES ('TK001', 'admin', 'admin123', 'ADMIN', 'NV001')";
                stmt.executeUpdate(sql);
                System.out.println("Tạo thành công ADMIN mặc định (Tên đăng nhập: admin / Password: admin123)!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tạo tài khoản ADMIN mặc định " + e.getMessage(), e);
        }
    }

    //Phương thức thêm tài khoản vào bảng tai_khoan trong mysql
    public void themTaiKhoan(TaiKhoan tk) {
        if (tk == null || tk.getTenDangNhap() == null || tk.getMatKhau() == null) {
            throw new IllegalArgumentException("Tài khoản hoặc các trường bắt buộc không được để trống");
        }
        String sql = "INSERT INTO tai_khoan (Ma_TK, Ten_Dang_Nhap, Mat_Khau, Vai_Tro, Ma_NV) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, tk.getMaTK());
            stmt.setString(2, tk.getTenDangNhap());
            stmt.setString(3, tk.getMatKhau());
            stmt.setString(4, tk.getVaiTro());
            stmt.setString(5, tk.getMaNV());
            stmt.executeUpdate();

            System.out.println("Thêm tài khoản thành công!");
        } 
        catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm tài khoản: " + e.getMessage(), e);
        }
    }

    //Phương thức xóa nhân viên bằng mã nhân viên trong mysql
    public void xoaTaiKhoanTheoMaTK(String MaTK) {
        if(MaTK == null || MaTK.trim().isEmpty()) {
            throw new IllegalArgumentException("Mã nhân viên không được để trống!");
        }
        String sql = "DELETE FROM tai_khoan WHERE Ma_TK = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, MaTK);
            stmt.executeUpdate();

            System.out.println("Xóa tài khoản thành công!");
        } 
        catch(Exception e) {
            throw new RuntimeException("Lỗi khi xóa tài khoản " + e.getMessage(), e);
        }
    }

    //Phương thức đổi mật khẩu
    public void doiMatKhau(String MaTK, String MatKhauMoi) {
        String sql = "UPDATE tai_khoan SET Mat_Khau = ? WHERE Ma_TK = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, MatKhauMoi);
            stmt.setString(2, MaTK);
            stmt.executeUpdate();
            System.out.println("Đổi mật khẩu thành công!");
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi đổi mật khẩu " + e.getMessage(), e);
        }
    }

    //Phương thức đổi tên đăng nhập
    public void doiTenDangNhap(String MaTK, String TenDangNhapMoi) {
        String sql = "UPDATE tai_khoan SET Ten_Dang_Nhap = ? WHERE Ma_TK = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, TenDangNhapMoi);
            stmt.setString(2, MaTK);
            stmt.executeUpdate();
            System.out.println("Đổi tên đăng nhập thành công!");
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi đổi tên đăng nhập " + e.getMessage(), e);
        }
    }

    //Phương thức cập nhật vai trò của tài khoản
    public void capNhatVaiTro(String MaTK, String VaiTro) {
        String sql = "UPDATE tai_khoan SET Vai_Tro = ? WHERE Ma_TK = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, VaiTro);
            stmt.setString(2, MaTK);
            stmt.executeUpdate();
            System.out.println("Cập nhật vai trò thành công!");
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi cập nhật vai trò " + e.getMessage(), e);
        }
    }

    //Phương thức đăng nhập
    public TaiKhoan dangNhap(String TenDangNhap, String MatKhau) {
        String sql = "SELECT * FROM tai_khoan WHERE ten_dang_nhap = ? AND mat_khau = ?";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, TenDangNhap);
            stmt.setString(2, MatKhau);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return new TaiKhoan(
                    rs.getString("Ma_TK"),
                    rs.getString("Ten_Dang_Nhap"),
                    rs.getString("Mat_Khau"),
                    rs.getString("Vai_Tro"),
                    rs.getString("Ma_NV")
                );
            }
        } catch (Exception e) {
           throw new RuntimeException("Lỗi khi chương trình thực hiện đăng nhập " + e.getMessage(), e);
        }
        return null;
    }

    //Phương thức lấy tất cả các tài khoản
    public List<TaiKhoan> layTatCaTaiKhoan() {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM tai_khoan";

        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setMaTK(rs.getString("Ma_TK"));
                tk.setTenDangNhap(rs.getString("Ten_Dang_Nhap"));
                tk.setMatKhau(rs.getString("Mat_Khau"));
                tk.setVaiTro(rs.getString("Vai_Tro"));
                tk.setMaNV(rs.getString("Ma_NV"));
                list.add(tk);
                System.out.println(tk);
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy danh sách tài khoản: " + e.getMessage(), e);
        }
        return list;
    }

    public TaiKhoan layTheoMaTK(String MaTK) {
        String sql = "SELECT * FROM tai_khoan";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, MaTK);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return new TaiKhoan(
                    rs.getString("Ma_TK"),
                    rs.getString("Ten_Dang_Nhap"),
                    rs.getString("Mat_Khau"),
                    rs.getString("Vai_Tro"),
                    rs.getString("Ma_NV")
                ); 
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi chương trình lấy theo mã tài khoản " + e.getMessage(), e);
        }
        return null;
    }

    public TaiKhoan layTheoTenDangNhap(String TenDangNhap) {
        String sql = "SELECT * FROM tai_khoan";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, TenDangNhap);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return new TaiKhoan(
                    rs.getString("Ma_TK"),
                    rs.getString("Ten_Dang_Nhap"),
                    rs.getString("Mat_Khau"),
                    rs.getString("Vai_Tro"),
                    rs.getString("Ma_NV")
                ); 
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi chương trình lấy theo tên đăng nhập " + e.getMessage(), e);
        }
        return null;
    }

    public static void main(String[] args) {
        TaiKhoanDAO dao = new TaiKhoanDAO();
        dao.createTable();
        // TaiKhoan tk = new TaiKhoan();
        // tk.setMaTK("TK003");
        // tk.setTenDangNhap("maheu");
        // tk.setMatKhau("maheu2707");
        // tk.setVaiTro("ADMIN");
        // tk.setMaNV("NV003");

        //  dao.themTaiKhoan(tk);
        // dao.taoAdminMacDinh();
        // dao.layTatCaTaiKhoan();
        // dao.doiMatKhau("TK002", "manhhieu27");
        
    }
}
