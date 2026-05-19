package model;

public class TaiKhoan {
    //Khai báo thuộc tính của TaiKhoan
    private String MaTK;
    private String TenDangNhap;
    private String MatKhau;
    private String VaiTro;
    private String MaNV;

    //Hàm khởi tạo không tham số
    public TaiKhoan() {
        MaTK = "";
        TenDangNhap = "unknown";
        MatKhau = "unknown";
        VaiTro = "NHANVIEN";
        MaNV = "";
    }

    //Hàm khởi tạo có tham số
    public TaiKhoan(String MaTK, String TenDangNhap, String MatKhau, String VaiTro, String MaNV) {
        this.MaTK = MaTK;
        this.TenDangNhap = TenDangNhap;
        this.MatKhau = MatKhau;
        this.VaiTro = VaiTro;
        this.MaNV = MaNV;
    }

    //Getter
    public String getMaTK() {return MaTK;}
    public String getTenDangNhap() {return TenDangNhap;}
    public String getMatKhau() {return MatKhau;}
    public String getVaiTro() {return VaiTro;}
    public String getMaNV() {return MaNV;}

    //Setter
    public void setMaTK(String MaTK) {this.MaTK = MaTK;}
    public void setTenDangNhap(String TenDangNhap) {
        if (TenDangNhap == null || TenDangNhap.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên đăng nhập không được để trống");
        }
        this.TenDangNhap = TenDangNhap;
    }
    public void setMatKhau(String MatKhau) {
        if (MatKhau == null) {
            throw new IllegalArgumentException("Mật khẩu không được để trống");
        }
        this.MatKhau = MatKhau;}
    public void setVaiTro(String VaiTro) {
        if(VaiTro != null && !VaiTro.equals("ADMIN") && !VaiTro.equals("NHANVIEN")) {
            throw new IllegalArgumentException("Vai trò phải là ADMIN hoặc NHANVIEN");
        }
        this.VaiTro = VaiTro;}
    public void setMaNV(String MaNV) {this.MaNV = MaNV;}

    //hàm toString() của TaiKhoan
    @Override
    public String toString() {
        return "TaiKhoan {"
             + "MaTK = " + MaTK + " / "
             + "TenDangNhap = " + TenDangNhap + " / "
             + "MatKhau = " + MatKhau + " / "
             + "VaiTro = " + VaiTro + " / "
             + "MaNV = " + MaNV
             + "}";
    }

    //Hàm main để test TaiKhoan (8/3/2026)
    // public static void main(String[] args) {
    //     TaiKhoan tk = new TaiKhoan();
    //     tk.setVaiTro("helo");
    //     tk.getVaiTro();
    // }
}
