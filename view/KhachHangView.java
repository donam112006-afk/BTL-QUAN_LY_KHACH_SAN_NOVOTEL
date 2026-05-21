package view;

import dao.KhachHangDAO;
import model.KhachHang;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class KhachHangView extends JFrame {
    // Khai báo các thành phần giao diện theo đúng quy chuẩn tiền tố của nhóm
    private JTextField txtMaKH, txtHoTen, txtSDT, txtCCCD, txtDiaChi, txtTimKiem;
    private JButton btnThem, btnSua, btnXoa, btnTimKiemMa, btnTimKiemTen, btnLamMoi;
    private JTable tblKhachHang;
    private DefaultTableModel modelTable;
    private KhachHangDAO dao = new KhachHangDAO();

    public KhachHangView() {
        setTitle("Quản Lý Khách Hàng - Novotel Hotel");
        setSize(950, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 1. Tiêu đề chính
        JLabel lblTitle = new JLabel("QUẢN LÝ KHÁCH HÀNG", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(new Color(0, 102, 204)); // Màu xanh thương hiệu

        // 2. Khu vực nhập liệu (Panel Input)
        JPanel pnInput = new JPanel(new GridLayout(3, 4, 10, 10));
        pnInput.setBorder(BorderFactory.createTitledBorder("Thông tin chi tiết khách hàng"));

        pnInput.add(new JLabel("Mã Khách Hàng:"));
        txtMaKH = new JTextField();
        pnInput.add(txtMaKH);

        pnInput.add(new JLabel("Họ Tên:"));
        txtHoTen = new JTextField();
        pnInput.add(txtHoTen);

        pnInput.add(new JLabel("Số Điện Thoại:"));
        txtSDT = new JTextField();
        pnInput.add(txtSDT);

        pnInput.add(new JLabel("Số CCCD:"));
        txtCCCD = new JTextField();
        pnInput.add(txtCCCD);

        pnInput.add(new JLabel("Địa Chỉ:"));
        txtDiaChi = new JTextField();
        pnInput.add(txtDiaChi);
        
        // Ô trống để căn đều lưới GridLayout
        pnInput.add(new JLabel(""));
        pnInput.add(new JLabel(""));

        // 3. Khu vực nút bấm điều khiển và Tìm kiếm (Panel Control)
        JPanel pnControl = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        btnThem = createStyledButton("Thêm", Color.GREEN);
        btnSua = createStyledButton("Sửa", Color.ORANGE);
        btnXoa = createStyledButton("Xóa", Color.RED);
        btnLamMoi = createStyledButton("Làm Mới", Color.CYAN);
        
        txtTimKiem = new JTextField(15);
        txtTimKiem.setPreferredSize(new Dimension(150, 30)); // Đồng bộ chiều cao ô tìm kiếm
        btnTimKiemMa = createStyledButton("Tìm theo Mã", Color.LIGHT_GRAY);
        btnTimKiemTen = createStyledButton("Tìm theo Tên", Color.LIGHT_GRAY);

        pnControl.add(btnThem);
        pnControl.add(btnSua);
        pnControl.add(btnXoa);
        pnControl.add(btnLamMoi);
        pnControl.add(new JLabel(" | Nhập từ khóa:"));
        pnControl.add(txtTimKiem);
        pnControl.add(btnTimKiemMa);
        pnControl.add(btnTimKiemTen);

        // Gom nhóm Tiêu đề, Ô nhập và Nút bấm vào vùng phía Bắc (North)
        JPanel pnNorth = new JPanel(new BorderLayout());
        pnNorth.add(lblTitle, BorderLayout.NORTH);
        pnNorth.add(pnInput, BorderLayout.CENTER);
        pnNorth.add(pnControl, BorderLayout.SOUTH);
        add(pnNorth, BorderLayout.NORTH);

        // 4. Khu vực bảng hiển thị danh sách (Panel Table)
        String[] columnNames = {"Mã KH", "Họ Tên", "Số Điện Thoại", "Số CCCD", "Địa Chỉ"};
        modelTable = new DefaultTableModel(columnNames, 0);
        tblKhachHang = new JTable(modelTable);
        add(new JScrollPane(tblKhachHang), BorderLayout.CENTER);

        // Kích hoạt sự kiện nút bấm và nạp dữ liệu từ Database lên bảng
        initEvents();
        loadDataToTable();
    }

    // Hàm tạo nút bấm có kích thước chiều cao cố định đúng 30px theo yêu cầu thống nhất nhóm
    private JButton createStyledButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(115, 30)); // CHIỀU CAO ĐÚNG ĐỒNG BỘ 30PX
        return btn;
    }

    // Đổ dữ liệu từ SQL Server lên JTable thông qua KhachHangDAO
    private void loadDataToTable() {
        modelTable.setRowCount(0);
        List<KhachHang> list = dao.LayTatCa();
        for (KhachHang kh : list) {
            modelTable.addRow(new Object[]{kh.getMaKH(), kh.getHoTen(), kh.getSdt(), kh.getCccd(), kh.getDiaChi()});
        }
    }

    // Cập nhật lại dữ liệu trên bảng sau khi thực hiện tìm kiếm
    private void updateTable(List<KhachHang> list) {
        modelTable.setRowCount(0);
        for (KhachHang kh : list) {
            modelTable.addRow(new Object[]{kh.getMaKH(), kh.getHoTen(), kh.getSdt(), kh.getCccd(), kh.getDiaChi()});
        }
    }

    private void initEvents() {
        // Sự kiện Thêm Khách Hàng
        btnThem.addActionListener(e -> {
            if (txtMaKH.getText().trim().isEmpty() || txtHoTen.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ Mã và Họ Tên khách hàng!");
                return;
            }
            KhachHang kh = new KhachHang(
                txtMaKH.getText().trim(), 
                txtHoTen.getText().trim(), 
                txtSDT.getText().trim(), 
                txtCCCD.getText().trim(), 
                txtDiaChi.getText().trim()
            );
            if (dao.Them(kh)) {
                JOptionPane.showMessageDialog(this, "Thêm mới khách hàng thành công!");
                loadDataToTable();
                btnLamMoi.doClick();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại! Trùng mã khách hàng.");
            }
        });

        // Sự kiện Sửa Khách Hàng
        btnSua.addActionListener(e -> {
            KhachHang kh = new KhachHang(
                txtMaKH.getText().trim(), 
                txtHoTen.getText().trim(), 
                txtSDT.getText().trim(), 
                txtCCCD.getText().trim(), 
                txtDiaChi.getText().trim()
            );
            if (dao.Sua(kh)) {
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin khách hàng thành công!");
                loadDataToTable();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại! Không tìm thấy mã khách hàng phù hợp.");
            }
        });

        // Sự kiện Xóa Khách Hàng
        btnXoa.addActionListener(e -> {
            int selectedRow = tblKhachHang.getSelectedRow();
            if (selectedRow != -1) {
                String maKH = tblKhachHang.getValueAt(selectedRow, 0).toString();
                int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa khách hàng " + maKH + " không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    if (dao.Xoa(maKH)) {
                        JOptionPane.showMessageDialog(this, "Đã xóa khách hàng!");
                        loadDataToTable();
                        btnLamMoi.doClick();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng khách hàng trên bảng để xóa!");
            }
        });

        // Sự kiện Làm Mới (Xóa sạch chữ trên các ô nhập liệu)
        btnLamMoi.addActionListener(e -> {
            txtMaKH.setText(""); 
            txtHoTen.setText(""); 
            txtSDT.setText(""); 
            txtCCCD.setText(""); 
            txtDiaChi.setText(""); 
            txtTimKiem.setText("");
            loadDataToTable();
        });

        // Sự kiện Tìm kiếm theo Mã Khách Hàng
        btnTimKiemMa.addActionListener(e -> {
            String keyword = txtTimKiem.getText().trim();
            List<KhachHang> list = dao.TimKiemTheoMa(keyword);
            updateTable(list);
        });

        // Sự kiện Tìm kiếm theo Tên Khách Hàng
        btnTimKiemTen.addActionListener(e -> {
            String keyword = txtTimKiem.getText().trim();
            List<KhachHang> list = dao.TimKiemTheoTen(keyword);
            updateTable(list);
        });

        // Đổ ngược dữ liệu từ dòng được chọn trong JTable lên các ô nhập liệu phía trên
        tblKhachHang.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tblKhachHang.getSelectedRow();
            if (selectedRow != -1) {
                txtMaKH.setText(tblKhachHang.getValueAt(selectedRow, 0).toString());
                txtHoTen.setText(tblKhachHang.getValueAt(selectedRow, 1).toString());
                txtSDT.setText(tblKhachHang.getValueAt(selectedRow, 2) != null ? tblKhachHang.getValueAt(selectedRow, 2).toString() : "");
                txtCCCD.setText(tblKhachHang.getValueAt(selectedRow, 3) != null ? tblKhachHang.getValueAt(selectedRow, 3).toString() : "");
                txtDiaChi.setText(tblKhachHang.getValueAt(selectedRow, 4) != null ? tblKhachHang.getValueAt(selectedRow, 4).toString() : "");
            }
        });
    }

    public static void main(String[] args) {
        // Chạy thử giao diện Khách Hàng độc lập
        SwingUtilities.invokeLater(() -> new KhachHangView().setVisible(true));
    }
}