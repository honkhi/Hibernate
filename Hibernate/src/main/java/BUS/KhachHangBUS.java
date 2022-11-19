package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHang;
import MyCustom.MyDialog;

import java.util.ArrayList;
import java.util.Locale;

import javax.swing.UnsupportedLookAndFeelException;

public class KhachHangBUS {

    private ArrayList<KhachHang> listKhachHang = null;
    private KhachHangDAO khachHangDAO = new KhachHangDAO();

    public void docDanhSach() {
        this.listKhachHang = khachHangDAO.getListKhachHang();
    }

    public ArrayList<KhachHang> getListKhachHang() {
        if (listKhachHang == null)
            docDanhSach();
        return listKhachHang;
    }

    public ArrayList<KhachHang> timKiemKhachHang(String txtMin, String txtMax) {
        if (txtMax.trim().equals("") && txtMin.trim().equals(""))
            return listKhachHang;
        try {
            ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
            txtMin = txtMin.replace(",", "");
            txtMax = txtMax.replace(",", "");
            int min = Integer.parseInt(txtMin);
            int max = Integer.parseInt(txtMax);
            for (KhachHang kh : listKhachHang) {
                if (kh.getTongChiTieu() >= min && kh.getTongChiTieu() <= max) {
                    dskh.add(kh);
                }
            }
            return dskh;
        } catch (Exception e) {
            try {
				new MyDialog("Hãy nhập giá trị nguyên phù hợp!", MyDialog.ERROR_DIALOG);
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
        }
        return null;
    }

    public ArrayList<KhachHang> timKiemKhachHang(String tuKhoa) {
        tuKhoa = tuKhoa.toLowerCase();
        ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
        for (KhachHang kh : listKhachHang) {
            String ho = kh.getHo().toLowerCase();
            String ten = kh.getTen().toLowerCase();
            String gioiTinh = kh.getGioiTinh().toLowerCase();
            if (ho.contains(tuKhoa) || ten.contains(tuKhoa) || gioiTinh.contains(tuKhoa)) {
                dskh.add(kh);
            }
        }
        return dskh;
    }

    public boolean themKhachHang(String ho, String ten, String gioiTinh) {
        if (ten.trim().equals("")) {
            try {
				new MyDialog("Không được để trống tên!", MyDialog.ERROR_DIALOG);
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
        if (gioiTinh.equals("Ch�?n giới tính")) {
            try {
				new MyDialog("Hãy ch�?n giới tính!", MyDialog.ERROR_DIALOG);
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
        KhachHang kh = new KhachHang();
        kh.setHo(ho);
        kh.setTen(ten);
        kh.setGioiTinh(gioiTinh);
        kh.setTongChiTieu(0);
        boolean flag = khachHangDAO.addKhachHang(kh);
        if (flag) {
            try {
				new MyDialog("Thêm thành công!", MyDialog.SUCCESS_DIALOG);
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
				new MyDialog("Thêm thất bại!", MyDialog.ERROR_DIALOG);
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

    public boolean suaKhachHang(String ma, String ho, String ten, String gioiTinh) {
        if (ten.trim().equals("")) {
            try {
				new MyDialog("Không được để trống tên!", MyDialog.ERROR_DIALOG);
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
        if (gioiTinh.equals("Ch�?n giới tính")) {
            try {
				new MyDialog("Hãy ch�?n giới tính!", MyDialog.ERROR_DIALOG);
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
        KhachHang kh = new KhachHang();
        kh.setHo(ho);
        kh.setTen(ten);
        kh.setGioiTinh(gioiTinh);
        boolean flag = khachHangDAO.updateKhachHang(Integer.parseInt(ma), kh);
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

    public boolean xoaKhachHang(String ma) {
        boolean flag = false;
        try {
            int maKH = Integer.parseInt(ma);
            MyDialog dlg = new MyDialog("Bạn có chắc chắn muốn xoá?", MyDialog.WARNING_DIALOG);
            if(dlg.getAction() == MyDialog.CANCEL_OPTION)
                return false;
            flag = khachHangDAO.deleteKhachHang(maKH);
        } catch (Exception e) {
            try {
				new MyDialog("Chưa ch�?n khách hàng!", MyDialog.ERROR_DIALOG);
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
        }
        if (flag) {
            try {
				new MyDialog("Xoá thành công!", MyDialog.SUCCESS_DIALOG);
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
				new MyDialog("Xoá thất bại!", MyDialog.ERROR_DIALOG);
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
