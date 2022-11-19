package BUS;

import DAO.GiamGiaDAO;
import DTO.GiamGia;
import MyCustom.MyDialog;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.UnsupportedLookAndFeelException;

public class GiamGiaBUS {

    private ArrayList<GiamGia> listGiamGia = null;
    private GiamGiaDAO giamGiaDAO = new GiamGiaDAO();

    public GiamGiaBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listGiamGia = giamGiaDAO.getDanhSachMaGiam();
    }

    public ArrayList<GiamGia> getDanhSachGiamGia() {
        if (this.listGiamGia == null)
            docDanhSach();
        return this.listGiamGia;
    }

    public boolean themMaGiam(String ten, String phanTram, String dieuKien, Date ngayBD, Date ngayKT) {
        ten = ten.trim();
        phanTram = phanTram.replace("%", "");
        dieuKien = dieuKien.replace(",", "");
        if (ten.equals("")) {
            try {
				new MyDialog("Hãy nhập tên chương trình khuyến mãi!", MyDialog.ERROR_DIALOG);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return false;
        }
        if (ngayBD.compareTo(ngayKT) > 0 || ngayBD.compareTo(ngayKT) == 0) {
            try {
				new MyDialog("Ngày kết thúc không hợp lệ!", MyDialog.ERROR_DIALOG);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return false;
        }
        boolean flag = false;
        try {
            int phanTramGiam = Integer.parseInt(phanTram);
            int dieuKienGiam = Integer.parseInt(dieuKien);

            GiamGia gg = new GiamGia();
            gg.setTenGiamGia(ten);
            gg.setPhanTramGiam(phanTramGiam);
            gg.setDieuKien(dieuKienGiam);
            gg.setNgayBD(ngayBD);
            gg.setNgayKT(ngayKT);

            flag = giamGiaDAO.themMaGiam(gg);
        } catch (Exception e) {
            try {
				new MyDialog("Hãy nhập số nguyên hợp lệ!", MyDialog.ERROR_DIALOG);
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            return false;
        }
        if (flag) {
            try {
				new MyDialog("Thêm mới thành công!", MyDialog.SUCCESS_DIALOG);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else {
            try {
				new MyDialog("Thêm mới thất bại!", MyDialog.ERROR_DIALOG);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return flag;
    }

    public boolean suaMaGiam(String ma, String ten, String phanTram, String dieuKien, Date ngayBD, Date ngayKT) {
        ten = ten.trim();
        phanTram = phanTram.replace("%", "");
        dieuKien = dieuKien.replace(",", "");
        if (ma.equals("")) {
            try {
				new MyDialog("Chưa ch�?n mã để sửa!", MyDialog.ERROR_DIALOG);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return false;
        }
        if (ten.equals("")) {
            try {
				new MyDialog("Hãy nhập tên chương trình khuyến mãi!", MyDialog.ERROR_DIALOG);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return false;
        }
        if (ngayBD.compareTo(ngayKT) > 0 || ngayBD.compareTo(ngayKT) == 0) {
            try {
				new MyDialog("Ngày kết thúc không hợp lệ!", MyDialog.ERROR_DIALOG);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return false;
        }
        boolean flag = false;
        try {
            int maGiam = Integer.parseInt(ma);
            int phanTramGiam = Integer.parseInt(phanTram);
            int dieuKienGiam = Integer.parseInt(dieuKien);

            GiamGia gg = new GiamGia();
            gg.setMaGiam(maGiam);
            gg.setTenGiamGia(ten);
            gg.setPhanTramGiam(phanTramGiam);
            gg.setDieuKien(dieuKienGiam);
            gg.setNgayBD(ngayBD);
            gg.setNgayKT(ngayKT);

            flag = giamGiaDAO.suaMaGiam(gg);
        } catch (Exception e) {
            try {
				new MyDialog("Hãy nhập số nguyên hợp lệ!", MyDialog.ERROR_DIALOG);
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedLookAndFeelException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            return false;
        }
        if (flag) {
            try {
				new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else {
            try {
				new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return flag;
    }
}
