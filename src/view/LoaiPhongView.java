package view;

import DAO.LoaiPhongDAO;
import model.LoaiPhong;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class LoaiPhongView extends JFrame {

    private JTextField txtMaLoai, txtTenLoai, txtDonGia, txtsoNguoiToiDa;
    private JTable tblLoaiPhong;
    private DefaultTableModel model;
    private JButton btnThem, btnSua, btnXoa, btnTimKiem;

    private LoaiPhong[] danhSachLoaiPhong = new LoaiPhong[100];
    private int soLuongThucTe = 0;

    public LoaiPhongView() {
        setTitle("Quản Lý Loại Phòng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Thiết kế khu vực nhập
        JPanel pnlInput = new JPanel(new GridLayout(3, 2, 10, 10));
        pnlInput.setBorder(BorderFactory.createTitledBorder("Thông tin loại phòng"));
        
        pnlInput.add(new JLabel("Mã loại:"));
        txtMaLoai = new JTextField();
        txtMaLoai.setEditable(false);
        pnlInput.add(txtMaLoai);

        pnlInput.add(new JLabel("Tên loại:"));
        txtTenLoai = new JTextField();
        pnlInput.add(txtTenLoai);

        pnlInput.add(new JLabel("Giá theo ngày:"));
        txtDonGia = new JTextField();
        pnlInput.add(txtDonGia);

      
        pnlInput.add(new JLabel("Số người tối đa:"));
        txtsoNguoiToiDa = new JTextField();
        pnlInput.add(txtsoNguoiToiDa);
        add(pnlInput, BorderLayout.NORTH);

        // Thiết kế bảng hiển thị
        model = new DefaultTableModel();
        model.addColumn("Mã Loại");
        model.addColumn("Tên Loại");
        model.addColumn("Giá Theo Ngày");
        model.addColumn("Số Người Tối Đa");
        tblLoaiPhong = new JTable(model);
        add(new JScrollPane(tblLoaiPhong), BorderLayout.CENTER);

        // Thiết kế khu vực nút bấm
        JPanel pnlButtons = new JPanel(new FlowLayout());
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnTimKiem = new JButton("Tìm kiếm");
        
        pnlButtons.add(btnThem);
        pnlButtons.add(btnSua);
        pnlButtons.add(btnXoa);
        pnlButtons.add(btnTimKiem);
        add(pnlButtons, BorderLayout.SOUTH);

        // Nạp dữ liệu ban đầu
        loadDataFromDAO();
        fillToTable();
    }


    private void loadDataFromDAO() {
        LoaiPhongDAO dao = new LoaiPhongDAO();
        soLuongThucTe = 0; 
    }


    private void fillToTable() {
        model.setRowCount(0); 
        for (int i = 0; i < soLuongThucTe; i++) {
            LoaiPhong lp = danhSachLoaiPhong[i];
            Object[] row = {
                lp.getMaLoai(), 
                lp.getTenLoai(), 
                lp.getGiaTheoNgay(),
                lp.getSoNguoiToiDa()
            };
            model.addRow(row);
        }
    }

    
    private LoaiPhong getModelFromInput() {
        try {
            String ten = txtTenLoai.getText();
            double gia = Double.parseDouble(txtDonGia.getText());
            
            LoaiPhong lp = new LoaiPhong();
            lp.setTenLoai(ten);
            lp.setGiaTheoNgay(gia);
            if (!txtMaLoai.getText().isEmpty()) {
                lp.setMaLoai(Integer.parseInt(txtMaLoai.getText()));
            }
            return lp;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ");
            return null;
        }
    }

    public static void main(String[] args) {
        new LoaiPhongView().setVisible(true);
    }
}