package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVien;
import MyCustom.MyDialog;

import java.util.ArrayList;

import javax.swing.UnsupportedLookAndFeelException;

public class NhanVienBUS {

    private NhanVienDAO nvDAO = new NhanVienDAO();
    private ArrayList<NhanVien> listNhanVien = null;

    public NhanVienBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listNhanVien = nvDAO.getDanhSachNhanVien();
    }

    public ArrayList<NhanVien> getDanhSachNhanVien() {
        if (this.listNhanVien == null)
            docDanhSach();
        return this.listNhanVien;
    }

    public boolean themNhanVien(String ho, String ten, String gioiTinh, String chucVu) throws InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        ho = ho.trim();
        ten = ten.trim();
        chucVu = chucVu.trim();
        if (ten.equals("")) {
            new MyDialog("TÃªn khÃ´ng Ä‘Æ°á»£c Ä‘á»ƒ trá»‘ng!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (chucVu.equals("")) {
            new MyDialog("Chá»©c vá»¥ khÃ´ng Ä‘Æ°á»£c Ä‘á»ƒ trá»‘ng!", MyDialog.ERROR_DIALOG);
            return false;
        }
        NhanVien nv = new NhanVien();
        nv.setHo(ho);
        nv.setTen(ten);
        nv.setGioiTinh(gioiTinh);
        nv.setChucVu(chucVu);
        boolean flag = nvDAO.themNhanVien(nv);
        if (!flag) {
            new MyDialog("ThÃªm tháº¥t báº¡i!", MyDialog.ERROR_DIALOG);
        } else {
            new MyDialog("ThÃªm thÃ nh cÃ´ng!", MyDialog.SUCCESS_DIALOG);
        }
        return flag;
    }

    public boolean updateNhanVien(String ma, String ho, String ten, String gioiTinh, String chucVu) throws InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        int maNV = Integer.parseInt(ma);
        ho = ho.trim();
        ten = ten.trim();
        chucVu = chucVu.trim();
        if (ten.equals("")) {
            new MyDialog("TÃªn khÃ´ng Ä‘Æ°á»£c Ä‘á»ƒ trá»‘ng!", MyDialog.ERROR_DIALOG);
            return false;
        }
        if (chucVu.equals("")) {
            new MyDialog("Chá»©c vá»¥ khÃ´ng Ä‘Æ°á»£c Ä‘á»ƒ trá»‘ng!", MyDialog.ERROR_DIALOG);
            return false;
        }
        NhanVien nv = new NhanVien();
        nv.setMaNV(maNV);
        nv.setHo(ho);
        nv.setTen(ten);
        nv.setGioiTinh(gioiTinh);
        nv.setChucVu(chucVu);
        boolean flag = nvDAO.updateNhanVien(nv);
        if (!flag) {
            new MyDialog("Cáº­p nháº­p tháº¥t báº¡i!", MyDialog.ERROR_DIALOG);
        } else {
            new MyDialog("Cáº­p nháº­p thÃ nh cÃ´ng!", MyDialog.SUCCESS_DIALOG);
        }
        return flag;
    }

    public ArrayList<NhanVien> timNhanVien(String tuKhoa) {
        tuKhoa = tuKhoa.toLowerCase();
        ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
        for (NhanVien nv : listNhanVien) {
            if (nv.getHo().toLowerCase().contains(tuKhoa) || nv.getTen().toLowerCase().contains(tuKhoa) ||
                    nv.getGioiTinh().toLowerCase().contains(tuKhoa) || nv.getChucVu().toLowerCase().contains(tuKhoa)) {
                dsnv.add(nv);
            }
        }
        return dsnv;
    }

    public boolean xoaNhanVien(String ma) {
        try {
            int maNV = Integer.parseInt(ma);
            MyDialog dlg = new MyDialog("Báº¡n cÃ³ cháº¯c cháº¯n muá»‘n xoÃ¡?", MyDialog.WARNING_DIALOG);
            boolean flag = false;
            if (dlg.getAction() == MyDialog.OK_OPTION) {
                flag = nvDAO.deleteNhanVien(maNV);
                if (flag) {
                    new MyDialog("XoÃ¡ thÃ nh cÃ´ng!", MyDialog.SUCCESS_DIALOG);
                } else {
                    new MyDialog("XoÃ¡ tháº¥t báº¡i!", MyDialog.ERROR_DIALOG);
                }
            }
            return flag;
        } catch (Exception e) {
            try {
				new MyDialog("ChÆ°a chï¿½?n nhÃ¢n viÃªn!", MyDialog.ERROR_DIALOG);
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
        return false;
    }

    public boolean nhapExcel(String ho, String ten, String gioiTinh, String chucVu) {
        NhanVien nv = new NhanVien();
        nv.setHo(ho);
        nv.setTen(ten);
        nv.setGioiTinh(gioiTinh);
        nv.setChucVu(chucVu);
        boolean flag = nvDAO.nhapExcel(nv);
        return flag;
    }
}
