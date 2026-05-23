package model;

public class KhachHang {
    private String MaKH;
    private String HoTen;
    private String Sdt;
    private String DiaChi;
    private String SoCCCD;

    public KhachHang() {}

    public KhachHang(String MaKH, String HoTen, String Sdt, String DiaChi, String SoCCCD) {
        this.MaKH = MaKH;
        this.HoTen = HoTen;
        this.Sdt = Sdt;
        this.DiaChi = DiaChi;
        this.SoCCCD = SoCCCD;
    }

    public String getMaKH() { return MaKH; }
    public void setMaKH(String MaKH) { this.MaKH = MaKH; }
    public String getHoTen() { return HoTen; }
    public void setHoTen(String HoTen) { this.HoTen = HoTen; }
    public String getSdt() { return Sdt; }
    public void setSdt(String Sdt) { this.Sdt = Sdt; }
    public String getDiaChi() { return DiaChi; }
    public void setDiaChi(String DiaChi) { this.DiaChi = DiaChi; }
    public String getSoCCCD() { return SoCCCD; }
    public void setSoCCCD(String SoCCCD) { this.SoCCCD = SoCCCD; }
}