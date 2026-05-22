package model;

public class DichVu {
    private String maDV;
    private String tenDV;
    private int giaDV; // Theo đúng file chia việc: kiểu int[cite: 2]
    private String donViTinh;

    public DichVu(String maDV, String tenDV, int giaDV, String donViTinh) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.giaDV = giaDV;
        this.donViTinh = donViTinh;
    }

    public String getMaDV() { return maDV; }
    public void setMaDV(String maDV) { this.maDV = maDV; }
    public String getTenDV() { return tenDV; }
    public void setTenDV(String tenDV) { this.tenDV = tenDV; }
    public int getGiaDV() { return giaDV; }
    public void setGiaDV(int giaDV) { this.giaDV = giaDV; }
    public String getDonViTinh() { return donViTinh; }
    public void setDonViTinh(String donViTinh) { this.donViTinh = donViTinh; }
}