/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DTO.CTHoaDon;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
/**
 *
 * @author User
 */
public class CTHoaDonDAO {

//    public ArrayList<CTHoaDon> getListCTHoaDon() {
//        ArrayList<CTHoaDon> dscthd = new ArrayList<CTHoaDon>();
//        try {
//            String sql = "SELECT * FROM cthoadon";
//            Statement stmt = MyConnect.conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while(rs.next()) {
//                CTHoaDon cthd = new CTHoaDon();
//                cthd.setMaHD(rs.getInt(1));
//                cthd.setMaSP(rs.getInt(2));
//                cthd.setSoLuong(rs.getInt(3));
//                cthd.setDonGia(rs.getInt(4));
//                cthd.setThanhTien(rs.getInt(5));
//                dscthd.add(cthd);
//            }
//        } catch(SQLException ex) {
//        }
//        return dscthd;
//    }
//
//    public ArrayList<CTHoaDon> getListCTHoaDonTheoMaHD(int maHD) {
//        ArrayList<CTHoaDon> dscthd = new ArrayList<CTHoaDon>();
//        try {
//            String sql = "SELECT * FROM cthoadon WHERE MaHD="+maHD;
//            Statement stmt = MyConnect.conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while(rs.next()) {
//                CTHoaDon cthd = new CTHoaDon();
//                cthd.setMaHD(rs.getInt(1));
//                cthd.setMaSP(rs.getInt(2));
//                cthd.setSoLuong(rs.getInt(3));
//                cthd.setDonGia(rs.getInt(4));
//                cthd.setThanhTien(rs.getInt(5));
//                dscthd.add(cthd);
//            }
//        } catch(SQLException ex) {
//            return null;
//        }
//        return dscthd;
//    }
//
//    public ArrayList<CTHoaDon> getListCTHoaDonTheoMaSP(int maSP) {
//        ArrayList<CTHoaDon> dscthd = new ArrayList<CTHoaDon>();
//        try {
//            String sql = "SELECT * FROM cthoadon WHERE MaSP="+maSP;
//            Statement stmt = MyConnect.conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while(rs.next()) {
//                CTHoaDon cthd = new CTHoaDon();
//                cthd.setMaHD(rs.getInt(1));
//                cthd.setMaSP(rs.getInt(2));
//                cthd.setSoLuong(rs.getInt(3));
//                cthd.setDonGia(rs.getInt(4));
//                cthd.setThanhTien(rs.getInt(5));
//                dscthd.add(cthd);
//            }
//        } catch(SQLException ex) {
//            return null;
//        }
//        return dscthd;
//    }
//
//    public boolean addCTHoaDon(CTHoaDon cthd) {
//        boolean result = false;
//        try {
//            String sql = "INSERT INTO cthoadon VALUES(?,?,?,?,?)";
//            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
//            prep.setInt(1, cthd.getMaHD());
//            prep.setInt(2, cthd.getMaSP());
//            prep.setInt(3, cthd.getSoLuong());
//            prep.setInt(4, cthd.getDonGia());
//            prep.setInt(5, cthd.getThanhTien());
//            result = prep.executeUpdate() > 0;
//        } catch(SQLException ex) {
//            ex.printStackTrace();
//            return false;
//        }
//        return result;
//    }
//
//    public boolean deleteCTHoaDon(int maHD, int maSP) {
//        boolean result = false;
//        try {
//            String sql = "DELETE FROM cthoadon WHERE MaHD="+maHD+" AND MaSP="+maSP;
//            Statement stmt = MyConnect.conn.createStatement();
//            result = stmt.executeUpdate(sql) > 0;
//        } catch(SQLException ex) {
//            return false;
//        }
//        return result;
//    }
//
//    public boolean deleteCTHoaDon(int maHD) {
//        boolean result = false;
//        try {
//            String sql = "DELETE FROM cthoadon WHERE MaHD="+maHD;
//            Statement stmt = MyConnect.conn.createStatement();
//            result = stmt.executeUpdate(sql) > 0;
//        } catch(SQLException ex) {
//            return false;
//        }
//        return result;
//    }
//
//    public boolean updateCTHoaDon(int maHD, int maSP, CTHoaDon cthd) {
//        boolean result = false;
//        try {
//            String sql = "UPDATE cthoadon SET MaHD=?, MaSP=?, SoLuong=?, DonGia=? ThanhTien=? "
//                    + "WHERE MaHD=? AND MaSP=?";
//            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
//            prep.setInt(1, cthd.getMaHD());
//            prep.setInt(2, cthd.getMaSP());
//            prep.setInt(3, cthd.getSoLuong());
//            prep.setInt(4, cthd.getDonGia());
//            prep.setInt(5, cthd.getThanhTien());
//            prep.setInt(6, maHD);
//            prep.setInt(7, maSP);
//            result = prep.executeUpdate() > 0;
//        } catch(SQLException ex) {
//            return false;
//        }
//        return result;
//    }
	 public ArrayList<CTHoaDon> getListCTHoaDon() {
	    	SessionFactory factory = HibernateUtils.getSessionFactory();
			Session session = factory.getCurrentSession();
	        ArrayList<CTHoaDon> dscthd = new ArrayList<CTHoaDon>();
	        try {
				session.getTransaction().begin();
	            String sql = "SELECT e FROM " + CTHoaDon.class.getName() + " e";
	            Query<CTHoaDon> query = session.createQuery(sql);
				List<CTHoaDon> danhsachhoadon = query.getResultList();
				for(CTHoaDon cthd : danhsachhoadon) {
					CTHoaDon cthoadon = new CTHoaDon();
					cthoadon.setMaHD(cthd.getMaHD());
					cthoadon.setMaSP(cthd.getMaSP());
					cthoadon.setSoLuong(cthd.getSoLuong());
					cthoadon.setDonGia(cthd.getDonGia());
					cthoadon.setThanhTien(cthd.getThanhTien());
					dscthd.add(cthoadon);
				}
				session.getTransaction().commit();
				return dscthd;
			} catch (Exception e) {
			}

			return dscthd;
	    }

	    
	    //Works
	    public ArrayList<CTHoaDon> getListCTHoaDonTheoMaHD(int maHD) {
	    	SessionFactory factory = HibernateUtils.getSessionFactory();
			Session session = factory.getCurrentSession();
	        ArrayList<CTHoaDon> dscthd = new ArrayList<CTHoaDon>();
	        try {
	        	session.getTransaction().begin();
	        	String sql = "Select e from " +CTHoaDon.class.getName() + " e " + " where e.maHD LIKE :maHD";
//	            String sql = "SELECT * FROM cthoadon WHERE MaHD="+maHD;
	        	Query<CTHoaDon> query = session.createQuery(sql);
	        	query.setParameter("maHD", maHD);
	        	List<CTHoaDon> danhsachhoadon = query.getResultList();
				
				for(CTHoaDon cthd : danhsachhoadon) {
					CTHoaDon cthoadon = new CTHoaDon();
					cthoadon.setMaHD(cthd.getMaHD());
					cthoadon.setMaSP(cthd.getMaSP());
					cthoadon.setSoLuong(cthd.getSoLuong());
					cthoadon.setDonGia(cthd.getDonGia());
					cthoadon.setThanhTien(cthd.getThanhTien());
					dscthd.add(cthoadon);
				}
				session.getTransaction().commit();
				return dscthd;

	        } catch(Exception ex) {
	        }
	        return dscthd;
	    }
	// works
	    public ArrayList<CTHoaDon> getListCTHoaDonTheoMaSP(int maSP) {
	    	SessionFactory factory = HibernateUtils.getSessionFactory();
			Session session = factory.getCurrentSession();
	        ArrayList<CTHoaDon> dscthd = new ArrayList<CTHoaDon>();
	        try {
	        	session.getTransaction().begin();
	            String sql = "SELECT e FROM " +CTHoaDon.class.getName() + " e WHERE e.maSP like :maSP";
	            Query<CTHoaDon> query = session.createQuery(sql);
	            query.setParameter("maSP", maSP);
	        	List<CTHoaDon> danhsachhoadon = query.getResultList();
				
				for(CTHoaDon cthd : danhsachhoadon) {
					CTHoaDon cthoadon = new CTHoaDon();
					cthoadon.setMaHD(cthd.getMaHD());
					cthoadon.setMaSP(cthd.getMaSP());
					cthoadon.setSoLuong(cthd.getSoLuong());
					cthoadon.setDonGia(cthd.getDonGia());
					cthoadon.setThanhTien(cthd.getThanhTien());
					dscthd.add(cthoadon);
				}
				session.getTransaction().commit();
				return dscthd;
	        } catch(Exception ex) {

	        }
	        return dscthd;
	    }
	// works
	    public boolean addCTHoaDon(CTHoaDon cthd) {
	    	SessionFactory factory = HibernateUtils.getSessionFactory();
			Session session = factory.getCurrentSession();
	        boolean result = false;
	        try {
	        	session.getTransaction().begin();
	            String sql = "INSERT INTO cthoadon VALUES(:maHD,:maSP,:SoLuong,:DonGia,:ThanhTien)";
	            Query<CTHoaDon> query = session.createNativeQuery(sql);
	            query.setParameter("maHD", cthd.getMaHD());
	            query.setParameter("maSP", cthd.getMaSP());
	            query.setParameter("SoLuong", cthd.getSoLuong());
	            query.setParameter("DonGia", cthd.getDonGia());
	            query.setParameter("ThanhTien", cthd.getThanhTien());
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

	    //works
	    public boolean deleteCTHoaDon(int maHD, int maSP) {
	    	SessionFactory factory = HibernateUtils.getSessionFactory();
			Session session = factory.getCurrentSession();
	        boolean result = false;
	        try {
	        	session.getTransaction().begin();
	            String sql = "DELETE FROM " +CTHoaDon.class.getName() + " e WHERE e.maHD=:maHD AND e.maSP=:maSP";
	            Query<CTHoaDon> query = session.createQuery(sql);
	            query.setParameter("maHD", maHD);
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
	 // works
	    public boolean deleteCTHoaDon(int maHD) {
	    	SessionFactory factory = HibernateUtils.getSessionFactory();
			Session session = factory.getCurrentSession();
	        boolean result = false;
	        try {
	        	session.getTransaction().begin();
	            String sql = "DELETE FROM " +CTHoaDon.class.getName() + " e WHERE e.maHD=:maHD";
	            Query<CTHoaDon> query = session.createQuery(sql);
	            query.setParameter("maHD", maHD);
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
	//works
	    public boolean updateCTHoaDon(int maHD, int maSP, CTHoaDon cthd) {
	    	SessionFactory factory = HibernateUtils.getSessionFactory();
			Session session = factory.getCurrentSession();
	        boolean result = false;
	        try {
	        	session.getTransaction().begin();
	            String sql = "UPDATE " +CTHoaDon.class.getName() + " SET MaHD=:maHD, MaSP=:maSP, SoLuong=:SoLuong, DonGia=:DonGia, ThanhTien=:ThanhTien "
	                    + "WHERE MaHD=:maHD AND MaSP=:maSP";
	            Query<CTHoaDon> query = session.createQuery(sql);
	            query.setParameter("maHD", maHD);
	            query.setParameter("maSP", maSP);
	            query.setParameter("SoLuong", cthd.getSoLuong());
	            query.setParameter("DonGia", cthd.getDonGia());
	            query.setParameter("ThanhTien", cthd.getThanhTien());
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
