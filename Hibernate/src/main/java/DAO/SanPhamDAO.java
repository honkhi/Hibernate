package DAO;

import DTO.HibernateUtils;
import DTO.NhanVien;
import DTO.SanPham;
import DTO.TaiKhoan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class SanPhamDAO {
	
	public ArrayList<SanPham> getListSanPham() {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		ArrayList<SanPham> dssp = new ArrayList<>();
		try {
			
			session.getTransaction().begin();
			String sql = "Select e from " + SanPham.class.getName() + " e ";
			Query<SanPham> query = session.createQuery(sql);
			List<SanPham> danhsachsanpham = query.getResultList();
			for(SanPham sp : danhsachsanpham) {
				SanPham sanpham = new SanPham();
				sanpham.setMaSP(sp.getMaSP());
				sanpham.setTenSP(sp.getTenSP());
				sanpham.setMaLoai(sp.getMaLoai());
				sanpham.setSoLuong(sp.getSoLuong());
				sanpham.setDonGia(sp.getDonGia());
				sanpham.setDonViTinh(sp.getDonViTinh());
				sanpham.setHinhAnh(sp.getHinhAnh());
				dssp.add(sanpham);
			}
			session.getTransaction().commit();
			return dssp;
		} catch (Exception e) {
		}

		return dssp;
	}

//	 public ArrayList<SanPham> getListSanPham() {
//	        try {
//	            String sql = "SELECT * FROM sanpham";
//	            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
//	            ResultSet rs = pre.executeQuery();
//	            ArrayList<SanPham> dssp = new ArrayList<>();
//	            while (rs.next()) {
//	                SanPham sp = new SanPham();
//
//	                sp.setMaSP(rs.getInt(1));
//	                sp.setTenSP(rs.getString(2));
//	                sp.setMaLoai(rs.getInt(3));
//	                sp.setSoLuong(rs.getInt(4));
//	                sp.setDonViTinh(rs.getString(5));
//	                sp.setHinhAnh(rs.getString(6));
//	                sp.setDonGia(rs.getInt(7));
//
//	                dssp.add(sp);
//	            }
//	            return dssp;
//	        } catch (SQLException e) {
//	        }
//
//	        return null;
//	    }
	public SanPham getSanPham(int ma) {
		SanPham sanpham = null;
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
			String sql = "Select e from " + SanPham.class.getName() + " e " + " where e.maSP like :ma";
			Query<SanPham> query = session.createQuery(sql);
			query.setParameter("ma", ma);
			sanpham = query.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			return null;
		}

		return sanpham;
	}

	public ArrayList<SanPham> getSanPhamTheoLoai(int maLoai) {
		SanPham sanpham = null;
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
			String sql = "Select e from " + SanPham.class.getName() + " e " + " where e.maLoai like :ma";
			Query<SanPham> query = session.createQuery(sql);
			query.setParameter("ma", maLoai);
			List<SanPham> danhsachsanpham = query.getResultList();
			ArrayList<SanPham> dssp = new ArrayList<SanPham>();
			dssp.addAll(danhsachsanpham);
			session.getTransaction().commit();
			return dssp;

		} catch (Exception e) {
		}

		return null;
	}

	public String getAnh(int ma) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
			String sql = "Select d.hinhAnh from " + SanPham.class.getName() + " d " + "where d.maSP like :maNV ";
			Query<String> query = session.createQuery(sql);
			query.setParameter("maNV", ma);
			String result = query.getSingleResult();
			if (result != null) {
				session.clear();
				session.close();
				return result;
			} else {
				session.clear();
				session.close();
				return "";
			}
		} catch (Exception e) {
		}
		return "";
	}

	public void capNhatSoLuongSP(int ma, int soLuongMat) {

		SanPham sp = getSanPham(ma);
		int soLuong = sp.getSoLuong();
		sp.setSoLuong(soLuong + soLuongMat);
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
			String sql = "Update " + SanPham.class.getName() + " d "
					+ " Set d.maSP = :ma , d.tenSP = :tenSP , d.maLoai = :maLoai, d.soLuong = :soluong , d.donViTinh = :donViTinh, d.hinhAnh = :hinhAnh , d.donGia = :donGia where d.maSP = :ma";
			Query<SanPham> query = session.createQuery(sql);
			query.setParameter("soluong", sp.getSoLuong());
			query.setParameter("tenSP", sp.getTenSP());
			query.setParameter("maLoai", sp.getMaLoai());
			query.setParameter("donViTinh", sp.getDonViTinh());
			query.setParameter("hinhAnh", sp.getHinhAnh());
			query.setParameter("donGia", sp.getDonGia());
			query.setParameter("ma", ma);
			int count = query.executeUpdate();
			if (count > 0) {
				session.getTransaction().commit();
				System.out.println("add thanh cong");
			} else {
				session.clear();
				session.close();
				System.out.println("add that bai");
			}

		} catch (Exception e) {
			System.out.println("bug cmnr");
		}

	}

	public boolean themSanPham(SanPham sp) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
			String sql = "INSERT INTO sanpham (tenSP, maLoai, soLuong, donViTinh, hinhAnh, donGia) "
					+ "VALUES (:tenSP, :maLoai, :soLuong, :donViTinh, :hinhAnh, :donGia)";
			Query<SanPham> query = session.createNativeQuery(sql);
			query.setParameter("tenSP", sp.getTenSP());
			query.setParameter("maLoai", sp.getMaLoai());
			query.setParameter("soLuong", sp.getSoLuong());
			query.setParameter("donViTinh", sp.getDonViTinh());
			query.setParameter("hinhAnh", sp.getHinhAnh());
			query.setParameter("donGia", sp.getDonGia());
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

	public boolean nhapSanPhamTuExcel(SanPham sp) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
			String sql = "DELETE * FROM sanpham; "
					+ "INSERT INTO SanPham(TenSP, MaLoai, SoLuong, DonViTinh, HinhAnh, DonGia) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
			Query<SanPham> query = session.createNativeQuery(sql);
			query.setParameter("tenSP", sp.getTenSP());
			query.setParameter("maLoai", sp.getMaLoai());
			query.setParameter("soLuong", sp.getSoLuong());
			query.setParameter("donViTinh", sp.getDonViTinh());
			query.setParameter("hinhAnh", sp.getHinhAnh());
			query.setParameter("donGia", sp.getDonGia());
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

	public boolean xoaSanPham(int maSP) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
			String sql = "Delete from " + SanPham.class.getName() + " d "+" where d.maSP = :maSP " ;
			Query<SanPham> query = session.createQuery(sql);
			query.setParameter("maSP", maSP);
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

	public boolean suaSanPham(SanPham sp) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
			String sql = "Update " + SanPham.class.getName() + " d "
					+ " Set d.maSP = :ma , d.tenSP = :tenSP , d.maLoai = :maLoai, d.soLuong = :soluong , d.donViTinh = :donViTinh, d.hinhAnh = :hinhAnh , d.donGia = :donGia where d.maSP = :ma";
			Query<SanPham> query = session.createQuery(sql);
			query.setParameter("soluong", sp.getSoLuong());
			query.setParameter("tenSP", sp.getTenSP());
			query.setParameter("maLoai", sp.getMaLoai());
			query.setParameter("donViTinh", sp.getDonViTinh());
			query.setParameter("hinhAnh", sp.getHinhAnh());
			query.setParameter("donGia", sp.getDonGia());
			query.setParameter("ma", sp.getMaSP());
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
			e.printStackTrace();
		}
		return false;
	}
	
}
