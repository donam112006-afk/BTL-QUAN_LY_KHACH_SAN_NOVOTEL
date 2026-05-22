package model;

import java.sql.Date;

public class DatPhong {
    private int maDat; 
    private String maPhong; // BỔ SUNG MÃ PHÒNG (Ví dụ: "PT102")
    private String maKH;
    private String maNV;
    private Date ngayCheckIn; 
    private Date ngayCheckOut; 

    public DatPhong(int maDat, String maPhong, String maKH, String maNV, Date ngayCheckIn, Date ngayCheckOut) {
        this.maDat = maDat;
        this.maPhong = maPhong;
        this.maKH = maKH;
        this.maNV = maNV;
        this.ngayCheckIn = ngayCheckIn;
        this.ngayCheckOut = ngayCheckOut;
    }

    // Các Getter và Setter
    public int getMaDat() { return maDat; }
    public void setMaDat(int maDat) { this.maDat = maDat; }
    
    public String getMaPhong() { return maPhong; }
    public void setMaPhong(String maPhong) { this.maPhong = maPhong; }
    
    public String getMaKH() { return maKH; }
    public void setMaKH(String maKH) { this.maKH = maKH; }
    
    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }
    
    public Date getNgayCheckIn() { return ngayCheckIn; }
    public void setNgayCheckIn(Date ngayCheckIn) { this.ngayCheckIn = ngayCheckIn; }
    
    public Date getNgayCheckOut() { return ngayCheckOut; }
    public void setNgayCheckOut(Date ngayCheckOut) { this.ngayCheckOut = ngayCheckOut; }
}