package view;

import dao.DatPhongDAO;
import model.DatPhong;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;

public class DatPhongView extends JPanel {
    private JTextField txtMaDat, txtMaKH, txtMaNV, txtNgayCheckIn, txtNgayCheckOut;
    private JButton btnThem, btnSua, btnXoa, btnTimKiem, btnLamMoi;
    private JTable tblDatPhong;
    private DefaultTableModel tableModel;
    private DatPhongDAO dao = new DatPhongDAO();

    public DatPhongView() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel pnlForm = new JPanel(new GridLayout(5, 2, 10, 10));
        txtMaDat = new JTextField(); txtMaKH = new JTextField(); txtMaNV = new JTextField();
        txtNgayCheckIn = new JTextField(); txtNgayCheckOut = new JTextField();

        pnlForm.add(new JLabel("Mã Đặt (Số nguyên):")); pnlForm.add(txtMaDat);
        pnlForm.add(new JLabel("Mã Khách Hàng:")); pnlForm.add(txtMaKH);
        pnlForm.add(new JLabel("Mã Nhân Viên:")); pnlForm.add(txtMaNV);
        pnlForm.add(new JLabel("Ngày Check-In (YYYY-MM-DD):")); pnlForm.add(txtNgayCheckIn);
        pnlForm.add(new JLabel("Ngày Check-Out (YYYY-MM-DD):")); pnlForm.add(txtNgayCheckOut);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnThem = taoNut30px("Thêm"); btnSua = taoNut30px("Sửa");
        btnXoa = taoNut30px("Xóa"); btnTimKiem = taoNut30px("Tìm Kiếm"); btnLamMoi = taoNut30px("Làm Mới");
        pnlButtons.add(btnThem); pnlButtons.add(btnSua); pnlButtons.add(btnXoa); pnlButtons.add(btnTimKiem); pnlButtons.add(btnLamMoi);

        JPanel pnlTop = new JPanel(new BorderLayout(10, 10));
        pnlTop.add(pnlForm, BorderLayout.CENTER); pnlTop.add(pnlButtons, BorderLayout.SOUTH);

        String[] cols = {"Mã Đặt", "Mã KH", "Mã NV", "Check-In", "Check-Out"};
        tableModel = new DefaultTableModel(cols, 0);
        tblDatPhong = new JTable(tableModel);

        add(pnlTop, BorderLayout.NORTH); add(new JScrollPane(tblDatPhong), BorderLayout.CENTER);

        initEvents(); loadDataToTable();
    }

    private JButton taoNut30px(String tenNut) { // Nút cao 30px[cite: 2]
        JButton btn = new JButton(tenNut);
        btn.setPreferredSize(new Dimension(100, 30));
        return btn;
    }

    private void loadDataToTable() {
        tableModel.setRowCount(0);
        for (DatPhong dp : dao.LayTatCa()) tableModel.addRow(new Object[]{dp.getMaDat(), dp.getMaKH(), dp.getMaNV(), dp.getNgayCheckIn(), dp.getNgayCheckOut()});
    }

    private void initEvents() {
        tblDatPhong.getSelectionModel().addListSelectionListener(e -> {
            int row = tblDatPhong.getSelectedRow();
            if (row >= 0) {
                txtMaDat.setText(tblDatPhong.getValueAt(row, 0).toString());
                txtMaKH.setText(tblDatPhong.getValueAt(row, 1).toString());
                txtMaNV.setText(tblDatPhong.getValueAt(row, 2).toString());
                txtNgayCheckIn.setText(tblDatPhong.getValueAt(row, 3).toString());
                txtNgayCheckOut.setText(tblDatPhong.getValueAt(row, 4).toString());
                txtMaDat.setEditable(false);
            }
        });

        btnThem.addActionListener(e -> {
            try {
                DatPhong dp = new DatPhong(Integer.parseInt(txtMaDat.getText()), txtMaKH.getText(), txtMaNV.getText(), Date.valueOf(txtNgayCheckIn.getText()), Date.valueOf(txtNgayCheckOut.getText()));
                if(dao.Them(dp)) { JOptionPane.showMessageDialog(this, "Thêm OK!"); loadDataToTable(); }
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Lỗi định dạng!"); }
        });

        btnSua.addActionListener(e -> {
            try {
                DatPhong dp = new DatPhong(Integer.parseInt(txtMaDat.getText()), txtMaKH.getText(), txtMaNV.getText(), Date.valueOf(txtNgayCheckIn.getText()), Date.valueOf(txtNgayCheckOut.getText()));
                if(dao.Sua(dp)) { JOptionPane.showMessageDialog(this, "Sửa OK!"); loadDataToTable(); }
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Lỗi định dạng!"); }
        });

        btnXoa.addActionListener(e -> {
            try {
                if(dao.Xoa(Integer.parseInt(txtMaDat.getText()))) { JOptionPane.showMessageDialog(this, "Xóa OK!"); btnLamMoi.doClick(); }
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Mã đặt phải là số nguyên!"); }
        });

       btnTimKiem.addActionListener(e -> {
            String tuKhoa = JOptionPane.showInputDialog(this, "Nhập Mã Đặt hoặc Mã Khách Hàng cần tìm:");
            if (tuKhoa != null && !tuKhoa.trim().isEmpty()) {
                tableModel.setRowCount(0);
                for (DatPhong dp : dao.TimKiem(tuKhoa.trim())) {
                    tableModel.addRow(new Object[]{dp.getMaDat(), dp.getMaKH(), dp.getMaNV(), dp.getNgayCheckIn(), dp.getNgayCheckOut()});
                }
            } else if (tuKhoa != null && tuKhoa.trim().isEmpty()) {
                loadDataToTable(); // Trống thì hiển thị lại tất cả
            }
        });

        btnLamMoi.addActionListener(e -> {
            txtMaDat.setText(""); txtMaKH.setText(""); txtMaNV.setText(""); txtNgayCheckIn.setText(""); txtNgayCheckOut.setText("");
            txtMaDat.setEditable(true); loadDataToTable();
        });
    }
}