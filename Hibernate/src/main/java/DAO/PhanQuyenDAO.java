package DAO;

import DTO.HibernateUtils;
import DTO.LoaiSP;
import DTO.PhanQuyen;
import DTO.TaiKhoan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class PhanQuyenDAO {

    public ArrayList<PhanQuyen> getListQuyen() {
    	SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		ArrayList<PhanQuyen> dspq = new ArrayList<PhanQuyen>();
        try {
        	session.getTransaction().begin();
			String sql = "Select d from " + PhanQuyen.class.getName() + " d ";
            Query<PhanQuyen> query = session.createQuery(sql);
			List<PhanQuyen> danhsachphanquyen = query.getResultList();
            
           for(PhanQuyen pq :danhsachphanquyen) {
        	   PhanQuyen phanquyen = new PhanQuyen();
        	   phanquyen.setNhapHang(pq.getNhapHang());
        	   phanquyen.setQlKhachHang(pq.getQlKhachHang());
        	   phanquyen.setQlNhanVien(pq.getQlNhanVien());
        	   phanquyen.setQlSanPham(pq.getQlSanPham());
        	   phanquyen.setThongKe(pq.getThongKe());
        	   phanquyen.setQuyen(pq.getQuyen());
        	   dspq.add(phanquyen);
           }
           session.getTransaction().commit();
            return dspq;
        } catch (Exception e) {
        }
        return dspq;
    }

    public PhanQuyen getQuyen(String quyen) {
    	SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
        try {
        	session.getTransaction().begin();
           // String sql = "SELECT * FROM phanquyen WHERE quyen='" + quyen + "'";
        	String sql = "select e from " + PhanQuyen.class.getName() + " e " +" where e.quyen = :phanquyen";
            Query<PhanQuyen> query = session.createQuery(sql);
            query.setParameter("phanquyen", quyen);
            PhanQuyen pq = query.getSingleResult();
            PhanQuyen phanquyen = new PhanQuyen();
            phanquyen.setNhapHang(pq.getNhapHang());
            phanquyen.setQlKhachHang(pq.getQlKhachHang());
            phanquyen.setQlNhanVien(pq.getQlNhanVien());
            phanquyen.setQlSanPham(pq.getQlSanPham());
            phanquyen.setQuyen(pq.getQuyen());
            phanquyen.setThongKe(pq.getThongKe());
            session.getTransaction().commit();
            return phanquyen;
        } catch (Exception e) {
        	 return null;        }
       
    }

    public boolean suaQuyen(PhanQuyen phanQuyen) {
    	SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
        try {
        	session.getTransaction().begin();
            //String sql = "UPDATE phanquyen SET NhapHang=?,QLSanPham=?,QLNhanVien=?,QLKhachHang=?,ThongKe=? WHERE Quyen=?";
        	String sql = "update " +PhanQuyen.class.getName()+ " e " + 
            "set e.nhapHang = :nhaphang , e.qlSanPham = :qlsanpham , e.qlNhanVien = :qlnhanvien , e.qlKhachHang = :qlkhachhang ,e.thongKe = :thongke where e.quyen = :quyen";
            Query<PhanQuyen> query = session.createQuery(sql);
            query.setParameter("nhaphang", phanQuyen.getNhapHang());
            query.setParameter("qlsanpham", phanQuyen.getQlSanPham());
            query.setParameter("qlnhanvien", phanQuyen.getQlNhanVien());
            query.setParameter("qlkhachhang", phanQuyen.getQlKhachHang());
            query.setParameter("thongke", phanQuyen.getThongKe());
            query.setParameter("quyen", phanQuyen.getQuyen());
            int count = query.executeUpdate();
			System.out.println(count);
			if (count > 0) {
				session.getTransaction().commit();
				return true;
			} else {
				session.clear();
				session.close();
				return false;
			}
        } catch (Exception e) {
        }
        return false;
    }

    public boolean themQuyen(PhanQuyen phanQuyen) {
    	SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
        try {
        	session.getTransaction().begin();
        	String sql = "INSERT INTO phanquyen(Quyen,NhapHang,QLSanPham,QLNhanVien,QLKhachHang,ThongKe) VALUES (:Quyen,:NhapHang,:QLSanPham,:QLNhanVien,:QLKhachHang,:ThongKe)";
            Query<PhanQuyen> query = session.createNativeQuery(sql);
            query.setParameter("Quyen", phanQuyen.getQuyen());
            query.setParameter("NhapHang", phanQuyen.getNhapHang());
            query.setParameter("QLSanPham", phanQuyen.getQlSanPham());
            query.setParameter("QLNhanVien", phanQuyen.getQlNhanVien());
            query.setParameter("QLKhachHang", phanQuyen.getQlKhachHang());
            query.setParameter("ThongKe", phanQuyen.getThongKe());
             
            int count = query.executeUpdate();
			System.out.println(count);
			if (count > 0) {
				session.getTransaction().commit();
				return true;
			} else {
				session.clear();
				session.close();
				return false;
			}
        } catch (Exception e) {
        	return false;
        }
        
    }

    public boolean xoaQuyen(String phanQuyen) {
    	SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
        try {
        	session.getTransaction().begin();
            String sql1 = "UPDATE "+ TaiKhoan.class.getName() +" e SET e.quyen='Default' WHERE e.quyen= :quyen";
            Query<TaiKhoan> query = session.createQuery(sql1);
            query.setParameter("quyen", phanQuyen);
            int count = query.executeUpdate();
            session.flush();
            String sql = "DELETE FROM "+ PhanQuyen.class.getName() + " e WHERE e.quyen= :quyen";
            Query<PhanQuyen> query1 = session.createQuery(sql);
            query1.setParameter("quyen", phanQuyen);
            int count1 = query1.executeUpdate();
            if (count > 0 || count1 > 0) {
				session.getTransaction().commit();
				return true;
			} else {
				session.clear();
				session.close();
				return false;
			}
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
}
