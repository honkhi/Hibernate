package BUS;

import javax.swing.UnsupportedLookAndFeelException;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoan;
import MyCustom.MyDialog;

public class TaiKhoanBUS {

    private TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

    public String getTenDangNhapTheoMa(String ma) {
        int maNV = Integer.parseInt(ma);
        return taiKhoanDAO.getTenDangNhapTheoMa(maNV);
    }

    public String getQuyenTheoMa(String ma) {
        int maNV = Integer.parseInt(ma);
        return taiKhoanDAO.getQuyenTheoMa(maNV);
    }

    public void datLaiMatKhau(String ma, String tenDangNhap) {
        int maNV = Integer.parseInt(ma);
        boolean flag = taiKhoanDAO.datLaiMatKhau(maNV, tenDangNhap);
        if (flag) {
            try {
				new MyDialog("Đặt lại thành công! Mật khẩu mới là: " + tenDangNhap, MyDialog.SUCCESS_DIALOG);
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
				new MyDialog("Đặt lại thất bại!", MyDialog.ERROR_DIALOG);
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
    }

    public void datLaiQuyen(String ma, String quyen) throws InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        int maNV = Integer.parseInt(ma);
        boolean flag = taiKhoanDAO.datLaiQuyen(maNV, quyen);
        if (flag) {
            try {
				new MyDialog("Đặt lại thành công!", MyDialog.SUCCESS_DIALOG);
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
            new MyDialog("Đặt lại thất bại!", MyDialog.ERROR_DIALOG);
        }
    }

    public boolean kiemTraTrungTenDangNhap(String tenDangNhap) {
        return taiKhoanDAO.kiemTraTrungTenDangNhap(tenDangNhap);
    }

    public boolean themTaiKhoan(String ma, String tenDangNhap, String quyen) {
        int maNV = Integer.parseInt(ma);
        if (tenDangNhap.trim().equals("")) {
            try {
				new MyDialog("Không được để trống Tên đăng nhập!", MyDialog.ERROR_DIALOG);
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
        if (kiemTraTrungTenDangNhap(tenDangNhap)) {
            MyDialog dlg = null;
			try {
				dlg = new MyDialog("Tên đăng nhập bị trùng! Có thể tài khoản bị khoá, thực hiện mở khoá?", MyDialog.WARNING_DIALOG);
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
            if (dlg.getAction() == MyDialog.OK_OPTION) {
                moKhoaTaiKhoan(ma);
                return true;
            }
            return false;
        }
        boolean flag = taiKhoanDAO.themTaiKhoan(ma, tenDangNhap, quyen);
        if (flag) {
            try {
				new MyDialog("Cấp tài khoản thành công! Mật khẩu là " + tenDangNhap, MyDialog.SUCCESS_DIALOG);
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
				new MyDialog("Cấp tài khoản thất bại! Tài khoản đã tồn tại", MyDialog.ERROR_DIALOG);
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

    public void khoaTaiKhoan(String ma) {
        int maNV = Integer.parseInt(ma);
        boolean flag = taiKhoanDAO.khoaTaiKhoan(maNV);
        if (flag) {
            try {
				new MyDialog("Khoá tài khoản thành công!", MyDialog.SUCCESS_DIALOG);
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
				new MyDialog("Khoá tài khoản thất bại!", MyDialog.ERROR_DIALOG);
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
    }

    public void moKhoaTaiKhoan(String ma) {
        int maNV = Integer.parseInt(ma);
        boolean flag = taiKhoanDAO.moKhoaTaiKhoan(maNV);
        if (flag) {
            try {
				new MyDialog("Mở khoá tài khoản thành công!", MyDialog.SUCCESS_DIALOG);
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
				new MyDialog("Mở khoá tài khoản thất bại!", MyDialog.ERROR_DIALOG);
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
    }

    public boolean doiMatKhau(String matKhauCu, String matKhauMoi, String nhapLaiMatKhau) {
        if(!matKhauMoi.equals(nhapLaiMatKhau)) {
            try {
				new MyDialog("Mật khẩu mới không khớp!", MyDialog.ERROR_DIALOG);
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
        boolean flag = taiKhoanDAO.doiMatKhau(matKhauCu, matKhauMoi);
        if (flag) {
            try {
				new MyDialog("�?ổi thành công!", MyDialog.SUCCESS_DIALOG);
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
				new MyDialog("Mật khẩu cũ nhập sai!", MyDialog.ERROR_DIALOG);
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
    
    public int getTrangThai(String maNV) {
        int ma = Integer.parseInt(maNV);
        return taiKhoanDAO.getTrangThai(ma);
    }

}
