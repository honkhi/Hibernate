package DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "taikhoan")
public class TaiKhoan {
	private int maNhanVien;
	private String tenDangNhap;
	private String matKhau;
	private String quyen;
	private Integer TrangThai;

	public TaiKhoan() {
	}

	public TaiKhoan(int maNhanVien, String tenDangNhap, String matKhau, String quyen) {
		this.maNhanVien = maNhanVien;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.quyen = quyen;
		this.TrangThai = 1;
	}

	@Id
	@Column(name = "MaNV")
	public int getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(int maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	@Column(name = "TenDangNhap")
	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	@Column(name = "MatKhau")
	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	@Column(name = "Quyen")
	public String getQuyen() {
		return quyen;
	}

	public void setQuyen(String quyen) {
		this.quyen = quyen;
	}

	@Column(name = "TrangThai")
	public Integer getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(Integer trangThai) {
		TrangThai = trangThai;
	}

}
