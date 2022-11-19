package DAO;

import DTO.HibernateUtils;
import DTO.TaiKhoan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class DangNhapDAO {

	public TaiKhoan dangNhap(TaiKhoan tk) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.getTransaction().begin();
			// String sql = "SELECT * FROM taikhoan WHERE TenDangNhap=? AND MatKhau=? AND
			// TrangThai=1";
			String sql = "select e from " + TaiKhoan.class.getName() + " e "
					+ " where e.matKhau = :matkhau and e.tenDangNhap = :tendangnhap and e.trangThai=1";
			Query<TaiKhoan> query = session.createQuery(sql);
			query.setParameter("matkhau", tk.getMatKhau());
			query.setParameter("tendangnhap", tk.getTenDangNhap());
			TaiKhoan tkLogin = null;
			TaiKhoan tkLogin1 = query.getSingleResult();
			tkLogin = tk;
			tkLogin.setMaNhanVien(tkLogin1.getMaNhanVien());
			tkLogin.setQuyen(tkLogin1.getQuyen());
			session.getTransaction().commit();
			return tkLogin;
		} catch (Exception e) {
			e.printStackTrace();
			return tk;
		}

	}
}