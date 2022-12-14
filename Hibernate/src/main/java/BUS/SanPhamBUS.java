package BUS;

import DAO.SanPhamDAO;
import DTO.SanPham;
import MyCustom.MyDialog;

import java.util.ArrayList;

import javax.swing.UnsupportedLookAndFeelException;

public class SanPhamBUS {

    private ArrayList<SanPham> listSanPham = null;
    private SanPhamDAO spDAO = new SanPhamDAO();

    public SanPhamBUS() {
        docListSanPham();
    }

    public void docListSanPham() {
        listSanPham = spDAO.getListSanPham();
    }

    public ArrayList<SanPham> getListSanPham() {
        if (listSanPham == null) {
            docListSanPham();
        }
        return listSanPham;
    }

    public SanPham getSanPham(String ma) {
        if (!ma.trim().equals("")) {
            try {
                int maSP = Integer.parseInt(ma);
                for (SanPham sp : listSanPham) {
                    if (sp.getMaSP() == maSP) {
                        return sp;
                    }
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    public ArrayList<SanPham> getSanPhamTheoTen(String ten) {
        ArrayList<SanPham> dssp = new ArrayList<SanPham>();
        for (SanPham sp : listSanPham) {
            String tenSP = sp.getTenSP().toLowerCase();
            if (tenSP.toLowerCase().contains(ten.toLowerCase())) {
                dssp.add(sp);
            }
        }
        return dssp;
    }

    public ArrayList<SanPham> getSanPhamTheoLoai(String ma) {
        if (!ma.trim().equals("")) {
            ArrayList<SanPham> dssp = new ArrayList<SanPham>();
            try {
                int maLoai = Integer.parseInt(ma);
                for (SanPham sp : listSanPham) {
                    if (sp.getMaLoai() == maLoai) {
                        dssp.add(sp);
                    }
                }
                return dssp;
            } catch (Exception e) {
            }
        }
        return null;
    }

    public String getAnh(String ma) {
        int maSP = Integer.parseInt(ma);
        return spDAO.getAnh(maSP);
    }

    public void capNhatSoLuongSP(int ma, int soLuongMat) {
        spDAO.capNhatSoLuongSP(ma, soLuongMat);
    }

    public boolean themSanPham(String ten,
            String loai,
            String soLuong,
            String donViTinh,
            String anh,
            String donGia) throws InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

        if (ten.trim().equals("")) {
            new MyDialog("T??n SP kh??ng ???????c ????? r???ng!", MyDialog.ERROR_DIALOG);
            return false;
        }

        if (donViTinh.trim().equals("")) {
            new MyDialog("Vui l??ng ??i???n ????n v??? t??nh!", MyDialog.ERROR_DIALOG);
            return false;
        }

        try {
            String[] loaiTmp = loai.split(" - ");
            int maLoai = Integer.parseInt(loaiTmp[0]);
            int soLuongSP = Integer.parseInt(soLuong);
            donGia = donGia.replace(",", "");
            int donGiaSP = Integer.parseInt(donGia);
            if (maLoai == 0) {
                new MyDialog("Vui l??ng ch???n Lo???i s???n ph???m!", MyDialog.ERROR_DIALOG);
                return false;
            }
            SanPham sp = new SanPham();
            sp.setTenSP(ten);
            sp.setMaLoai(maLoai);
            sp.setSoLuong(soLuongSP);
            sp.setDonViTinh(donViTinh);
            sp.setHinhAnh(anh);
            sp.setDonGia(donGiaSP);

            if (spDAO.themSanPham(sp)) {
                new MyDialog("Th??m th??nh c??ng!", MyDialog.SUCCESS_DIALOG);
                return true;
            } else {
                new MyDialog("Th??m th???t b???i!", MyDialog.ERROR_DIALOG);
                return false;
            }
        } catch (Exception e) {
            new MyDialog("Nh???p s??? h???p l??? cho ????n gi?? v?? S??? l?????ng!", MyDialog.ERROR_DIALOG);
        }
        return false;
    }

    public boolean nhapSanPhamTuExcel(String ten,
            String loai,
            String soLuong,
            String donViTinh,
            String anh,
            String donGia) {

        try {
            String[] loaiTmp = loai.split(" - ");
            int maLoai = Integer.parseInt(loaiTmp[0]);
            int soLuongSP = Integer.parseInt(soLuong);
            donGia = donGia.replace(",", "");
            int donGiaSP = Integer.parseInt(donGia);

            SanPham sp = new SanPham();
            sp.setTenSP(ten);
            sp.setMaLoai(maLoai);
            sp.setSoLuong(soLuongSP);
            sp.setDonViTinh(donViTinh);
            sp.setHinhAnh(anh);
            sp.setDonGia(donGiaSP);

            spDAO.nhapSanPhamTuExcel(sp);
        } catch (Exception e) {
        }
        return false;
    }

    public boolean xoaSanPham(String ma) throws InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        if (ma.trim().equals("")) {
            new MyDialog("Ch??a ch????n s???n ph???m ????? xo??!", MyDialog.ERROR_DIALOG);
            return false;
        }

        int maSP = Integer.parseInt(ma);
        if (spDAO.xoaSanPham(maSP)) {
            new MyDialog("Xo?? th??nh c??ng!", MyDialog.SUCCESS_DIALOG);
            return true;
        }

        new MyDialog("Xo?? th???t b???i!", MyDialog.ERROR_DIALOG);
        return false;
    }

    public boolean suaSanPham(String ma,
            String ten,
            String loai,
            String soLuong,
            String donViTinh,
            String anh,
            String donGia) throws InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

        try {
            if (ma.trim().equals("")) {
                new MyDialog("Ch??a ch????n s???n ph???m ????? s???a!", MyDialog.ERROR_DIALOG);
                return false;
            }
            donGia = donGia.replace(",", "");
            int maSP = Integer.parseInt(ma);
            String[] loaiTmp = loai.split(" - ");
            int maLoai = Integer.parseInt(loaiTmp[0]);
            int soLuongSP = Integer.parseInt(soLuong);
            int donGiaSP = Integer.parseInt(donGia);

            if (maLoai == 0) {
                new MyDialog("Vui l??ng ch????n Lo???i s???n ph???m!", MyDialog.ERROR_DIALOG);
                return false;
            }

            if (ten.trim().equals("")) {
                new MyDialog("T??n SP kh??ng ???????c ????? r???ng!", MyDialog.ERROR_DIALOG);
                return false;
            }

            if (donViTinh.trim().equals("")) {
                new MyDialog("Vui l??ng ??i????n ??????n v??? t??nh!", MyDialog.ERROR_DIALOG);
                return false;
            }

            SanPham sp = new SanPham();
            sp.setMaSP(maSP);
            sp.setTenSP(ten);
            sp.setMaLoai(maLoai);
            sp.setSoLuong(soLuongSP);
            sp.setDonViTinh(donViTinh);
            sp.setHinhAnh(anh);
            sp.setDonGia(donGiaSP);

            if (spDAO.suaSanPham(sp)) {
                new MyDialog("S???a th??nh c??ng!", MyDialog.SUCCESS_DIALOG);
                return true;
            } else {
                new MyDialog("S???a th???t b???i!", MyDialog.ERROR_DIALOG);
                return false;
            }
        } catch (Exception e) {
            new MyDialog("Nh???p s??? h???p l??? cho ??????n gi?? v?? S??? l?????ng!", MyDialog.ERROR_DIALOG);
        }
        return false;
    }

    public String getTenSP(int maSP) {
        for (SanPham sp : listSanPham) {
            if (sp.getMaSP() == maSP) {
                return sp.getTenSP();
            }
        }
        return "";
    }
}
