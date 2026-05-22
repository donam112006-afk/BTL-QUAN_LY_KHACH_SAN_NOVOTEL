package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import model.DatPhong;
import dao.DatPhongDAO;

public class DatPhongView extends JPanel {
    // 1. Khai báo các thành phần giao diện
    private JTextField txtMaDat, txtMaKH, txtMaNV, txtNgayCheckIn, txtNgayCheckOut;
    private JButton btnThem, btnSua, btnXoa, btnTimKiem, btnLamMoi;
    private JTable tblDatPhong;
    private DefaultTableModel tableModel;
    
    // 2. Kết nối với tầng dữ liệu
    private DatPhongDAO dao = new DatPhongDAO();

    public DatPhongView() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initComponents();   
        initEvents();       
        loadDataToTable();  
    }

    private void initComponents() {
        JPanel pnlTop = new JPanel(new BorderLayout(10, 10));
        
        // Form nhập liệu: 5 hàng, 2 cột
        JPanel pnlForm = new JPanel(new GridLayout(5, 2, 10, 10));
        
        txtMaDat = new JTextField();
        txtMaDat.setEditable(false); // KHÓA Ô MÃ ĐẶT PHÒNG LẠI 🔒

        txtMaKH = new JTextField();
        txtMaNV = new JTextField();
        txtNgayCheckIn = new JTextField();
        txtNgayCheckOut = new JTextField();

        pnlForm.add(new JLabel("Mã đặt phòng (Tự động):")); pnlForm.add(txtMaDat);
        pnlForm.add(new JLabel("Mã khách hàng:")); pnlForm.add(txtMaKH);
        pnlForm.add(new JLabel("Mã nhân viên:")); pnlForm.add(txtMaNV);
        pnlForm.add(new JLabel("Ngày Check-in (YYYY-MM-DD):")); pnlForm.add(txtNgayCheckIn);
        pnlForm.add(new JLabel("Ngày Check-out (YYYY-MM-DD):")); pnlForm.add(txtNgayCheckOut);

        // Khu vực Nút bấm
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnThem = taoNutChuan("Thêm");
        btnSua = taoNutChuan("Sửa");
        btnXoa = taoNutChuan("Xóa");
        btnTimKiem = taoNutChuan("Tìm kiếm (Mã KH)");
        btnLamMoi = taoNutChuan("Làm mới");

        pnlButtons.add(btnThem);
        pnlButtons.add(btnSua);
        pnlButtons.add(btnXoa);
        pnlButtons.add(btnTimKiem);
        pnlButtons.add(btnLamMoi);

        pnlTop.add(pnlForm, BorderLayout.CENTER);
        pnlTop.add(pnlButtons, BorderLayout.SOUTH);

        // Khu vực Bảng hiển thị
        String[] columns = {"Mã Đặt", "Mã KH", "Mã NV", "Ngày Check-in", "Ngày Check-out"};
        tableModel = new DefaultTableModel(columns, 0);
        tblDatPhong = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblDatPhong);

        this.add(pnlTop, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private JButton taoNutChuan(String text) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(130, 30)); // Cố định cao 30px
        return btn;
    }

    public void loadDataToTable() {
        tableModel.setRowCount(0);
        List<DatPhong> ds = dao.LayTatCa();
        for (DatPhong dp : ds) {
            tableModel.addRow(new Object[]{
                dp.getMaDat(), dp.getMaKH(), dp.getMaNV(), dp.getNgayCheckIn(), dp.getNgayCheckOut()
            });
        }
    }

    private void initEvents() {
        // Click vào bảng -> đẩy lên Form
        tblDatPhong.getSelectionModel().addListSelectionListener(e -> {
            int row = tblDatPhong.getSelectedRow();
            if (row >= 0) {
                txtMaDat.setText(tblDatPhong.getValueAt(row, 0).toString());
                txtMaKH.setText(tblDatPhong.getValueAt(row, 1).toString());
                txtMaNV.setText(tblDatPhong.getValueAt(row, 2).toString());
                txtNgayCheckIn.setText(tblDatPhong.getValueAt(row, 3).toString());
                txtNgayCheckOut.setText(tblDatPhong.getValueAt(row, 4).toString());
            }
        });

        // Nút Thêm (Truyền 0 vào mã đặt vì CSDL tự tăng)
        btnThem.addActionListener(e -> {
            try {
                DatPhong dp = new DatPhong(0, txtMaKH.getText(), txtMaNV.getText(), 
                                           txtNgayCheckIn.getText(), txtNgayCheckOut.getText());
                if (dao.Them(dp)) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
                    loadDataToTable();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi dữ liệu: " + ex.getMessage());
            }
        });

        // Nút Sửa
        btnSua.addActionListener(e -> {
            try {
                if (txtMaDat.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Chọn dòng để sửa!"); return;
                }
                int maDat = Integer.parseInt(txtMaDat.getText());
                DatPhong dp = new DatPhong(maDat, txtMaKH.getText(), txtMaNV.getText(), 
                                           txtNgayCheckIn.getText(), txtNgayCheckOut.getText());
                if (dao.CapNhat(dp)) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                    loadDataToTable();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi cập nhật!");
            }
        });

        // Nút Xóa
        btnXoa.addActionListener(e -> {
            if (txtMaDat.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Chọn dòng để xóa!"); return;
            }
            int maDat = Integer.parseInt(txtMaDat.getText());
            if (dao.Xoa(maDat)) {
                JOptionPane.showMessageDialog(this, "Đã xóa!");
                loadDataToTable();
                btnLamMoi.doClick();
            }
        });

        // Nút Tìm kiếm (theo Mã KH)
        btnTimKiem.addActionListener(e -> {
            String tuKhoa = txtMaKH.getText();
            List<DatPhong> ds = dao.TimKiem(tuKhoa);
            tableModel.setRowCount(0);
            for (DatPhong dp : ds) {
                tableModel.addRow(new Object[]{
                    dp.getMaDat(), dp.getMaKH(), dp.getMaNV(), dp.getNgayCheckIn(), dp.getNgayCheckOut()
                });
            }
        });

        // Nút Làm mới
        btnLamMoi.addActionListener(e -> {
            txtMaDat.setText(""); txtMaKH.setText(""); txtMaNV.setText("");
            txtNgayCheckIn.setText(""); txtNgayCheckOut.setText("");
            loadDataToTable();
        });
    }
}