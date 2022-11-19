package DAO;

import DTO.HibernateUtils;
import DTO.HoaDon;
import DTO.KhachHang;
import DTO.NhanVien;
import DTO.SanPham;
import DTO.ThongKe;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.type.LongType;

import BUS.SanPhamBUS;
import BUS.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author User
 */
public class ThongKeDAO {
	
	
	public ThongKe getThongKe(int nam) {
	ThongKe thongKe = new ThongKe();
		int[] tongThuQuy = new int[4];
		thongKe.setSoLuongSP(getTongSoLuongSP());
		thongKe.setSoLuongKH(getSoLuongKhachHang());
		thongKe.setSoLuongNV(getSoLuongNhanVien());
		tongThuQuy[0] = getTongThuQuy(nam, 1);
		tongThuQuy[1] = getTongThuQuy(nam, 2);
		tongThuQuy[2] = getTongThuQuy(nam, 3);
		tongThuQuy[3] = getTongThuQuy(nam, 4);
		thongKe.setTongThuQuy(tongThuQuy);
		thongKe.setTopSanPhamBanChay(getTopBanChay());
		return thongKe;
	}
//
//    public ArrayList<SanPham> getTopBanChay() {
//        try {
//            String sql = "SELECT MaSP, DaBan FROM (" +
//                    "SELECT MaSP, SUM(SoLuong) AS DaBan FROM " +
//                    "cthoadon GROUP BY MaSP" +
//                    ") temp " +
//                    "ORDER BY DaBan " +
//                    "DESC LIMIT 5";
//            Statement st = MyConnect.conn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            ArrayList<SanPham> dssp = new ArrayList<SanPham>();
//            SanPhamBUS spBUS = new SanPhamBUS();
//            while (rs.next()) {
//                SanPham sp = new SanPham();
//                sp.setMaSP(rs.getInt(1));
//                sp.setSoLuong(rs.getInt(2));
//                sp.setTenSP(spBUS.getTenSP(sp.getMaSP()));
//                dssp.add(sp);
//            }
//            return dssp;
//        } catch (Exception e) {
//        }
//        return null;
//    }

	public ArrayList<SanPham> getTopBanChay() {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		ArrayList<SanPham>	dssp = new ArrayList<SanPham>();
		try {
			session.getTransaction().begin();
			String sql = "select sanpham.MaSP,sanpham.TenSP,sanpham.MaLoai,sanpham.SoLuong,sanpham.DonViTinh,sanpham.HinhAnh,sanpham.DonGia from sanpham inner JOIN (SELECT maSP, SUM(SoLuong) AS daBan FROM cthoadon GROUP BY MaSP ORDER BY DaBan DESC LIMIT 5) as temp ON sanpham.MaSP=temp.maSP";
			Query<Object[]> query = session.createNativeQuery(sql);
			List<Object[]> danhsachsanpham = query.getResultList();
		
			for(Object[] spham:danhsachsanpham) {
				SanPham sp= new SanPham();
				sp.setMaSP((int) spham[0]);
				sp.setTenSP((String) spham[1]);
				sp.setMaLoai((int) spham[2]);
				sp.setSoLuong((int) spham[3]);
				sp.setDonViTinh((String) spham[4]);
				sp.setHinhAnh((String) spham[5]);
				sp.setDonGia((int) spham[6]);
				dssp.add(sp);
			}
			session.getTransaction().commit();
			return dssp;
		} catch (Exception e) {
		}
		return dssp;
	}

	public int getTongSoLuongSP() {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		long tongsanpham = 0;
		try {
			if(!session.getTransaction().isActive()) {
			session.getTransaction().begin();
			String sql = "select count(e) from " + SanPham.class.getName() + " e ";
			Query<Long> query = session.createQuery(sql);
			
			tongsanpham=query.uniqueResult();
			if(query.uniqueResult()==null) {
				tongsanpham=0;
				
				}
			session.getTransaction().commit();
			return (int) tongsanpham;}
			return (int) tongsanpham;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			return (int) -1;
		}
		
	}

	private String[] getDateString(int nam, int quy) {
		int namBatDau = nam;
		int namKetThuc = nam;
		String thangBatDau = "01"; // kiểu String do có số 0 ở phía trước
		String thangKetThuc = "04"; // kiểu String do có số 0 ở phía trước
		String[] kq = new String[2];
		switch (quy) {
		case 1:
			thangBatDau = "01";
			thangKetThuc = "04";
			break;
		case 2:
			thangBatDau = "03";
			thangKetThuc = "07";
			break;
		case 3:
			thangBatDau = "06";
			thangKetThuc = "10";
			break;
		case 4:
			thangBatDau = "09";
			thangKetThuc = "01";
			namKetThuc++;
		}
		String strBatDau = Integer.toString(namBatDau) + thangBatDau + "01";
		String strKetThuc = Integer.toString(namKetThuc) + thangKetThuc + "01";
		kq[0] = strBatDau;
		kq[1] = strKetThuc;
		return kq;
	}

	public int getTongThuQuy(int nam, int quy) {
		String[] dateString = getDateString(nam, quy);
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		long result=(long) 0;
		try {
			session.getTransaction().begin();
			String sql = "SELECT SUM(TongTien) as sum FROM hoadon WHERE NgayLap >= :ngaylap AND NgayLap < :ngaylap1";
			Query<Long> query = session.createNativeQuery(sql).addScalar("sum", LongType.INSTANCE);
			query.setString("ngaylap", dateString[0]);
			query.setString("ngaylap1", dateString[1]);
			long tongdoanhthu = 0;
			if( query.uniqueResult() != null) {
				tongdoanhthu = query.uniqueResult();
				//result = tongdoanhthu;
				
			}
			session.getTransaction().commit();
			return (int) tongdoanhthu;
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			return 0;
		}
		
	}

	private int getSoLuongNhanVien() {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		long tongnhanvien = 0;
		try {
			if(!session.getTransaction().isActive()) {
			session.getTransaction().begin();
			String sql = "select count(e) from " + NhanVien.class.getName() + " e ";
			Query<Long> query = session.createQuery(sql);
			
			tongnhanvien=query.uniqueResult();
			if(query.uniqueResult()==null) {
				tongnhanvien=0;
				
				}
			session.getTransaction().commit();
			return (int) tongnhanvien;}
			return (int) tongnhanvien;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			return (int) -1;
		}
	}

	private int getSoLuongKhachHang() {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		long tongkhachhang = 0;
		try {
			if(!session.getTransaction().isActive()) {
			session.getTransaction().begin();
			String sql = "select count(e) from " + KhachHang.class.getName() + " e ";
			Query<Long> query = session.createQuery(sql);
			
			tongkhachhang=query.uniqueResult();
			if(query.uniqueResult()==null) {
				tongkhachhang=0;
				
				}
			session.getTransaction().commit();
			return (int) tongkhachhang;}
			return (int) tongkhachhang;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			return (int) -1;
		}
	}

	public double getDoanhThuThang(int thang, int nam) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			if(!session.getTransaction().isActive()) {
				session.getTransaction().begin();
			String thangBD = nam + "-" + thang + "-01";
			String thangKT = nam + "-" + (thang + 1) + "-01";
			String sql = "SELECT SUM(TongTien) as sum FROM hoadon WHERE NgayLap BETWEEN CAST(:ngaylap AS DATE) AND CAST(:ngaylap1 AS DATE)";
			Query<Double> query = session.createNativeQuery(sql).addScalar("sum", LongType.INSTANCE);
			query.setString("ngaylap", thangBD);
			query.setString("ngaylap1", thangKT);
			double doanhthu=0;
			if(query.getSingleResult()!=null) {
				doanhthu=query.getSingleResult();
			}
			session.getTransaction().commit();
			return (int) doanhthu;
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nam;
	}
//
//	public double abc(int thang, int nam) {
//		try {
//			String d1 = nam + "-" + thang + "-01";
//			String d2 = nam + "-" + (thang + 1) + "-01";
//			String sql = "SELECT SUM(TongTien) FROM hoadon WHERE NgayLap BETWEEN CAST(? AS DATE) AND CAST(? AS DATE)";
//
//			PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
//			pre.setString(1, d1);
//			pre.setString(2, d2);
//			ResultSet rs = pre.executeQuery();
//
//			while (rs.next())
//				return rs.getDouble(1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return 0.0f;
//	}
//	
}
