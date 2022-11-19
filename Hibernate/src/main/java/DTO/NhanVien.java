package DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nhanvien")
public class NhanVien {

    private int maNV;
    private String ho;
    private String ten;
    private String gioiTinh;
    private String chucVu;

    public NhanVien() {
    }

    public NhanVien(int maNV, String ho, String ten, String gioiTinh, String chucVu) {
        this.maNV = maNV;
        this.ho = ho;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.chucVu = chucVu;
    }
    public NhanVien(String ho, String ten, String gioiTinh, String chucVu) {
        this.ho = ho;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.chucVu = chucVu;
    }
    
    @Id
    @GeneratedValue
	@Column(name = "MaNV")
    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }
    @Column(name="Ho")
    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }
    @Column(name="Ten")
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
    @Column(name="GioiTinh")
    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    @Column(name="ChucVu")
    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

}
