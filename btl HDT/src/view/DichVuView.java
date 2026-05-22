package view;

import dao.DichVuDAO;
import model.DichVu;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DichVuView extends JPanel {
    // Đặt tên biến giao diện cực chuẩn theo yêu cầu[cite: 2]
    private JTextField txtMaDV, txtTenDV, txtGiaDV, txtDonViTinh;
    private JButton btnThem, btnSua, btnXoa, btnTimKiem, btnLamMoi;
    private JTable tblDichVu;
    private DefaultTableModel tableModel;
    private DichVuDAO dao = new DichVuDAO();

    public DichVuView() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel pnlForm = new JPanel(new GridLayout(4, 2, 10, 10));
        txtMaDV = new JTextField(); txtTenDV = new JTextField();
        txtGiaDV = new JTextField(); txtDonViTinh = new JTextField();

        pnlForm.add(new JLabel("Mã DV:")); pnlForm.add(txtMaDV);
        pnlForm.add(new JLabel("Tên DV:")); pnlForm.add(txtTenDV);
        pnlForm.add(new JLabel("Giá DV (Số nguyên):")); pnlForm.add(txtGiaDV);
        pnlForm.add(new JLabel("Đơn vị tính:")); pnlForm.add(txtDonViTinh);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnThem = taoNut30px("Thêm"); btnSua = taoNut30px("Sửa");
        btnXoa = taoNut30px("Xóa"); btnTimKiem = taoNut30px("Tìm Kiếm"); btnLamMoi = taoNut30px("Làm Mới");
        pnlButtons.add(btnThem); pnlButtons.add(btnSua); pnlButtons.add(btnXoa); pnlButtons.add(btnTimKiem); pnlButtons.add(btnLamMoi);

        JPanel pnlTop = new JPanel(new BorderLayout(10, 10));
        pnlTop.add(pnlForm, BorderLayout.CENTER); pnlTop.add(pnlButtons, BorderLayout.SOUTH);

        String[] cols = {"Mã DV", "Tên DV", "Giá DV", "ĐVT"};
        tableModel = new DefaultTableModel(cols, 0);
        tblDichVu = new JTable(tableModel);

        add(pnlTop, BorderLayout.NORTH); add(new JScrollPane(tblDichVu), BorderLayout.CENTER);

        initEvents(); loadDataToTable();
    }

    // Đảm bảo chiều cao nút bấm cố định 30px theo tài liệu[cite: 2]
    private JButton taoNut30px(String tenNut) {
        JButton btn = new JButton(tenNut);
        btn.setPreferredSize(new Dimension(100, 30));
        return btn;
    }

    private void loadDataToTable() {
        tableModel.setRowCount(0);
        for (DichVu dv : dao.LayTatCa()) tableModel.addRow(new Object[]{dv.getMaDV(), dv.getTenDV(), dv.getGiaDV(), dv.getDonViTinh()});
    }

    private void initEvents() {
        tblDichVu.getSelectionModel().addListSelectionListener(e -> {
            int row = tblDichVu.getSelectedRow();
            if (row >= 0) {
                txtMaDV.setText(tblDichVu.getValueAt(row, 0).toString());
                txtTenDV.setText(tblDichVu.getValueAt(row, 1).toString());
                txtGiaDV.setText(tblDichVu.getValueAt(row, 2).toString());
                txtDonViTinh.setText(tblDichVu.getValueAt(row, 3).toString());
                txtMaDV.setEditable(false);
            }
        });

        btnThem.addActionListener(e -> {
            try {
                DichVu dv = new DichVu(txtMaDV.getText(), txtTenDV.getText(), Integer.parseInt(txtGiaDV.getText()), txtDonViTinh.getText());
                if(dao.Them(dv)) { JOptionPane.showMessageDialog(this, "Thêm OK!"); loadDataToTable(); }
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Giá phải là số nguyên!"); }
        });

        btnSua.addActionListener(e -> {
            try {
                DichVu dv = new DichVu(txtMaDV.getText(), txtTenDV.getText(), Integer.parseInt(txtGiaDV.getText()), txtDonViTinh.getText());
                if(dao.Sua(dv)) { JOptionPane.showMessageDialog(this, "Sửa OK!"); loadDataToTable(); }
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Giá phải là số nguyên!"); }
        });

        btnXoa.addActionListener(e -> {
            if(dao.Xoa(txtMaDV.getText())) { JOptionPane.showMessageDialog(this, "Xóa OK!"); btnLamMoi.doClick(); }
        });

        btnTimKiem.addActionListener(e -> {
            // Hiện hộp thoại yêu cầu nhập từ khóa
            String tuKhoa = JOptionPane.showInputDialog(this, "Nhập Mã hoặc Tên Dịch Vụ cần tìm:");
            if (tuKhoa != null && !tuKhoa.trim().isEmpty()) { // Kiểm tra nếu người dùng không bấm Cancel
                tableModel.setRowCount(0);
                for (DichVu dv : dao.TimKiem(tuKhoa.trim())) {
                    tableModel.addRow(new Object[]{dv.getMaDV(), dv.getTenDV(), dv.getGiaDV(), dv.getDonViTinh()});
                }
            } else if (tuKhoa != null && tuKhoa.trim().isEmpty()) {
                loadDataToTable(); // Nếu để trống và bấm OK thì tải lại toàn bộ
            }
        });

        btnLamMoi.addActionListener(e -> {
            txtMaDV.setText(""); txtTenDV.setText(""); txtGiaDV.setText(""); txtDonViTinh.setText("");
            txtMaDV.setEditable(true); loadDataToTable();
        });
    }
}