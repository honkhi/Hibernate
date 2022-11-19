package DAO;

import DTO.HibernateUtils;
import DTO.HoaDon;
import DTO.PhieuNhap;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class PhieuNhapDAO {

//    public ArrayList<PhieuNhap> getListPhieuNhap() {
//        ArrayList<PhieuNhap> dspn = new ArrayList<PhieuNhap>();
//        try {
//            String sql = "SELECT * FROM phieunhap";
//            Statement stmt = MyConnect.conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                PhieuNhap pn = new PhieuNhap();
//                pn.setMaPN(rs.getInt(1));
//                pn.setMaNCC(rs.getInt(2));
//                pn.setMaNV(rs.getInt(3));
//                pn.setNgayLap(rs.getDate(4));
//                pn.setTongTien(rs.getInt(5));
//                dspn.add(pn);
//            }
//        } catch (SQLException ex) {
//            return null;
//        }
//        return dspn;
//    }
	  public ArrayList<PhieuNhap> getListPhieuNhap() {
	    	SessionFactory factory = HibernateUtils.getSessionFactory();
			Session session = factory.getCurrentSession();
			ArrayList<PhieuNhap> dspn = new ArrayList<PhieuNhap>();
	        try {
	        	session.getTransaction().begin();
				String sql = "Select e from " + PhieuNhap.class.getName() + " e ";
	            Query<PhieuNhap> query = session.createQuery(sql);
				List<PhieuNhap> danhsachphieunhap = query.getResultList();
	            
	           for(PhieuNhap pn :danhsachphieunhap) {
	        	   PhieuNhap phieunhap = new PhieuNhap();
	        	   phieunhap.setMaPN(pn.getMaPN());
	        	   phieunhap.setMaNCC(pn.getMaNCC());
	        	   phieunhap.setMaNV(pn.getMaNV());
	        	   phieunhap.setNgayLap(pn.getNgayLap());
	        	   phieunhap.setTongTien(pn.getTongTien());

	        	   dspn.add(phieunhap);
	           }
	           session.getTransaction().commit();
	            return dspn;
	        } catch (Exception e) {

	        }
	        return dspn;
		}

//    public boolean themPhieuNhap(PhieuNhap pn) {
//        boolean result = false;
//        try {
//            String sql = "INSERT INTO phieunhap(MaNCC, MaNV, NgayLap, TongTien) "
//                    + "VALUES(?,?,?,?)";
//            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
//            prep.setInt(1, pn.getMaNCC());
//            prep.setInt(2, pn.getMaNV());
//            prep.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
//            prep.setInt(4, pn.getTongTien());
//            result = prep.executeUpdate() > 0;
//        } catch (SQLException ex) {
//            return false;
//        }
//        return result;
//    }
	  public boolean themPhieuNhap(PhieuNhap pn) {
	    	SessionFactory factory = HibernateUtils.getSessionFactory();
			Session session = factory.getCurrentSession();
	        boolean result = false;
	        try {
	        	session.getTransaction().begin();
	            String sql = "INSERT INTO phieunhap(MaNCC, MaNV, NgayLap, TongTien) "
	                    + "VALUES(:MaNCC,:MaNV,:NgayLap,:TongTien)";
	            Query<PhieuNhap> query = session.createNativeQuery(sql);
	            query.setParameter("MaNCC", pn.getMaNCC());
	            query.setParameter("MaNV", pn.getMaNV());
	            query.setTimestamp("NgayLap", new java.sql.Timestamp(new java.util.Date().getTime()));
	            query.setParameter("TongTien", pn.getTongTien());
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

//    public PhieuNhap getPhieuNhap(int maPN) {
//        PhieuNhap pn = null;
//        try {
//            String sql = "SELECT * FROM phieunhap WHERE MaPN=" + maPN;
//            Statement stmt = MyConnect.conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                pn = new PhieuNhap();
//                pn.setMaPN(rs.getInt(1));
//                pn.setMaNCC(rs.getInt(2));
//                pn.setMaNV(rs.getInt(3));
//                pn.setNgayLap(rs.getDate(4));
//                pn.setTongTien(rs.getInt(5));
//            }
//        } catch (SQLException ex) {
//            return null;
//        }
//        return pn;
//    }
//
//    public boolean deletePhieuNhap(int maPN) {
//        boolean result = false;
//        try {
//            String sql = "DELETE FROM phieunhap WHERE MaPN=" + maPN;
//            Statement stmt = MyConnect.conn.createStatement();
//            result = stmt.executeUpdate(sql) > 0;
//        } catch (SQLException ex) {
//            return false;
//        }
//        return result;
//    }

	    public PhieuNhap getPhieuNhap(int maPN) {
//	    	SessionFactory factory = HibernateUtils.getSessionFactory();
//			Session session = factory.getCurrentSession();
//	        PhieuNhap pn = null;
//	        try {
//	        	session.getTransaction().begin();
//	            String sql = "SELECT * FROM PhieuNhap  WHERE MaPN=:MaPN";
//	            Query<PhieuNhap> query = session.createNativeQuery(sql);
//	            query.setParameter("MaPN", maPN);
//	    		pn = query.getSingleResult();
//	    		session.getTransaction().commit();
//	    		return pn;
//	    	} catch (Exception ex) {
//	    		ex.printStackTrace();
//	    		session.getTransaction().rollback();
//	    		return null;
//	    	}
	        PhieuNhap pn = null;
	    	SessionFactory factory = HibernateUtils.getSessionFactory();
	    	Session session = factory.getCurrentSession();
	    	try {
	    		session.getTransaction().begin();
	    		String sql = "Select e from "+ PhieuNhap.class.getName() + " e where e.maPN = :maPN";
//	          String sql = "SELECT * FROM khachhang WHERE MaKH=? AND TinhTrang=1";
	    		Query<PhieuNhap> query = session.createQuery(sql);
	    		query.setParameter("maPN", maPN);
	    		pn = query.getSingleResult();
	    		session.getTransaction().commit();
	    		return pn;
	    	} catch (Exception ex) {
	    		ex.printStackTrace();
	    		session.getTransaction().rollback();
	    		return null;
	    	}
	    }

	    //Works
	    public boolean deletePhieuNhap(int maPN) {
	    	SessionFactory factory = HibernateUtils.getSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				session.getTransaction().begin();
				String sql = "Delete from " +PhieuNhap.class.getName()+ " d" + " where d.maPN like :maPN";
				Query<PhieuNhap> query = session.createQuery(sql);
				query.setParameter("maPN", maPN);
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
	    
//    public boolean updatePhieuNhap(int maPN, PhieuNhap pn) {
//        boolean result = false;
//        try {
//            String sql = "UPDATE phieunhap SET MaPN=?, MaNCC=?, MaNV=?, NgayLap=?, TongTien=? "
//                    + "WHERE MaPN=" + maPN;
//            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
//            prep.setInt(1, pn.getMaPN());
//            prep.setInt(2, pn.getMaNCC());
//            prep.setInt(3, pn.getMaNV());
//            prep.setDate(4, new java.sql.Date(pn.getNgayLap().getTime()));
//            prep.setInt(5, pn.getTongTien());
//            result = prep.executeUpdate() > 0;
//        } catch (SQLException ex) {
//            return false;
//        }
//        return result;
//    }
	    public boolean updatePhieuNhap(int maPN, PhieuNhap pn) {
	    	SessionFactory factory = HibernateUtils.getSessionFactory();
			Session session = factory.getCurrentSession();
	        try {
	        	session.getTransaction().begin();
	        	System.out.println("before query");
//	            String sql = "UPDATE phieunhap SET MaPN= :mapn, MaNCC=:MaNCC, MaNV=:MaNV, NgayLap=:NgayLap, TongTien=:TongTien "
//	                    + "WHERE MaPN= :mapn";
	            String sql = "Update " + PhieuNhap.class.getName() + " d "
						+ "Set d.maPN = :MaPN , d.maNCC = :MaNCC , d.maNV = :MaNV , d.ngayLap = :NgayLap, d.tongTien = :TongTien where d.maPN = :MaPN";
	            Query<PhieuNhap> query = session.createQuery(sql);
	            query.setParameter("MaPN", maPN);
	            query.setParameter("MaNCC", pn.getMaNCC());
	            query.setParameter("MaNV", pn.getMaNV());
	            query.setDate("NgayLap", new java.sql.Date(pn.getNgayLap().getTime()));
	            query.setParameter("TongTien", pn.getTongTien());
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
				session.getTransaction().rollback();
				return false;
			}
	    }

    public int getLastID() {
    	SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
        try {
        	session.getTransaction().begin();
        	  String sql = "SELECT MAX(e.maPN) FROM " + PhieuNhap.class.getName() + " e ";
            //String sql = "SELECT MAX(maPN) FROM phieunhap";
        	  Query<Integer> query = session.createQuery(sql);
              int result = query.getSingleResult();
              session.getTransaction().commit();
              return result;
          } catch (Exception e) {
              e.printStackTrace();
              session.getTransaction().rollback();
              return -1;
          }
    }
}
