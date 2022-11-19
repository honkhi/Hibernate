package DAO;

import DTO.HibernateUtils;
import DTO.LoaiSP;
import DTO.NhaCungCap;
import DTO.SanPham;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class NhaCungCapDAO {

    public ArrayList<NhaCungCap> getListNhaCungCap() {
    	SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		ArrayList<NhaCungCap> dsncc = new ArrayList<NhaCungCap>();
        try {
    		session.getTransaction().begin();
            String sql = "SELECT e FROM " + NhaCungCap.class.getName() + " e ";
            Query<NhaCungCap> query = session.createQuery(sql);
            List<NhaCungCap> listncc = query.getResultList();
            for(NhaCungCap ncc : listncc) {
            	NhaCungCap nhacungcap = new NhaCungCap();
            	nhacungcap.setMaNCC(ncc.getMaNCC());
            	nhacungcap.setTenNCC(ncc.getTenNCC());
            	nhacungcap.setDiaChi(ncc.getDiaChi());
            	nhacungcap.setDienThoai(ncc.getDienThoai());
            	dsncc.add(nhacungcap);
            }
            session.getTransaction().commit();
            return dsncc;
        } catch (Exception ex) {
        	session.getTransaction().rollback();
        }
        return dsncc;
    }

    public NhaCungCap getNhaCungCap(int maNCC) {
    	SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
       
        try {
        	session.getTransaction().begin();
           // String sql = "SELECT * FROM nhacungcap WHERE MaNCC=" + maNCC;
          String sql = "select e from " +NhaCungCap.class.getName() + " e" +" where e.maNCC = :nhacungcap";
          Query<NhaCungCap> query = session.createQuery(sql);
          query.setParameter("nhacungcap", maNCC);
          NhaCungCap ncc = new NhaCungCap();
          ncc.setMaNCC(query.getSingleResult().getMaNCC());
          ncc.setTenNCC(query.getSingleResult().getTenNCC());
          ncc.setDiaChi(query.getSingleResult().getDiaChi());
          ncc.setDienThoai(query.getSingleResult().getDienThoai());
          session.getTransaction().commit();
          return ncc;
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean addNCC(NhaCungCap ncc) {
    	SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
        try {
        	session.getTransaction().begin();
        	String sql = "INSERT INTO nhacungcap (MaNCC,TenNCC,DiaChi,DienThoai) "
					+ "VALUES (:mancc , :tenncc , :diachi , :dienthoai)";
        	Query<NhaCungCap> query = session.createNativeQuery(sql);
        	query.setParameter("mancc", ncc.getMaNCC());
        	query.setParameter("tenncc", ncc.getTenNCC());
        	query.setParameter("diachi", ncc.getDiaChi());
        	query.setParameter("dienthoai", ncc.getDienThoai());
        	int count = query.executeUpdate();
			if (count > 0) {
				session.getTransaction().commit();
				return true;
			} else {
				session.clear();
				session.close();
				return false;
			}
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean updateNCC(NhaCungCap ncc) {
        SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
        	session.getTransaction().begin();
           
            String sql = "update " +NhaCungCap.class.getName() + " d " +
            " set d.tenNCC = :tennhacungcap , d.diaChi = :diachi , d.dienThoai = :dienthoai  where d.maNCC = :manhacungcap";
            Query<NhaCungCap> query = session.createQuery(sql);
			query.setParameter("manhacungcap",ncc.getMaNCC());
			query.setParameter("tennhacungcap",ncc.getTenNCC());
			query.setParameter("diachi", ncc.getDiaChi());
			query.setParameter("dienthoai", ncc.getDienThoai());
			int count = query.executeUpdate();
			if (count > 0) {
				session.getTransaction().commit();
				return true;
			} else {
				session.clear();
				session.close();
				return false;
			}
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteNCC(int maNCC) {
        boolean result = false;
        SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
        try {
        	session.getTransaction().begin();
            String sql = "DELETE FROM " +NhaCungCap.class.getName() +" WHERE MaNCC = :macungcap";
           Query<NhaCungCap> query = session.createQuery(sql);
           query.setParameter("macungcap", maNCC);
           int count = query.executeUpdate();
			if (count > 0) {
				session.getTransaction().commit();
				return true;
			} else {
				session.clear();
				session.close();
				return false;
			}
        } catch (Exception ex) {
            return false;
        }
    }
}
