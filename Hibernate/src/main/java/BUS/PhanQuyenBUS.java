package BUS;

import DAO.PhanQuyenDAO;
import DTO.PhanQuyen;
import MyCustom.MyDialog;

import java.util.ArrayList;

import javax.swing.UnsupportedLookAndFeelException;

public class PhanQuyenBUS {

    public static PhanQuyen quyenTK = null;
    private PhanQuyenDAO phanQuyenDAO = new PhanQuyenDAO();
    private ArrayList<PhanQuyen> listPhanQuyen = null;

    public void docDanhSachQuyen() {
        this.listPhanQuyen = phanQuyenDAO.getListQuyen();
    }

    public void kiemTraQuyen(String quyen) {
        quyenTK = phanQuyenDAO.getQuyen(quyen);
    }

    public ArrayList<PhanQuyen> getListQuyen() {
        if (listPhanQuyen == null)
            docDanhSachQuyen();
        return this.listPhanQuyen;
    }

    public boolean suaQuyen(String tenQuyen, int nhapHang, int sanPham, int nhanVien, int khachHang, int thongKe) throws InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        PhanQuyen phanQuyen = new PhanQuyen(tenQuyen, nhapHang, sanPham, nhanVien, khachHang, thongKe);
        boolean flag = phanQuyenDAO.suaQuyen(phanQuyen);
        if (flag) {
            new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public boolean themQuyen(String tenQuyen) throws InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        if (tenQuyen == null || tenQuyen.trim().equals("")) {
            return false;
        }

        if (kiemTonTaiTraQuyen(tenQuyen)) {
            new MyDialog("Thêm thất bại! Quy�?n đã tồn tại", MyDialog.ERROR_DIALOG);
            return false;
        }

        PhanQuyen phanQuyen = new PhanQuyen(tenQuyen, 0, 0, 0, 0, 0);
        boolean flag = phanQuyenDAO.themQuyen(phanQuyen);
        if (flag) {
            new MyDialog("Thêm thành công! Hãy hiệu chỉnh quy�?n", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Thêm thất bại! Quy�?n đã tồn tại", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    private boolean kiemTonTaiTraQuyen(String tenQuyen) {
        docDanhSachQuyen();
        for (PhanQuyen q : listPhanQuyen) {
            if (q.getQuyen().equalsIgnoreCase(tenQuyen))
                return true;
        }
        return false;
    }

    public boolean xoaQuyen(String tenQuyen) throws InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        boolean flag = phanQuyenDAO.xoaQuyen(tenQuyen);
        if (flag) {
            new MyDialog("Xoá thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Xoá thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }
}
