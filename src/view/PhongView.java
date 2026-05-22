package view;

import DAO.PhongDAO;
import model.Phong;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PhongView extends JFrame {

    private JTextField txtMaPhong, txtSoPhong, txtMaLoai, txtTrangThai;
    private JTable tblPhong;
    private DefaultTableModel model;
    private JButton btnThem, btnSua, btnXoa, btnTimKiem;

    private Phong[] dsPhong = new Phong[100];
    private int soLuongPhong = 0;

    public PhongView() {
        setTitle("Quản Lý Phòng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 550);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10)); 

        JPanel pnlInput = new JPanel(new GridLayout(4, 2, 10, 10));
        pnlInput.setBorder(BorderFactory.createTitledBorder("Thông tin chi tiết phòng"));

        pnlInput.add(new JLabel("Mã phòng:"));
        txtMaPhong = new JTextField();
        txtMaPhong.setEditable(false);
        pnlInput.add(txtMaPhong);

        pnlInput.add(new JLabel("Số phòng:"));
        txtSoPhong = new JTextField();
        pnlInput.add(txtSoPhong);

        pnlInput.add(new JLabel("Mã loại phòng:"));
        txtMaLoai = new JTextField();
        pnlInput.add(txtMaLoai);

        pnlInput.add(new JLabel("Trạng thái:"));
        cboTrangThai = new JComboBox<>(new String[]{
            "Trống", "Có khách", "Đang dọn..."
        });
        pnlInput.add(cboTrangThai);

        add(pnlInput, BorderLayout.NORTH);

        // Bảng hiển thị 
        model = new DefaultTableModel();
        model.addColumn("Mã Phòng");
        model.addColumn("Số Phòng");
        model.addColumn("Mã Loại");
        model.addColumn("Trạng Thái");
        
        tblPhong = new JTable(model);
        add(new JScrollPane(tblPhong), BorderLayout.CENTER);

        // Nút bấm
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa bỏ");
        btnTimKiem = new JButton("Tìm kiếm");

        pnlButtons.add(btnThem);
        pnlButtons.add(btnSua);
        pnlButtons.add(btnXoa);
        pnlButtons.add(btnTimKiem);
        
        add(pnlButtons, BorderLayout.SOUTH);
    }

    private void fillToTable() {
        model.setRowCount(0); 
        for (int i = 0; i < soLuongPhong; i++) {
            Phong p = dsPhong[i];

            Object[] row = {
                p.getMaPhong(), 
                p.getSoPhong(), 
                p.getMaLoai(), 
                p.getTrangThai()
            };
            model.addRow(row);
        }
    }

    // Hàm lấy dữ liệu từ giao diện 
    private Phong getFormData() {
        try {
            Phong p = new Phong();
            p.setSoPhong(txtSoPhong.getText());
            p.setMaLoai(Integer.parseInt(txtMaLoai.getText()));
            p.setTrangThai(txtTrangThai.getText());
            return p;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã loại phải là số nguyên");
            return null;
        }
    }

    public static void main(String[] args) {
        // Khởi chạy View
        java.awt.EventQueue.invokeLater(() -> {
            new PhongView().setVisible(true);
        });
    }
}
