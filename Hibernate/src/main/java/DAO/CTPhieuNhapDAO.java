package DAO;

import DTO.CTPhieuNhap;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import DTO.HibernateUtils;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CTPhieuNhapDAO {

//    public ArrayList<CTPhieuNhap> getListCTPhieuNhap() {
//        ArrayList<CTPhieuNhap> dsctpn = new ArrayList<CTPhieuNhap>();
//        try {
//            String sql = "SELECT * FROM ctphieunhap";
//            Statement stmt = MyConnect.conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                CTPhieuNhap ctpn = new CTPhieuNhap();
//                ctpn.setMaPN(rs.getInt(1));
//                ctpn.setMaSP(rs.getInt(2));
//                ctpn.setSoLuong(rs.getInt(3));
//                ctpn.setDonGia(rs.getInt(4));
//                ctpn.setThanhTien(rs.getInt(5));
//                dsctpn.add(ctpn);
//            }
//        } catch (SQLException ex) {
//            return null;
//        }
//        return dsctpn;
//    }
//
//    public ArrayList<CTPhieuNhap> getListCTPhieuNhapTheoMaPN(int maPN) {
//        ArrayList<CTPhieuNhap> dsctpn = new ArrayList<CTPhieuNhap>();
//        try {
//            String sql = "SELECT * FROM ctphieunhap WHERE MaPN=" + maPN;
//            Statement stmt = MyConnect.conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                CTPhieuNhap ctpn = new CTPhieuNhap();
//                ctpn.setMaPN(rs.getInt(1));
//                ctpn.setMaSP(rs.getInt(2));
//                ctpn.setSoLuong(rs.getInt(3));
//                ctpn.setDonGia(rs.getInt(4));
//                ctpn.setThanhTien(rs.getInt(5));
//                dsctpn.add(ctpn);
//            }
//        } catch (SQLException ex) {
//            return null;
//        }
//        return dsctpn;
//    }
//
//    public ArrayList<CTPhieuNhap> getListCTPhieuNhapTheoMaSP(int maSP) {
//        ArrayList<CTPhieuNhap> dsctpn = new ArrayList<CTPhieuNhap>();
//        try {
//            String sql = "SELECT * FROM ctphieunhap WHERE MaSP=" + maSP;
//            Statement stmt = MyConnect.conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                CTPhieuNhap ctpn = new CTPhieuNhap();
//                ctpn.setMaPN(rs.getInt(1));
//                ctpn.setMaSP(rs.getInt(2));
//                ctpn.setSoLuong(rs.getInt(3));
//                ctpn.setDonGia(rs.getInt(4));
//                ctpn.setThanhTien(rs.getInt(5));
//                dsctpn.add(ctpn);
//            }
//        } catch (SQLException ex) {
//            return null;
//        }
//        return dsctpn;
//    }
//
//    public boolean addCTPhieuNhap(CTPhieuNhap ctpn) {
//        boolean result = false;
//        try {
//            // Phải Update số lượng SP trong kho
//            String sqlUpdateSP = "UPDATE SanPham SET SoLuong = SoLuong + ? WHERE MaSP = ?";
//            PreparedStatement pre = MyConnect.conn.prepareCall(sqlUpdateSP);
//            pre.setInt(1, ctpn.getSoLuong());
//            pre.setInt(2, ctpn.getMaSP());
//            pre.executeUpdate();
//
//            String sql = "INSERT INTO ctphieunhap VALUES(?,?,?,?,?)";
//            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
//            prep.setInt(1, ctpn.getMaPN());
//            prep.setInt(2, ctpn.getMaSP());
//            prep.setInt(3, ctpn.getSoLuong());
//            prep.setInt(4, ctpn.getDonGia());
//            prep.setInt(5, ctpn.getThanhTien());
//            result = prep.executeUpdate() > 0;
//        } catch (SQLException ex) {
//            return false;
//        }
//        return result;
//    }
//
//    public boolean deleteCTPhieuNhap(int maPN) {
//        boolean result = false;
//        try {
//            String sql = "DELETE FROM ctphieunhap WHERE MaPN=" + maPN;
//            Statement stmt = MyConnect.conn.createStatement();
//            result = stmt.executeUpdate(sql) > 0;
//        } catch (SQLException ex) {
//            return false;
//        }
//        return result;
//    }
//
//    public boolean deleteCTPhieuNhap(int maPN, int maSP) {
//        boolean result = false;
//        try {
//            String sql = "DELETE FROM ctphieunhap WHERE MaPN=" + maPN + " AND MaSP=" + maSP;
//            Statement stmt = MyConnect.conn.createStatement();
//            result = stmt.executeUpdate(sql) > 0;
//        } catch (SQLException ex) {
//            return false;
//        }
//        return result;
//    }
//
//    public boolean updateCTPhieuNhap(int maPN, CTPhieuNhap ctpn) {
//        boolean result = false;
//        try {
//            String sql = "UPDATE ctphieunhap SET MaPN=?, MaSP=?, SoLuong=?, DonGia=?, ThanhTien=? WHERE MaPN=?";
//            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
//            prep.setInt(1, ctpn.getMaPN());
//            prep.setInt(2, ctpn.getMaSP());
//            prep.setInt(3, ctpn.getSoLuong());
//            prep.setInt(4, ctpn.getDonGia());
//            prep.setInt(5, ctpn.getThanhTien());
//            prep.setInt(6, maPN);
//            result = prep.executeUpdate() > 0;
//        } catch (SQLException ex) {
//            return false;
//        }
//        return result;
//    }
	public ArrayList<CTPhieuNhap> getListCTPhieuNhap() {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		ArrayList<CTPhieuNhap> dsctpn = new ArrayList<>();
		try {

			session.getTransaction().begin();
			String sql = "Select e from " + CTPhieuNhap.class.getName() + " e ";
			Query<CTPhieuNhap> query = session.createQuery(sql);
			List<CTPhieuNhap> danhsachchitietphieunhap = query.getResultList();
			for (CTPhieuNhap ctpn : danhsachchitietphieunhap) {
				CTPhieuNhap chitietphieunhap = new CTPhieuNhap();
				chitietphieunhap.setMaPN(ctpn.getMaPN());
				chitietphieunhap.setMaSP(ctpn.getMaSP());
				chitietphieunhap.setSoLuong(ctpn.getSoLuong());
				chitietphieunhap.setDonGia(ctpn.getDonGia());
				chitietphieunhap.setThanhTien(ctpn.getThanhTien());
				dsctpn.add(chitietphieunhap);
			}
			session.getTransaction().commit();
			return dsctpn;
		} catch (Exception e) {
		}

		return dsctpn;
	}

// works
	public ArrayList<CTPhieuNhap> getListCTPhieuNhapTheoMaPN(int maPN) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		ArrayList<CTPhieuNhap> dsctpn = new ArrayList<CTPhieuNhap>();
		try {
			session.getTransaction().begin();
			String sql = "SELECT e FROM " + CTPhieuNhap.class.getName() + " e WHERE e.maPN= :MaPN";
			Query<CTPhieuNhap> query = session.createQuery(sql);
			query.setParameter("MaPN", maPN);
			List<CTPhieuNhap> danhsachchitietphieunhap = query.getResultList();
			for (CTPhieuNhap ctpn : danhsachchitietphieunhap) {
				CTPhieuNhap chitietphieunhap = new CTPhieuNhap();
				chitietphieunhap.setMaPN(ctpn.getMaPN());
				chitietphieunhap.setMaSP(ctpn.getMaSP());
				chitietphieunhap.setSoLuong(ctpn.getSoLuong());
				chitietphieunhap.setDonGia(ctpn.getDonGia());
				chitietphieunhap.setThanhTien(ctpn.getThanhTien());
				dsctpn.add(chitietphieunhap);
			}
			session.getTransaction().commit();
			return dsctpn;
		} catch (Exception e) {
		}

		return dsctpn;
	}

//works
	public ArrayList<CTPhieuNhap> getListCTPhieuNhapTheoMaSP(int maSP) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		ArrayList<CTPhieuNhap> dsctpn = new ArrayList<CTPhieuNhap>();
		try {
			session.getTransaction().begin();
			String sql = "SELECT e FROM " + CTPhieuNhap.class.getName() + " e WHERE e.maSP= :MaSP";
			Query<CTPhieuNhap> query = session.createQuery(sql);
			query.setParameter("MaSP", maSP);
			List<CTPhieuNhap> danhsachchitietphieunhap = query.getResultList();
			for (CTPhieuNhap ctpn : danhsachchitietphieunhap) {
				CTPhieuNhap chitietphieunhap = new CTPhieuNhap();
				chitietphieunhap.setMaPN(ctpn.getMaPN());
				chitietphieunhap.setMaSP(ctpn.getMaSP());
				chitietphieunhap.setSoLuong(ctpn.getSoLuong());
				chitietphieunhap.setDonGia(ctpn.getDonGia());
				chitietphieunhap.setThanhTien(ctpn.getThanhTien());
				dsctpn.add(chitietphieunhap);
			}
			session.getTransaction().commit();
			return dsctpn;
		} catch (Exception e) {
		}

		return dsctpn;
	}

//I have no idea how, BUT IT WORKS!!!
	public boolean addCTPhieuNhap(CTPhieuNhap ctpn) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		boolean result = false;
		try {
			// Phải Update số lượng SP trong kho
			session.getTransaction().begin();
			String sqlUpdateSP = "UPDATE SanPham SET SoLuong = SoLuong + :SoLuong WHERE MaSP = :MaSP";
			Query<CTPhieuNhap> query = session.createNativeQuery(sqlUpdateSP);
			query.setParameter("SoLuong", ctpn.getSoLuong());
			query.setParameter("MaSP", ctpn.getMaSP());
			query.executeUpdate();

			String sql = "INSERT INTO ctphieunhap VALUES(:MaPN,:MaSP1,:SoLuong,:DonGia,:ThanhTien)";
			Query<CTPhieuNhap> query1 = session.createNativeQuery(sql);
			query1.setParameter("MaPN", ctpn.getMaPN());
			query1.setParameter("MaSP1", ctpn.getMaSP());
			query1.setParameter("SoLuong", ctpn.getSoLuong());
			query1.setParameter("DonGia", ctpn.getDonGia());
			query1.setParameter("ThanhTien", ctpn.getThanhTien());
			result = query1.executeUpdate() > 0;
			session.getTransaction().commit();
		} catch (Exception ex) {
			return false;
		}
		return result;
	}

//HOW DAFUQ DOES THIS WORK????
	public boolean deleteCTPhieuNhap(int maPN) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		boolean result = false;
		try {
			session.getTransaction().begin();
			String sql = "DELETE FROM " + CTPhieuNhap.class.getName() + " E WHERE E.maPN=:MaPN";
			Query<CTPhieuNhap> query = session.createQuery(sql);
			query.setParameter("MaPN", maPN);
			result = query.executeUpdate() > 0;
			session.getTransaction().commit();
		} catch (Exception ex) {
			return false;
		}
		return result;
	}

// works
	public boolean deleteCTPhieuNhap(int maPN, int maSP) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		boolean result = false;
		try {
			session.getTransaction().begin();
			String sql = "DELETE FROM " + CTPhieuNhap.class.getName() + " E WHERE E.maPN=:MaPN AND E.maSP = :MaSP";
			Query<CTPhieuNhap> query = session.createQuery(sql);
			query.setParameter("MaPN", maPN);
			query.setParameter("MaSP", maSP);
			result = query.executeUpdate() > 0;
			System.out.println("abc");
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			return false;

		}
		return result;
	}

// don't get how this function works... => COULDN'T DO THIS ONE
	public boolean updateCTPhieuNhap(int maPN, CTPhieuNhap ctpn) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		boolean result = false;
		try {
			session.getTransaction().begin();
			String sql = "UPDATE ctphieunhap SET MaPN=:MaPN, MaSP=:MaSP, SoLuong=:SoLuong, DonGia=:DonGia, ThanhTien=:ThanhTien WHERE MaPN=:MaPN";
			Query<CTPhieuNhap> query = session.createNativeQuery(sql);
			query.setParameter("MaPN", maPN);
			query.setParameter("MaSP", ctpn.getMaSP());
			query.setParameter("SoLuong", ctpn.getSoLuong());
			query.setParameter("DonGia", ctpn.getDonGia());
			query.setParameter("ThanhTien", ctpn.getThanhTien());
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
