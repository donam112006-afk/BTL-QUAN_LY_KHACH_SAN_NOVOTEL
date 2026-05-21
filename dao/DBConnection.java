package dao;

import java.sql.*;
import java.util.logging.*; 

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/ql_khachsannovotel";
    private static final String USER ="root";
    private static final String PASSWORD = "123456";
    private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName());
    
    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                LOGGER.info("Kết nối MySQL thành công!");
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Kết nối thất bại!", e);
            }
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                    LOGGER.info("Đã đóng kết nối!");
                }
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Không thể đóng kết nối!", e);
            }
        }
    }
    public static void main(String[] args) {
        Connection connection = DBConnection.getConnection();
        if (connection != null) {
            System.out.println("Kết nối thành công!");
            DBConnection.closeConnection(connection);
        } else {
            System.out.println("Kết nối thất bại!");
        }
    }
}
