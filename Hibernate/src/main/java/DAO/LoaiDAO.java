package DAO;

import DTO.GiamGia;
import DTO.HibernateUtils;
import DTO.LoaiSP;
import DTO.SanPham;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class LoaiDAO {

    public ArrayList<LoaiSP> getDanhSachLoai() {
    	SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		ArrayList<LoaiSP> dsl = new ArrayList<LoaiSP>();
        try {
        	session.getTransaction().begin();
			String sql = "Select e from " + LoaiSP.class.getName() + " e ";
            //String sql = "SELECT * FROM loai";
            Query<LoaiSP> query = session.createQuery(sql);
			List<LoaiSP> danhsachloai = query.getResultList();
            
           for(LoaiSP sp:danhsachloai) {
        	   LoaiSP loaisp = new LoaiSP();
        	   loaisp.setMaLoai(sp.getMaLoai());
        	   loaisp.setTenLoai(sp.getTenLoai());
        	   dsl.add(loaisp);
           }
           session.getTransaction().commit();
            return dsl;
        } catch ( Exception e) {
        	
        }
        return dsl;
    }

    public boolean themLoai(LoaiSP loai) {
    	SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
        try {
        	session.getTransaction().begin();
//            String sql = "insert into loai(TenLoai) "
//                    + "values ("
//                    + "'" + loai.getTenLoai() + "')";
        	String sql = "INSERT INTO loai (MaLoai,TenLoai) "
					+ "VALUES (:maloai , :tenloai)";
        	Query<LoaiSP> query = session.createNativeQuery(sql);
        	query.setParameter("maloai", loai.getMaLoai());
        	query.setParameter("tenloai", loai.getTenLoai());
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
        } catch (Exception ex) {
            Logger.getLogger(LoaiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean xoaLoai(int maLoai) {
    	SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
        try {
            //String sql = "DELETE FROM Loai WHERE MaLoai=" + maLoai;
        	session.getTransaction().begin();
			String sql = "Delete from " + LoaiSP.class.getName() + " d "+" where d.maLoai = :maloai " ;
			Query<LoaiSP> query = session.createQuery(sql);
			query.setParameter("maloai", maLoai);
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

    public boolean suaLoai(int maLoai, String ten) {
    	SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
        try {
        	session.getTransaction().begin();
            //String sql = "UPDATE Loai SET TenLoai='" + ten + "' WHERE MaLoai=" + maLoai;
            String sql = "update " +LoaiSP.class.getName() + " d " + " set d.tenLoai = :tenloai where d.maLoai = :maloai";
            Query<LoaiSP> query = session.createQuery(sql);
			query.setParameter("maloai", maLoai);
			query.setParameter("tenloai", ten);
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

}
