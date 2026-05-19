package model;

import java.time.LocalDate;

public class NhanVien {
    //Khai báo thuộc tính của NhanVien
    private String MaNV;
    private String TenNV;
    private String SoDienThoai;
    private String GioiTinh;
    private String ChucVu;
    private LocalDate NgaySinh;

    //Hàm khởi tạo không tham số
    public NhanVien() {
        MaNV = "";
        TenNV = "";
        SoDienThoai = "";
        GioiTinh = "";
        ChucVu = "";
        NgaySinh = LocalDate.now();
    }

    //Hàm khởi tạo có tham số
    public NhanVien(String MaNV, String TenNV, String SoDienThoai, String GioiTinh, String ChucVu, LocalDate NgaySinh) {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.SoDienThoai = SoDienThoai;
        this.GioiTinh = GioiTinh;
        this.ChucVu = ChucVu;
        this.NgaySinh = NgaySinh;
    }

    //Getter
    public String getMaNV() {return MaNV;}
    public String getTenNV() {return TenNV;}
    public String getSoDienThoai() {return SoDienThoai;}
    public String getGioiTinh() {return GioiTinh;}
    public String getChucVu() {return ChucVu;}
    public LocalDate getNgaySinh() {return NgaySinh;}

    //Setter
    public void setMaNV(String MaNV) {this.MaNV = MaNV;}
    public void setTenNV(String TenNV) {
        if (TenNV == null || TenNV.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên nhân viên không được để trống");
        }
        this.TenNV = TenNV;
    }
    public void setSoDienThoai(String SoDienThoai) {this.SoDienThoai = SoDienThoai;}
    public void setGioiTinh(String GioiTinh) {
        if (GioiTinh != null && !GioiTinh.equals("Nam") && !GioiTinh.equals("Nữ") && !GioiTinh.equals("Khác")) {
            throw new IllegalArgumentException("Giới tính phải là 'Nam', 'Nữ' hoặc 'Khác'");
        }
        this.GioiTinh = GioiTinh;
    }
    public void setChucVu(String ChucVu) {this.ChucVu = ChucVu;}
    public void setNgaySinh(LocalDate NgaySinh) {this.NgaySinh = NgaySinh;}

    //hàm toString() của NhanVien
    @Override
    public String toString() {
        return "NhanVien {"
             + "MaNV = " + MaNV + " / "
             + "TenNV = " + TenNV + " / "
             + "SoDienThoai = " + SoDienThoai + " / "
             + "GioiTinh = " + GioiTinh + " / "
             + "ChucVu = " + ChucVu + " / "
             + "NgaySinh = " + NgaySinh
             + "}";
    }
}