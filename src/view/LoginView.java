package view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnGoRegister;

    public LoginView() {
        setTitle("Đăng Nhập Hệ Thống Khách Sạn");
        setSize(760, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel container = new JPanel(new GridLayout(1, 2, 20, 0));
        container.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        container.setBackground(Color.WHITE);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(Color.WHITE);

        // Logo hoặc Icon giả lập
        JLabel lblIcon = new JLabel("HOTEL SYSTEM", JLabel.CENTER);
        lblIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblIcon.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblIcon.setForeground(new Color(52, 73, 94));
        formPanel.add(lblIcon);
        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Ô nhập Username
        txtUsername = new JTextField();
        txtUsername.setToolTipText("Tên đăng nhập");
        txtUsername.setBorder(BorderFactory.createTitledBorder("Tên đăng nhập"));
        txtUsername.setMaximumSize(new Dimension(280, 40));
        txtUsername.setPreferredSize(new Dimension(280, 40));
        formPanel.add(txtUsername);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Ô nhập Password
        txtPassword = new JPasswordField();
        txtPassword.setBorder(BorderFactory.createTitledBorder("Mật khẩu"));
        txtPassword.setMaximumSize(new Dimension(280, 40));
        txtPassword.setPreferredSize(new Dimension(280, 40));
        formPanel.add(txtPassword);
        formPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Nút Đăng nhập
        btnLogin = new JButton("ĐĂNG NHẬP");
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnLogin.setBackground(new Color(41, 128, 185));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        formPanel.add(btnLogin);

        // Link sang trang đăng ký
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        btnGoRegister = new JButton("Chưa có tài khoản? Đăng ký ngay");
        btnGoRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGoRegister.setBorderPainted(false);
        btnGoRegister.setContentAreaFilled(false);
        btnGoRegister.setFont(new Font("SansSerif", Font.ITALIC, 11));
        btnGoRegister.addActionListener(e -> {
            new RegisterView().setVisible(true);
            setVisible(false);
        });
        formPanel.add(btnGoRegister);

        ImagePanel imagePanel = new ImagePanel("src/images/thiet-ke-phan-mem-quan-ly-khach-san-1000x562.jpg");
        imagePanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

        container.add(formPanel);
        container.add(imagePanel);
        add(container);
    }

    public static void main(String[] args) {
        // Chạy thử LoginUI
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}