package model;

public class LoaiPhong {
    private int maLoai;
    private String tenLoai;
    private double giaTheoNgay;
    private int soNguoiToiDa;

    public LoaiPhong() {}

    public LoaiPhong(int maLoai, String tenLoai, double giaTheoNgay, int soNguoiToiDa) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.giaTheoNgay = giaTheoNgay;
        this.soNguoiToiDa = soNguoiToiDa;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public double getGiaTheoNgay() {
        return giaTheoNgay;
    }

    public void setGiaTheoNgay(double giaTheoNgay) {
        this.giaTheoNgay = giaTheoNgay;
    }

    public int getSoNguoiToiDa() {
        return soNguoiToiDa;
    }

    public void setSoNguoiToiDa(int soNguoiToiDa) {
        this.soNguoiToiDa = soNguoiToiDa;
    }
}