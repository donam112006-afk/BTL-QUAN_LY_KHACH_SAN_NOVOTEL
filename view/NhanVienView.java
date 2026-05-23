package view;

import dao.NhanVienDAO;
import model.NhanVien;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class NhanVienView extends JFrame {
    private JTextField txtMaNV, txtHoTen, txtSDT, txtChucVu, txtNgaySinh, txtTimKiem;
    private JComboBox<String> cbGioiTinh, cbLoaiTimKiem;
    private JTable table;
    private DefaultTableModel tableModel;
    private NhanVienDAO nvDAO = new NhanVienDAO();
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public NhanVienView() {
        setTitle("Quản Lý Nhân Viên - Novotel Hotel");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        // --- PANEL NHẬP LIỆU ---
        JPanel pnlInput = new JPanel(new GridLayout(3, 4, 10, 10));
        pnlInput.setBorder(BorderFactory.createTitledBorder("Thông tin chi tiết nhân viên"));
        txtMaNV = new JTextField(); txtHoTen = new JTextField(); txtSDT = new JTextField();
        txtChucVu = new JTextField(); txtNgaySinh = new JTextField();
        cbGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ", "Khác"});

        pnlInput.add(new JLabel("  Mã Nhân Viên:")); pnlInput.add(txtMaNV);
        pnlInput.add(new JLabel("  Họ Tên:")); pnlInput.add(txtHoTen);
        pnlInput.add(new JLabel("  Số Điện Thoại:")); pnlInput.add(txtSDT);
        pnlInput.add(new JLabel("  Giới Tính:")); pnlInput.add(cbGioiTinh);
        pnlInput.add(new JLabel("  Chức Vụ:")); pnlInput.add(txtChucVu);
        pnlInput.add(new JLabel("  Ngày Sinh (yyyy-MM-dd):")); pnlInput.add(txtNgaySinh);

        // --- PANEL CHỨC NĂNG & TÌM KIẾM ---
        JPanel pnlAction = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton btnThem = new JButton("Thêm mới");
        JButton btnSua = new JButton("Sửa thông tin");
        JButton btnXoa = new JButton("Xóa nhân viên");
        JButton btnLamMoi = new JButton("Làm mới");
        
        cbLoaiTimKiem = new JComboBox<>(new String[]{"Tìm theo Mã", "Tìm theo Tên"});
        txtTimKiem = new JTextField(15);
        JButton btnTim = new JButton("Tìm kiếm");

        pnlAction.add(btnThem); pnlAction.add(btnSua); pnlAction.add(btnXoa); pnlAction.add(btnLamMoi);
        pnlAction.add(new JLabel(" | ")); pnlAction.add(cbLoaiTimKiem); pnlAction.add(txtTimKiem); pnlAction.add(btnTim);

        JPanel pnlTop = new JPanel(new BorderLayout());
        pnlTop.add(pnlInput, BorderLayout.CENTER);
        pnlTop.add(pnlAction, BorderLayout.SOUTH);
        add(pnlTop, BorderLayout.NORTH);

        // --- BẢNG HIỂN THỊ DỮ LIỆU ---
        String[] cols = {"Mã NV", "Họ Tên", "Số Điện Thoại", "Giới Tính", "Chức Vụ", "Ngày Sinh"};
        tableModel = new DefaultTableModel(cols, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // --- SỰ KIỆN CLICK BẢNG ---
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                txtMaNV.setText(tableModel.getValueAt(row, 0).toString());
                txtHoTen.setText(tableModel.getValueAt(row, 1).toString());
                txtSDT.setText(tableModel.getValueAt(row, 2) != null ? tableModel.getValueAt(row, 2).toString() : "");
                cbGioiTinh.setSelectedItem(tableModel.getValueAt(row, 3).toString());
                txtChucVu.setText(tableModel.getValueAt(row, 4) != null ? tableModel.getValueAt(row, 4).toString() : "");
                txtNgaySinh.setText(tableModel.getValueAt(row, 5) != null ? tableModel.getValueAt(row, 5).toString() : "");
                txtMaNV.setEditable(false); // Khóa không cho sửa khóa chính
            }
        });

        // --- XỬ LÝ SỰ KIỆN NÚT ---
        btnThem.addActionListener(e -> {
            try {
                NhanVien nv = new NhanVien(
                    txtMaNV.getText().trim(),
                    txtHoTen.getText().trim(),
                    txtSDT.getText().trim(),
                    cbGioiTinh.getSelectedItem().toString(),
                    txtChucVu.getText().trim(),
                    txtNgaySinh.getText().trim().isEmpty() ? null : df.parse(txtNgaySinh.getText().trim())
                );
                if (nvDAO.Them(nv)) {
                    HienThiDuLieu(); LamMoiForm();
                    JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại! Trùng mã nhân viên.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ngày sinh nhập sai định dạng yyyy-MM-dd!");
            }
        });

        btnSua.addActionListener(e -> {
            try {
                NhanVien nv = new NhanVien(
                    txtMaNV.getText().trim(),
                    txtHoTen.getText().trim(),
                    txtSDT.getText().trim(),
                    cbGioiTinh.getSelectedItem().toString(),
                    txtChucVu.getText().trim(),
                    txtNgaySinh.getText().trim().isEmpty() ? null : df.parse(txtNgaySinh.getText().trim())
                );
                if (nvDAO.Sua(nv)) {
                    HienThiDuLieu(); LamMoiForm();
                    JOptionPane.showMessageDialog(this, "Cập nhật dữ liệu thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ngày sinh nhập sai định dạng yyyy-MM-dd!");
            }
        });

        btnXoa.addActionListener(e -> {
            String maNV = txtMaNV.getText().trim();
            if (maNV.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng lựa chọn nhân viên cần xóa từ bảng!");
                return;
            }
            int opt = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa nhân viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                if (nvDAO.Xoa(maNV)) {
                    HienThiDuLieu(); LamMoiForm();
                    JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại! Nhân viên này có liên kết với tài khoản hoặc hóa đơn.");
                }
            }
        });

        btnLamMoi.addActionListener(e -> { LamMoiForm(); HienThiDuLieu(); });
        
        btnTim.addActionListener(e -> ThucHienTimKiem());

        // Load dữ liệu lên lúc mở giao diện
        HienThiDuLieu();
    }

    private void HienThiDuLieu() {
        tableModel.setRowCount(0);
        List<NhanVien> list = nvDAO.LayTatCa();
        for (NhanVien nv : list) {
            tableModel.addRow(new Object[]{
                nv.getMaNV(), nv.getHoTen(), nv.getSdt(),
                nv.getGioiTinh(), nv.getChucVu(),
                nv.getNgaySinh() != null ? df.format(nv.getNgaySinh()) : ""
            });
        }
    }

    private void ThucHienTimKiem() {
        String tuKhoa = txtTimKiem.getText().trim();
        String loaiTim = cbLoaiTimKiem.getSelectedItem().toString();
        List<NhanVien> list;
        
        if (loaiTim.equals("Tìm theo Mã")) {
            list = nvDAO.TimKiemTheoMa(tuKhoa);
        } else {
            list = nvDAO.TimKiemTheoTen(tuKhoa);
        }
        
        tableModel.setRowCount(0);
        for (NhanVien nv : list) {
            tableModel.addRow(new Object[]{
                nv.getMaNV(), nv.getHoTen(), nv.getSdt(),
                nv.getGioiTinh(), nv.getChucVu(),
                nv.getNgaySinh() != null ? df.format(nv.getNgaySinh()) : ""
            });
        }
    }

    private void LamMoiForm() {
        txtMaNV.setText(""); txtHoTen.setText(""); txtSDT.setText(""); 
        txtChucVu.setText(""); txtNgaySinh.setText(""); txtTimKiem.setText("");
        cbGioiTinh.setSelectedIndex(0);
        txtMaNV.setEditable(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NhanVienView().setVisible(true));
    }
}