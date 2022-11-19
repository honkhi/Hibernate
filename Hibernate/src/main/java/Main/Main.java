package Main;

import DTO.HibernateUtils;
import DTO.HoaDon;
import DTO.LoaiSP;
import DTO.NhaCungCap;
import DTO.NhanVien;
import DTO.PhanQuyen;
import DTO.PhieuNhap;
import DTO.SanPham;
import DTO.TaiKhoan;
import DTO.ThongKe;

import GUI.DangNhapGUI;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.UnsupportedLookAndFeelException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import BUS.ThongKeBUS;
import DAO.*;
public class Main {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        changLNF("Nimbus");
        DangNhapGUI login = new DangNhapGUI();
        login.showWindow();
       
    }

    public static void changLNF(String nameLNF) throws InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (nameLNF.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
        }
    }
}
