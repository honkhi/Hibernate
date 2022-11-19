package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import DTO.HibernateUtils;
import DTO.NhanVien;

public class NhanVienDAO {

	public ArrayList<NhanVien> getDanhSachNhanVien() {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		ArrayList<NhanVien> dssv = new ArrayList<NhanVien>();
		try {
			session.getTransaction().begin();
			String sql = "Select e from " + NhanVien.class.getName() + " e ";
			Query<NhanVien> query = session.createQuery(sql);
			List<NhanVien> danhsachnhanvien = query.getResultList();
			
			dssv.addAll(danhsachnhanvien);
			session.getTransaction().commit();
			return dssv;
		} catch (Exception e) {
		}

		return dssv;
	}

	public NhanVien getNhanVien(int maNV) {
		NhanVien nv = null;
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
			String sql = "Select e from " + NhanVien.class.getName() + " e " + " where e.maNV like :manv";
			Query<NhanVien> query = session.createQuery(sql);
			query.setParameter("manv", maNV);
			nv = query.getSingleResult();
		} catch (Exception e) {
			return null;
		}

		return nv;
	}

	public boolean updateNhanVien(NhanVien nv) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
			String sql = "Update " + NhanVien.class.getName() + " d "
					+ "Set d.ho = :Ho , d.ten = :Ten , d.gioiTinh = :GioiTinh , d.chucVu = :ChucVu where d.maNV like :manv";
			Query<NhanVien> query = session.createQuery(sql);
			query.setParameter("Ho", nv.getHo());
			query.setParameter("Ten", nv.getTen());
			query.setParameter("GioiTinh", nv.getGioiTinh());
			query.setParameter("ChucVu", nv.getChucVu());
			query.setParameter("manv", nv.getMaNV());
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
			return false;
		}
	}

	public boolean deleteNhanVien(int maNV) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
			String sql = "Delete from " +NhanVien.class.getName()+ " d" + " where d.maNV like :manv";
			Query<NhanVien> query = session.createQuery(sql);
			query.setParameter("manv", maNV);
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
			return false;
		}
	}

	public boolean themNhanVien(NhanVien nv) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
			String sql = "Insert into nhanvien " +
					" (ho , ten , gioiTinh , chucVu ) " + " Values (:Ho , :Ten , :GioiTinh , :ChucVu)";
			Query<NhanVien> query = session.createNativeQuery(sql);
			query.setParameter("Ho", nv.getHo());
			query.setParameter("Ten", nv.getTen());
			query.setParameter("GioiTinh", nv.getGioiTinh());
			query.setParameter("ChucVu", nv.getChucVu());
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
            return false;
        }
    }
	public boolean nhapExcel(NhanVien nv) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
        try {
        	session.getTransaction().begin();
			String sql = "Delete from " +NhanVien.class.getName()+ " d" + " where d.maNV like :manv";
			Query<NhanVien> query = session.createQuery(sql);
			query.setParameter("manv", nv.getMaNV());
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
        	return false;
        }
        
    }
}
