package model;


public class NhanVien {
    private String MaNV;
    private String HoTen;
    private String Sdt;
    private String GioiTinh;
    private String ChucVu;

    public NhanVien() {
    }

    public NhanVien(String maNV, String hoTen, String sdt, String gioiTinh, String chucVu) {
        this.MaNV = maNV;
        this.HoTen = hoTen;
        this.Sdt = sdt;
        this.GioiTinh = gioiTinh;
        this.ChucVu = chucVu;
    }

    public String getMaNV() { return MaNV; }
    public void setMaNV(String maNV) { this.MaNV = maNV; }

    public String getHoTen() { return HoTen; }
    public void setHoTen(String hoTen) { this.HoTen = hoTen; }

    public String getSdt() { return Sdt; }
    public void setSdt(String sdt) { this.Sdt = sdt; }

    public String getGioiTinh() { return GioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.GioiTinh = gioiTinh; }

    public String getChucVu() { return ChucVu; }
    public void setChucVu(String chucVu) { this.ChucVu = chucVu; }
}
