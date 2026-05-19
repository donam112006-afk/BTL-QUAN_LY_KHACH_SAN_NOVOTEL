Quy trình up code hàng ngày (Dành cho thành viên)
Vì nhánh main đã bị khóa, thành viên không được push thẳng lên main. Họ phải tạo nhánh phụ để làm việc.

1. Tạo nhánh mới để code tính năng
Trước khi viết code mới, tạo một nhánh phụ (ví dụ nhánh tên là feature-datphong):

git checkout -b feature-datphong
2. Code xong thì Up lên GitHub
Sau khi viết code, chỉnh sửa file xong xuôi, chạy 3 lệnh sau để đưa code lên mạng:

# Xem các file đã thay đổi (không bắt buộc nhưng nên check)
git status

# 1. Thêm các file đã sửa vào hàng chờ chuẩn bị up
git add .

# 2. Đặt tên cho gói code vừa sửa (viết không dấu, ngắn gọn)
git commit -m "Hoan thanh giao dien dat phong"

# 3. Đẩy nhánh phụ này lên GitHub
git push origin feature-datphong
