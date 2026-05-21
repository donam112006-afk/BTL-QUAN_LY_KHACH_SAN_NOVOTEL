package model;

public class TaiKhoan {
    private String MaTK;
    private String TenDangNhap;
    private String MatKhau;
    private String VaiTro;
    private String MaNV;

    public TaiKhoan() {
    }

    public TaiKhoan(String maTK, String tenDangNhap, String matKhau, String vaiTro, String maNV) {
        this.MaTK = maTK;
        this.TenDangNhap = tenDangNhap;
        this.MatKhau = matKhau;
        this.VaiTro = vaiTro;
        this.MaNV = maNV;
    }

    public String getMaTK() { 
        return MaTK;
     }
    public void setMaTK(String maTK) {
         this.MaTK = maTK;
         }

    public String getTenDangNhap() {
         return TenDangNhap;
         }
    public void setTenDangNhap(String tenDangNhap) {
         this.TenDangNhap = tenDangNhap; 
        }

    public String getMatKhau() { 
        return MatKhau;
     }
    public void setMatKhau(String matKhau) { 
        this.MatKhau = matKhau;
     }

    public String getVaiTro() {
         return VaiTro;
         }
    public void setVaiTro(String vaiTro) {
         this.VaiTro = vaiTro;
         }

    public String getMaNV() {
         return MaNV;
         }
    public void setMaNV(String maNV) {
         this.MaNV = maNV;
         }
}
