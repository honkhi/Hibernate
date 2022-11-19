package DAO;

import DTO.GiamGia;
import DTO.HibernateUtils;
import DTO.SanPham;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class GiamGiaDAO {


    public ArrayList<GiamGia> getDanhSachMaGiam() {
    	SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		ArrayList<GiamGia> dsgg = new ArrayList<>();
		try {
			
			session.getTransaction().begin();
			String sql = "Select e from " + GiamGia.class.getName() + " e ";
			// String sql = "SELECT * FROM sanpham";
			Query<GiamGia> query = session.createQuery(sql);
			List<GiamGia> danhsachgiamgia = query.getResultList();
			for(GiamGia rs : danhsachgiamgia) {
				GiamGia gg = new GiamGia();
				gg.setMaGiam(rs.getMaGiam());
                gg.setTenGiamGia(rs.getTenGiamGia());
                gg.setPhanTramGiam(rs.getPhanTramGiam());
                gg.setDieuKien(rs.getDieuKien());
                gg.setNgayBD(rs.getNgayBD());
                gg.setNgayKT(rs.getNgayKT());
                dsgg.add(gg);
			}
			session.getTransaction().commit();
			return dsgg;
		} catch (Exception e) {
		}

		return dsgg;
    }

    public boolean themMaGiam(GiamGia gg) {
    	SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
			String sql = "INSERT INTO giamgia (MaGiam, TenGiamGia, PhanTramGiam, DieuKien, NgayBD, NgayKT) "
					+ "VALUES (:magiam, :tengiamgia, :phantramgiam, :dieukien, :ngaybd, :ngayketthuc)";
			Query<GiamGia> query = session.createNativeQuery(sql);
			query.setParameter("magiam", gg.getMaGiam());
			query.setParameter("tengiamgia", gg.getTenGiamGia());
			query.setParameter("phantramgiam", gg.getPhanTramGiam());
			query.setParameter("dieukien", gg.getDieuKien());
			query.setParameter("ngaybd", gg.getNgayBD());
			query.setParameter("ngaykt", gg.getNgayKT());
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

    public boolean suaMaGiam(GiamGia gg) {
    	SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
        try {
        	session.getTransaction().begin();
            String sql = "UPDATE " +GiamGia.class.getName()+ " d SET TenGiamGia = :tengiamgia, PhanTramGiam = :phantramgiam, DieuKien = :dieukien , NgayBD = :ngaybd, NgayKT = :ngaykt WHERE MaGiam = :magiam";
            Query<GiamGia> query = session.createQuery(sql);
            query.setParameter("magiam", gg.getMaGiam());
			query.setParameter("tengiamgia", gg.getTenGiamGia());
			query.setParameter("phantramgiam", gg.getPhanTramGiam());
			query.setParameter("dieukien", gg.getDieuKien());
			query.setTimestamp("ngaybd",new java.sql.Timestamp(gg.getNgayBD().getTime()));
			query.setTimestamp("ngaykt",new java.sql.Timestamp(gg.getNgayKT().getTime()));
            return query.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
