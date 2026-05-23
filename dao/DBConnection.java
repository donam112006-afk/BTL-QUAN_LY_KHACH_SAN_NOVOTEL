package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // 1. Kiểm tra chính xác tên database của bạn (ql_khachsannovotel)
    private static final String URL = "jdbc:mysql://localhost:3306/ql_khachsannovotel?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&characterEncoding=UTF-8";
    private static final String USER = "root"; 
    
    // 2. THAY ĐỔI: Điền chính xác mật khẩu MySQL Workbench của máy bạn vào đây
    private static final String PASSWORD = "123456"; // hoặc "123456"
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Nạp driver cho JVM
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Lỗi: Chưa cấu hình đúng file jar trong Referenced Libraries!");
        } catch (SQLException e) {
            System.out.println("❌ Lỗi kết nối: Sai mật khẩu root hoặc MySQL Server chưa được bật!");
            System.out.println("Mã lỗi chi tiết từ MySQL: " + e.getMessage());
        }
        return conn;
    }

    // Hàm test nhanh
    public static void main(String[] args) {
        Connection testConn = getConnection();
        if (testConn != null) {
            System.out.println(" KET NOI DATABASE THANH CONG!");
            try {
                testConn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(" Kết nối thất bại. Hãy kiểm tra lại mật khẩu hoặc đảm bảo MySQL đang chạy.");
        }
    }
}