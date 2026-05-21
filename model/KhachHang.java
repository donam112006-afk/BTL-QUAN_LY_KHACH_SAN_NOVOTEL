package model;


public class KhachHang {
    private String MaKH;
    private String HoTen;
    private String Sdt;
    private String Cccd;
    private String DiaChi;

    public KhachHang() {
    }

    public KhachHang(String maKH, String hoTen, String sdt, String cccd, String diaChi) {
        this.MaKH = maKH;
        this.HoTen = hoTen;
        this.Sdt = sdt;
        this.Cccd = cccd;
        this.DiaChi = diaChi;
    }

    public String getMaKH() { return MaKH; }
    public void setMaKH(String maKH) { this.MaKH = maKH; }

    public String getHoTen() { return HoTen; }
    public void setHoTen(String hoTen) { this.HoTen = hoTen; }

    public String getSdt() { return Sdt; }
    public void setSdt(String sdt) { this.Sdt = sdt; }

    public String getCccd() { return Cccd; }
    public void setCccd(String cccd) { this.Cccd = cccd; }

    public String getDiaChi() { return DiaChi; }
    public void setDiaChi(String diaChi) { this.DiaChi = diaChi; }
}
