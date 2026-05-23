package model;

import java.sql.Date;

public class DatPhong {
    private String maDat; // Cập nhật thành String theo VARCHAR(15)
    private String maPhong; 
    private String maKH;
    private String maNV;
    private Date ngayCheckIn; 
    private Date ngayCheckOut; 
    private double tienCoc; // Bổ sung theo bảng Dat_Phong
    private String trangThai; // Bổ sung theo bảng Dat_Phong

    public DatPhong(String maDat, String maPhong, String maKH, String maNV, Date ngayCheckIn, Date ngayCheckOut, double tienCoc, String trangThai) {
        this.maDat = maDat;
        this.maPhong = maPhong;
        this.maKH = maKH;
        this.maNV = maNV;
        this.ngayCheckIn = ngayCheckIn;
        this.ngayCheckOut = ngayCheckOut;
        this.tienCoc = tienCoc;
        this.trangThai = trangThai;
    }

    // Các Getter và Setter
    public String getMaDat() { return maDat; }
    public void setMaDat(String maDat) { this.maDat = maDat; }
    
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

    public double getTienCoc() { return tienCoc; }
    public void setTienCoc(double tienCoc) { this.tienCoc = tienCoc; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}