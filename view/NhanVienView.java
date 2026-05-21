package view;

import dao.NhanVienDAO;
import model.NhanVien;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class NhanVienView extends JFrame {
    // Khai báo các thành phần giao diện theo chuẩn đặt tên nhóm
    private JTextField txtMaNV, txtHoTen, txtSDT, txtChucVu, txtTimKiem;
    private JComboBox<String> cbGioiTinh;
    private JButton btnThem, btnSua, btnXoa, btnTimKiemMa, btnTimKiemTen, btnLamMoi;
    private JTable tblNhanVien;
    private DefaultTableModel modelTable;
    private NhanVienDAO dao = new NhanVienDAO();

    public NhanVienView() {
        setTitle("Quản Lý Nhân Viên - Novotel Hotel");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 1. Tiêu đề
        JLabel lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(Color.BLUE);
        add(lblTitle, BorderLayout.NORTH);

        // 2. Khu vực nhập liệu (Panel Input)
        JPanel pnInput = new JPanel(new GridLayout(3, 4, 10, 10));
        pnInput.setBorder(BorderFactory.createTitledBorder("Thông tin chi tiết"));

        pnInput.add(new JLabel("Mã Nhân Viên:"));
        txtMaNV = new JTextField();
        pnInput.add(txtMaNV);

        pnInput.add(new JLabel("Họ Tên:"));
        txtHoTen = new JTextField();
        pnInput.add(txtHoTen);

        pnInput.add(new JLabel("Số Điện Thoại:"));
        txtSDT = new JTextField();
        pnInput.add(txtSDT);

        pnInput.add(new JLabel("Giới Tính:"));
        cbGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});
        pnInput.add(cbGioiTinh);

        pnInput.add(new JLabel("Chức Vụ:"));
        txtChucVu = new JTextField();
        pnInput.add(txtChucVu);

        add(pnInput, BorderLayout.NORTH);

        // 3. Khu vực nút bấm và tìm kiếm (Panel Control)
        JPanel pnControl = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        btnThem = createStyledButton("Thêm", Color.GREEN);
        btnSua = createStyledButton("Sửa", Color.ORANGE);
        btnXoa = createStyledButton("Xóa", Color.RED);
        btnLamMoi = createStyledButton("Làm Mới", Color.CYAN);
        
        txtTimKiem = new JTextField(15);
        txtTimKiem.setPreferredSize(new Dimension(150, 30));
        btnTimKiemMa = createStyledButton("Tìm theo Mã", Color.LIGHT_GRAY);
        btnTimKiemTen = createStyledButton("Tìm theo Tên", Color.LIGHT_GRAY);

        pnControl.add(btnThem);
        pnControl.add(btnSua);
        pnControl.add(btnXoa);
        pnControl.add(btnLamMoi);
        pnControl.add(new JLabel(" | Tìm kiếm:"));
        pnControl.add(txtTimKiem);
        pnControl.add(btnTimKiemMa);
        pnControl.add(btnTimKiemTen);

        // Gom Panel Input và Panel Control vào phía Bắc
        JPanel pnNorth = new JPanel(new BorderLayout());
        pnNorth.add(lblTitle, BorderLayout.NORTH);
        pnNorth.add(pnInput, BorderLayout.CENTER);
        pnNorth.add(pnControl, BorderLayout.SOUTH);
        add(pnNorth, BorderLayout.NORTH);

        // 4. Khu vực bảng hiển thị (Panel Table)
        String[] columnNames = {"Mã NV", "Họ Tên", "Số ĐT", "Giới Tính", "Chức Vụ"};
        modelTable = new DefaultTableModel(columnNames, 0);
        tblNhanVien = new JTable(modelTable);
        add(new JScrollPane(tblNhanVien), BorderLayout.CENTER);

        // Sự kiện
        initEvents();
        loadDataToTable();
    }

    // Hàm tạo nút bấm chuẩn cao 30px
    private JButton createStyledButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(110, 30)); // ĐÚNG YÊU CẦU CAO 30PX
        return btn;
    }

    // Đổ dữ liệu từ Database lên bảng
    private void loadDataToTable() {
        modelTable.setRowCount(0);
        List<NhanVien> list = dao.LayTatCa();
        for (NhanVien nv : list) {
            modelTable.addRow(new Object[]{nv.getMaNV(), nv.getHoTen(), nv.getSdt(), nv.getGioiTinh(), nv.getChucVu()});
        }
    }

    private void initEvents() {
        // Sự kiện Thêm
        btnThem.addActionListener(e -> {
            NhanVien nv = new NhanVien(txtMaNV.getText(), txtHoTen.getText(), txtSDT.getText(), cbGioiTinh.getSelectedItem().toString(), txtChucVu.getText());
            if (dao.Them(nv)) {
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
                loadDataToTable();
            }
        });

        // Sự kiện Xóa
        btnXoa.addActionListener(e -> {
            int row = tblNhanVien.getSelectedRow();
            if (row != -1) {
                String ma = tblNhanVien.getValueAt(row, 0).toString();
                if (dao.Xoa(ma)) {
                    JOptionPane.showMessageDialog(this, "Đã xóa!");
                    loadDataToTable();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa!");
            }
        });

        // Sự kiện Sửa
        btnSua.addActionListener(e -> {
            NhanVien nv = new NhanVien(txtMaNV.getText(), txtHoTen.getText(), txtSDT.getText(), cbGioiTinh.getSelectedItem().toString(), txtChucVu.getText());
            if (dao.Sua(nv)) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                loadDataToTable();
            }
        });

        // Sự kiện Làm mới
        btnLamMoi.addActionListener(e -> {
            txtMaNV.setText(""); txtHoTen.setText(""); txtSDT.setText(""); txtChucVu.setText(""); txtTimKiem.setText("");
            loadDataToTable();
        });

        // Tìm kiếm theo Mã
        btnTimKiemMa.addActionListener(e -> {
            List<NhanVien> list = dao.TimKiemTheoMa(txtTimKiem.getText());
            updateTable(list);
        });

        // Tìm kiếm theo Tên
        btnTimKiemTen.addActionListener(e -> {
            List<NhanVien> list = dao.TimKiemTheoTen(txtTimKiem.getText());
            updateTable(list);
        });
    }

    private void updateTable(List<NhanVien> list) {
        modelTable.setRowCount(0);
        for (NhanVien nv : list) {
            modelTable.addRow(new Object[]{nv.getMaNV(), nv.getHoTen(), nv.getSdt(), nv.getGioiTinh(), nv.getChucVu()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NhanVienView().setVisible(true));
    }
}