package model;

public class Phong {
    private int maPhong;
    private String soPhong;
    private int maLoai;
    private String TrangThai;

    public Phong() {}

    public Phong(int maPhong, String soPhong, int maLoai, String trangThai) {
        this.maPhong = maPhong;
        this.soPhong = soPhong;
        this.maLoai = maLoai;
        this.TrangThai = trangThai;
    }

    public int getMaPhong() {
        return maPhong;
    }
    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public String getSoPhong() {
        return soPhong;
    }
    public void setSoPhong(String soPhong) {
        this.soPhong = soPhong;
    }

    public int getMaLoai() {
        return maLoai;
    }
    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTrangThai() {
        return TrangThai;
    }
    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }
}
