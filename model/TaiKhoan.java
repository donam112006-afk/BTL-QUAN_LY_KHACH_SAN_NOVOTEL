package model;

public class TaiKhoan {
    private String MaTK;
    private String TenDangNhap;
    private String MatKhau;
    private String VaiTro;
    private String MaNV;

    public TaiKhoan() {}

    public TaiKhoan(String MaTK, String TenDangNhap, String MatKhau, String VaiTro, String MaNV) {
        this.MaTK = MaTK;
        this.TenDangNhap = TenDangNhap;
        this.MatKhau = MatKhau;
        this.VaiTro = VaiTro;
        this.MaNV = MaNV;
    }

    public String getMaTK() { return MaTK; }
    public void setMaTK(String MaTK) { this.MaTK = MaTK; }
    public String getTenDangNhap() { return TenDangNhap; }
    public void setTenDangNhap(String TenDangNhap) { this.TenDangNhap = TenDangNhap; }
    public String getMatKhau() { return MatKhau; }
    public void setMatKhau(String MatKhau) { this.MatKhau = MatKhau; }
    public String getVaiTro() { return VaiTro; }
    public void setVaiTro(String VaiTro) { this.VaiTro = VaiTro; }
    public String getMaNV() { return MaNV; }
    public void setMaNV(String MaNV) { this.MaNV = MaNV; }
}