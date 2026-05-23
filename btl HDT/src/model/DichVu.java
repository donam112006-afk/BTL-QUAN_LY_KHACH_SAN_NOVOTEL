package model;

public class DichVu {
    private String maDV;
    private String tenDV;
    private double giaDV; // Đổi sang double để khớp với DECIMAL(15, 2)
    private String donViTinh;

    public DichVu(String maDV, String tenDV, double giaDV, String donViTinh) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.giaDV = giaDV;
        this.donViTinh = donViTinh;
    }

    public String getMaDV() { return maDV; }
    public void setMaDV(String maDV) { this.maDV = maDV; }
    public String getTenDV() { return tenDV; }
    public void setTenDV(String tenDV) { this.tenDV = tenDV; }
    public double getGiaDV() { return giaDV; }
    public void setGiaDV(double giaDV) { this.giaDV = giaDV; }
    public String getDonViTinh() { return donViTinh; }
    public void setDonViTinh(String donViTinh) { this.donViTinh = donViTinh; }
}