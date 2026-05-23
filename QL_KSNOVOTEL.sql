CREATE DATABASE ql_khachsannovotel;
use ql_khachsannovotel; 



-- =========================================================================
-- 1. BẢNG LOẠI PHÒNG (Ảnh số 3: Loai_Phong)
-- =========================================================================
CREATE TABLE Loai_Phong (
    Ma_Loai VARCHAR(10) PRIMARY KEY,
    Ten_Loai NVARCHAR(50) NOT NULL,
    Gia_Theo_Ngay DECIMAL(15, 2) NOT NULL, -- Đổi sang DECIMAL để lưu tiền tệ chuẩn hơn INT
    So_Nguoi_Toi_Da INT NOT NULL
);

-- =========================================================================
-- 2. BẢNG PHÒNG (Ảnh số 2: Phong)
-- =========================================================================
CREATE TABLE Phong (
    Ma_Phong VARCHAR(10) PRIMARY KEY,
    So_Phong VARCHAR(10) NOT NULL,
    Ma_Loai VARCHAR(10),
    Trang_Thai NVARCHAR(30), -- Trống, Có Khách, Đang dọn...
    FOREIGN KEY (Ma_Loai) REFERENCES Loai_Phong(Ma_Loai)
);

-- =========================================================================
-- 3. BẢNG KHÁCH HÀNG (Ảnh số 4: Khach_Hang)
-- =========================================================================
CREATE TABLE Khach_Hang (
    Ma_KH VARCHAR(10) PRIMARY KEY,
    Ho_Ten NVARCHAR(100) NOT NULL,
    SDT VARCHAR(15),
    Dia_Chi NVARCHAR(255),
    So_CCCD VARCHAR(20) NOT NULL UNIQUE -- Tôi đã bổ sung thêm cột này vì nghiệp vụ khách sạn bắt buộc phải có
);

-- =========================================================================
-- 4. BẢNG NHÂN VIÊN (Ảnh số 9: Nhan_Vien)
-- =========================================================================
CREATE TABLE Nhan_Vien (
    Ma_NV VARCHAR(10) PRIMARY KEY,
    Ho_Ten NVARCHAR(100) NOT NULL,
    SDT VARCHAR(15),
    Gioi_Tinh NVARCHAR(10),
    Chuc_Vu NVARCHAR(50),
    Ngay_Sinh DATE
);

-- =========================================================================
-- 5. BẢNG TÀI KHOẢN (Ảnh số 1: Tai_Khoan)
-- =========================================================================
CREATE TABLE Tai_Khoan (
    Ma_TK VARCHAR(10) PRIMARY KEY,
    Ten_Dang_Nhap VARCHAR(50) NOT NULL UNIQUE,
    Mat_Khau VARCHAR(100) NOT NULL,
    Vai_Tro VARCHAR(10), -- Admin, NhanVien...
    Ma_NV VARCHAR(10),
    FOREIGN KEY (Ma_NV) REFERENCES Nhan_Vien(Ma_NV)
);

-- =========================================================================
-- 6. BẢNG DỊCH VỤ (Ảnh số 6: Dich_Vu)
-- =========================================================================
CREATE TABLE Dich_Vu (
    Ma_DV VARCHAR(10) PRIMARY KEY,
    Ten_DV NVARCHAR(100) NOT NULL,
    Gia_DV DECIMAL(15, 2) NOT NULL,
    Don_Vi_Tinh NVARCHAR(30)
);

-- =========================================================================
-- 7. BẢNG ĐẶT PHÒNG (Ảnh số 8: Dat_Phong)
-- =========================================================================
CREATE TABLE Dat_Phong (
    Ma_Dat VARCHAR(15) PRIMARY KEY,
    Ma_Phong VARCHAR(10),
    Ma_KH VARCHAR(10),
    Ma_NV VARCHAR(10),
    Ngay_Vao DATETIME NOT NULL,
    Ngay_Ra DATETIME NOT NULL,
    Tien_Coc DECIMAL(15, 2) DEFAULT 0, -- Bổ sung tiền cọc để đúng luồng nghiệp vụ
    Trang_Thai NVARCHAR(50), -- Chờ nhận, Đã nhận phòng, Đã trả phòng
    FOREIGN KEY (Ma_Phong) REFERENCES Phong(Ma_Phong),
    FOREIGN KEY (Ma_KH) REFERENCES Khach_Hang(Ma_KH),
    FOREIGN KEY (Ma_NV) REFERENCES Nhan_Vien(Ma_NV)
);

-- =========================================================================
-- 8. BẢNG HÓA ĐƠN (Ảnh số 5: Hoa_Don)
-- =========================================================================
CREATE TABLE Hoa_Don (
    Ma_HD VARCHAR(15) PRIMARY KEY,
    Ma_Dat VARCHAR(15),
    Ma_NV VARCHAR(10),
    Ngay_Thanh_Toan DATETIME DEFAULT CURRENT_TIMESTAMP,
    Tong_Tien DECIMAL(15, 2),
    FOREIGN KEY (Ma_Dat) REFERENCES Dat_Phong(Ma_Dat),
    FOREIGN KEY (Ma_NV) REFERENCES Nhan_Vien(Ma_NV)
);

-- =========================================================================
-- 9. BẢNG CHI TIẾT HÓA ĐƠN (Ảnh số 7: Chi_Tiet_Hoa_Don)
-- =========================================================================
CREATE TABLE Chi_Tiet_Hoa_Don (
    Ma_CTHD VARCHAR(15) PRIMARY KEY,
    Ma_HD VARCHAR(15),
    Ma_DV VARCHAR(10),
    So_Luong INT NOT NULL,
    Don_Gia DECIMAL(15, 2), -- Đơn giá lúc gọi dịch vụ
    FOREIGN KEY (Ma_HD) REFERENCES Hoa_Don(Ma_HD),
    FOREIGN KEY (Ma_DV) REFERENCES Dich_Vu(Ma_DV)
);
select * from Nhan_Vien; 
select * from Tai_Khoan; 
select * from Phong; 
select * from Loai_Phong;
select * from Khach_Hang;
select * from Hoa_Don;
select * from Dich_Vu;
select * from Dat_Phong; 
select * from Chi_Tiet_Hoa_Don; 


