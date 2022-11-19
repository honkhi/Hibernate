package DAO;

import java.util.List;

import org.hibernate.query.Query;

import BUS.DangNhapBUS;
import DTO.HibernateUtils;
import DTO.NhanVien;
import DTO.TaiKhoan;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.*;

public class TaiKhoanDAO {

	public boolean themTaiKhoan(String ma, String tenDangNhap, String quyen) {

		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = factory.getCurrentSession();
		try {
			int Manv= Integer.parseInt(ma);
			session.getTransaction().begin();
			TaiKhoan nhanvien = new TaiKhoan(Manv,tenDangNhap,tenDangNhap,quyen);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return false;
	}

	public boolean kiemTraTrungTenDangNhap(String tenDangNhap1) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
			String sql = "Select d from " + TaiKhoan.class.getName() + " d "
					+ " where d.tenDangNhap like :tenDangNhap1";
			Query<TaiKhoan> query = session.createQuery(sql);
			query.setParameter("tenDangNhap1", tenDangNhap1);
			if (query.getResultList().size() > 0) {
				session.clear();
				session.close();
				return true;
			} else {
				session.clear();
				session.close();
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getTenDangNhapTheoMa(int maNV) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
			String sql = "Select d.tenDangNhap from " + TaiKhoan.class.getName() + " d "
					+ "where d.maNhanVien like :maNV ";
			Query<String> query = session.createQuery(sql);
			query.setParameter("maNV", maNV);
			String result = query.getSingleResult();
			if (result != null) {
				session.clear();
				session.close();
				return result;
			} else {
				session.clear();
				session.close();
				return "khong tim thay";
			}
		} catch (Exception e) {

		}
		return "khong tim thay";
	}

	public boolean datLaiMatKhau(int maNV, String tenDangNhap) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
			String sql = "Update TaiKhoan d " + " Set d.matKhau=:pass Where d.maNhanVien like :MaNV";
			Query query = session.createQuery(sql);
			query.setParameter("pass", tenDangNhap);
			query.setParameter("MaNV", maNV);

			int count = query.executeUpdate();
			System.out.println(count);
			if (count > 0) {
				session.getTransaction().commit();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	 public boolean datLaiQuyen(int maNV, String quyen) {
		 SessionFactory factory = HibernateUtils.getSessionFactory();
			Session session = factory.getCurrentSession();
	        try {
	        	session.getTransaction().begin();
				String sql = "Update TaiKhoan d " + " Set d.quyen=:quyen Where d.maNhanVien like :MaNV";
				Query query = session.createQuery(sql);
				query.setParameter("quyen", quyen);
				query.setParameter("MaNV", maNV);

				int count = query.executeUpdate();
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
	 
	  public String getQuyenTheoMa(int maNV) {
		  SessionFactory factory = HibernateUtils.getSessionFactory();
			Session session = factory.getCurrentSession();
	        try {
	        	session.getTransaction().begin();
				String sql = "Select d.quyen from " + TaiKhoan.class.getName() + " d "
						+ "where d.maNhanVien like :maNV ";
				Query<String> query = session.createQuery(sql);
				query.setParameter("maNV", maNV);
				String result = query.getSingleResult();
				if (result != null) {
					session.clear();
					session.close();
					return result;
				} else {
					session.clear();
					session.close();
					return "khong tim thay";
				}
	        } catch (Exception e) {
	        }
	        return "khong tim thay";
	    }
	  public boolean khoaTaiKhoan(int maNV) {
		  SessionFactory factory = HibernateUtils.getSessionFactory();
			Session session = factory.getCurrentSession();
	        try {
	        	session.getTransaction().begin();
				String sql = "Update TaiKhoan d " + " Set d.trangThai= 0 Where d.maNhanVien like :MaNV";
				Query query = session.createQuery(sql);
				query.setParameter("MaNV", maNV);

				int count = query.executeUpdate();
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
	  public boolean moKhoaTaiKhoan(int maNV) {
		  SessionFactory factory = HibernateUtils.getSessionFactory();
			Session session = factory.getCurrentSession();
	        try {
	        	session.getTransaction().begin();
				String sql = "Update TaiKhoan d " + " Set d.trangThai= 1 Where d.maNhanVien like :MaNV";
				Query query = session.createQuery(sql);
				query.setParameter("MaNV", maNV);

				int count = query.executeUpdate();
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

	  public boolean doiMatKhau(String matKhauCu, String matKhauMoi) {
		  SessionFactory factory = HibernateUtils.getSessionFactory();
			Session session = factory.getCurrentSession();
	        try {
	        	session.getTransaction().begin();
				String sql = "Update TaiKhoan d " + " Set d.matKhau= :matkhaumoi Where d.maNhanVien like :MaNV and d.matKhau like :matkhaucu";
				Query query = session.createQuery(sql);
				query.setParameter("MaNV", DangNhapBUS.taiKhoanLogin.getMaNhanVien() );
				query.setParameter("matkhaumoi", matKhauMoi);
				query.setParameter("matkhaucu", matKhauCu);
				int count = query.executeUpdate();
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
	  public int getTrangThai(int ma) {
		  SessionFactory factory = HibernateUtils.getSessionFactory();
			Session session = factory.getCurrentSession();
	        try {
	        	session.getTransaction().begin();
				String sql = "Select d.trangThai from " + TaiKhoan.class.getName() + " d "
						+ "where d.maNhanVien like :maNV ";
				Query<Integer> query = session.createQuery(sql);
				query.setParameter("maNV", ma);
				Integer result = query.getSingleResult();
				if (result != null) {
					session.clear();
					session.close();
					return result;
				} else {
					session.clear();
					session.close();
					return -1;
				}
	        } catch (Exception e) {
	        }
	        return -1;
	    }
	  
	  
}
