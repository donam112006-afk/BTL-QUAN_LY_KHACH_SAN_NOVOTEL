package model;

import java.util.Date;

public class NhanVien {
    private String MaNV;
    private String HoTen;
    private String Sdt;
    private String GioiTinh;
    private String ChucVu;
    private Date NgaySinh;

    public NhanVien() {}

    public NhanVien(String MaNV, String HoTen, String Sdt, String GioiTinh, String ChucVu, Date NgaySinh) {
        this.MaNV = MaNV;
        this.HoTen = HoTen;
        this.Sdt = Sdt;
        this.GioiTinh = GioiTinh;
        this.ChucVu = ChucVu;
        this.NgaySinh = NgaySinh;
    }

    public String getMaNV() { return MaNV; }
    public void setMaNV(String MaNV) { this.MaNV = MaNV; }
    public String getHoTen() { return HoTen; }
    public void setHoTen(String HoTen) { this.HoTen = HoTen; }
    public String getSdt() { return Sdt; }
    public void setSdt(String Sdt) { this.Sdt = Sdt; }
    public String getGioiTinh() { return GioiTinh; }
    public void setGioiTinh(String GioiTinh) { this.GioiTinh = GioiTinh; }
    public String getChucVu() { return ChucVu; }
    public void setChucVu(String ChucVu) { this.ChucVu = ChucVu; }
    public Date getNgaySinh() { return NgaySinh; }
    public void setNgaySinh(Date NgaySinh) { this.NgaySinh = NgaySinh; }
}