package view;

import dao.KhachHangDAO;
import model.KhachHang;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class KhachHangView extends JFrame {
    private JTextField txtMaKH, txtHoTen, txtSDT, txtDiaChi, txtCCCD, txtTimKiem;
    private JComboBox<String> cbLoaiTimKiem;
    private JTable table;
    private DefaultTableModel tableModel;
    private KhachHangDAO khDAO = new KhachHangDAO();

    public KhachHangView() {
        setTitle("Quản Lý Khách Hàng - Novotel Hotel");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        // Layout Ô nhập liệu
        JPanel pnlInput = new JPanel(new GridLayout(3, 4, 10, 10));
        pnlInput.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
        txtMaKH = new JTextField(); txtHoTen = new JTextField(); txtSDT = new JTextField();
        txtDiaChi = new JTextField(); txtCCCD = new JTextField();

        pnlInput.add(new JLabel(" Mã Khách Hàng:")); pnlInput.add(txtMaKH);
        pnlInput.add(new JLabel(" Họ Tên:")); pnlInput.add(txtHoTen);
        pnlInput.add(new JLabel(" Số Điện Thoại:")); pnlInput.add(txtSDT);
        pnlInput.add(new JLabel(" Số CCCD:")); pnlInput.add(txtCCCD);
        pnlInput.add(new JLabel(" Địa Chỉ:")); pnlInput.add(txtDiaChi);

        // Thanh công cụ hành động và tìm kiếm
        JPanel pnlAction = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton btnThem = new JButton("Thêm");
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");
        JButton btnLamMoi = new JButton("Làm mới");
        
        cbLoaiTimKiem = new JComboBox<>(new String[]{"Tìm theo Mã", "Tìm theo Tên"});
        txtTimKiem = new JTextField(12);
        JButton btnTim = new JButton("Tìm kiếm");

        pnlAction.add(btnThem); pnlAction.add(btnSua); pnlAction.add(btnXoa); pnlAction.add(btnLamMoi);
        pnlAction.add(new JLabel(" | ")); pnlAction.add(cbLoaiTimKiem); pnlAction.add(txtTimKiem); pnlAction.add(btnTim);

        JPanel pnlTop = new JPanel(new BorderLayout());
        pnlTop.add(pnlInput, BorderLayout.CENTER);
        pnlTop.add(pnlAction, BorderLayout.SOUTH);
        add(pnlTop, BorderLayout.NORTH);

        // Bảng dữ liệu
        String[] cols = {"Mã KH", "Họ Tên", "Số Điện Thoại", "Địa Chỉ", "Số CCCD"};
        tableModel = new DefaultTableModel(cols, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Lắng nghe sự kiện click chọn dòng trong bảng
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                txtMaKH.setText(tableModel.getValueAt(row, 0).toString());
                txtHoTen.setText(tableModel.getValueAt(row, 1).toString());
                txtSDT.setText(tableModel.getValueAt(row, 2).toString());
                txtDiaChi.setText(tableModel.getValueAt(row, 3).toString());
                txtCCCD.setText(tableModel.getValueAt(row, 4).toString());
                txtMaKH.setEditable(false);
            }
        });

        // Xử lý nút chức năng nghiệp vụ
        btnThem.addActionListener(e -> {
            KhachHang kh = new KhachHang(txtMaKH.getText().trim(), txtHoTen.getText().trim(), txtSDT.getText().trim(), txtDiaChi.getText().trim(), txtCCCD.getText().trim());
            if (khDAO.Them(kh)) { HienThiDuLieu(); LamMoiForm(); }
            else { JOptionPane.showMessageDialog(this, "Thêm thất bại!"); }
        });

        btnSua.addActionListener(e -> {
            KhachHang kh = new KhachHang(txtMaKH.getText().trim(), txtHoTen.getText().trim(), txtSDT.getText().trim(), txtDiaChi.getText().trim(), txtCCCD.getText().trim());
            if (khDAO.Sua(kh)) { HienThiDuLieu(); LamMoiForm(); }
            else { JOptionPane.showMessageDialog(this, "Sửa thất bại!"); }
        });

        btnXoa.addActionListener(e -> {
            if (khDAO.Xoa(txtMaKH.getText().trim())) { HienThiDuLieu(); LamMoiForm(); }
            else { JOptionPane.showMessageDialog(this, "Xóa thất bại!"); }
        });

        btnLamMoi.addActionListener(e -> { LamMoiForm(); HienThiDuLieu(); });
        
        btnTim.addActionListener(e -> ThucHienTimKiem());

        HienThiDuLieu();
    }

    private void HienThiDuLieu() {
        tableModel.setRowCount(0);
        List<KhachHang> list = khDAO.LayTatCa();
        for (KhachHang kh : list) {
            tableModel.addRow(new Object[]{kh.getMaKH(), kh.getHoTen(), kh.getSdt(), kh.getDiaChi(), kh.getSoCCCD()});
        }
    }

    private void ThucHienTimKiem() {
        String tuKhoa = txtTimKiem.getText().trim();
        String loaiTim = cbLoaiTimKiem.getSelectedItem().toString();
        List<KhachHang> list;
        
        if (loaiTim.equals("Tìm theo Mã")) {
            list = khDAO.TimKiemTheoMa(tuKhoa);
        } else {
            list = khDAO.TimKiemTheoTen(tuKhoa);
        }
        
        tableModel.setRowCount(0);
        for (KhachHang kh : list) {
            tableModel.addRow(new Object[]{kh.getMaKH(), kh.getHoTen(), kh.getSdt(), kh.getDiaChi(), kh.getSoCCCD()});
        }
    }

    private void LamMoiForm() {
        txtMaKH.setText(""); txtHoTen.setText(""); txtSDT.setText(""); txtDiaChi.setText(""); txtCCCD.setText(""); txtTimKiem.setText("");
        txtMaKH.setEditable(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new KhachHangView().setVisible(true));
    }
}