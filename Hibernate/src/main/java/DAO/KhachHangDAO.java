/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.HibernateUtils;
import DTO.KhachHang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 * @author User
 */
public class KhachHangDAO {

//  public ArrayList<KhachHang> getListKhachHang() {
//  try {
//      String sql = "SELECT * FROM khachhang WHERE TinhTrang=1";
//      PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
//      ResultSet rs = pre.executeQuery();
//      ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
//      while (rs.next()) {
//          KhachHang kh = new KhachHang();
//          kh.setMaKH(rs.getInt(1));
//          kh.setHo(rs.getString(2));
//          kh.setTen(rs.getString(3));
//          kh.setGioiTinh(rs.getString(4));
//          kh.setTongChiTieu(rs.getInt(5));
//          dskh.add(kh);
//      }
//      return dskh;
//  } catch (SQLException ex) {
//  }
//  return null;
//}
//
//public KhachHang getKhachHang(int maKH) {
//  KhachHang kh = null;
//  try {
//      String sql = "SELECT * FROM khachhang WHERE MaKH=? AND TinhTrang=1";
//      PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
//      prep.setInt(1, maKH);
//      ResultSet rs = prep.executeQuery();
//      while (rs.next()) {
//          kh = new KhachHang();
//          kh.setMaKH(rs.getInt(1));
//          kh.setHo(rs.getString(2));
//          kh.setTen(rs.getString(3));
//          kh.setGioiTinh(rs.getString(4));
//          kh.setTongChiTieu(rs.getInt(5));
//      }
//  } catch (SQLException ex) {
//      return null;
//  }
//  return kh;
//}

//Yay, Yatta zo
	public ArrayList<KhachHang> getListKhachHang() {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
		try {
			session.getTransaction().begin();
			String sql = "Select e from " + KhachHang.class.getName() + " e";
			Query<KhachHang> query = session.createQuery(sql);
			List<KhachHang> danhsachkhachhang = query.getResultList();
			for (KhachHang kh : danhsachkhachhang) {
				KhachHang kh1 = new KhachHang();
				kh1.setMaKH(kh.getMaKH());
				kh1.setHo(kh.getHo());
				kh1.setTen(kh.getTen());
				kh1.setGioiTinh(kh.getGioiTinh());
				kh1.setTongChiTieu(kh.getTongChiTieu());
				dskh.add(kh1);
			}
			session.getTransaction().commit();
			return dskh;
		} catch (Exception ex) {
			session.getTransaction().rollback();
		}
		return dskh;

	}

//public KhachHang getKhachHang(int maKH) {
//KhachHang kh = null;
//try {
//String sql = "SELECT * FROM khachhang WHERE MaKH=? AND TinhTrang=1";
//PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
//prep.setInt(1, maKH);
//ResultSet rs = prep.executeQuery();
//while (rs.next()) {
//    kh = new KhachHang();
//    kh.setMaKH(rs.getInt(1));
//    kh.setHo(rs.getString(2));
//    kh.setTen(rs.getString(3));
//    kh.setGioiTinh(rs.getString(4));
//    kh.setTongChiTieu(rs.getInt(5));
//}
//} catch (SQLException ex) {
//return null;
//}
//return kh;
//}

//It's working :>>
	public KhachHang getKhachHang(int maKH) {
		KhachHang kh = null;
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
			String sql = "Select e from " + KhachHang.class.getName() + " e where e.maKH = :maKH";
//String sql = "SELECT * FROM khachhang WHERE MaKH=? AND TinhTrang=1";
			Query<KhachHang> query = session.createQuery(sql);
			query.setParameter("maKH", maKH);
			kh = query.getSingleResult();
			session.getTransaction().commit();
			return kh;
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

//Works
	public boolean addKhachHang(KhachHang kh) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
			String sql = "INSERT INTO khachhang VALUES(:MaKH,:Ho,:Ten,:GioiTinh,:TongChiTieu,1)";
			Query<KhachHang> query = session.createNativeQuery(sql);
			query.setParameter("MaKH", kh.getMaKH());
			query.setParameter("Ho", kh.getHo());
			query.setParameter("Ten", kh.getTen());
			query.setParameter("GioiTinh", kh.getGioiTinh());
			query.setParameter("TongChiTieu", kh.getTongChiTieu());
			int count = query.executeUpdate();
			System.out.println(count);
			if (count > 0) {
				session.getTransaction().commit();
				System.out.println("abc");
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

//Yay, it worked
	public boolean deleteKhachHang(int maKH) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
			String sql = "Delete from " + KhachHang.class.getName() + " d" + " where d.maKH like :MaKH";
			Query<KhachHang> query = session.createQuery(sql);
			query.setParameter("MaKH", maKH);
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

//Works
	public boolean updateKhachHang(int maKH, KhachHang kh) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
			String sql = "UPDATE khachhang SET Ho=:Ho, Ten=:Ten, GioiTinh=:GioiTinh WHERE MaKH=:MaKH";
			Query<KhachHang> query = session.createNativeQuery(sql);
			query.setParameter("Ho", kh.getHo());
			query.setParameter("Ten", kh.getTen());
			query.setParameter("GioiTinh", kh.getGioiTinh());
			query.setParameter("MaKH", maKH);
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

//Thumbs up
	public boolean updateTongChiTieu(int maKH, int tongChiTieu) {
		boolean result = false;
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
//      String sql = "UPDATE khachhang SET TongChiTieu=" + tongChiTieu + " WHERE MaKH=" + maKH;
			String sql = "UPDATE khachhang SET TongChiTieu= :TongChiTieu WHERE MaKH= :MaKH";
			Query<KhachHang> query = session.createNativeQuery(sql);
			query.setParameter("TongChiTieu", tongChiTieu);
			query.setParameter("MaKH", maKH);
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
