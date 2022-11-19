package DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nhacungcap")
public class NhaCungCap {
    private int maNCC;
    private String tenNCC;
    private String diaChi;
    private String dienThoai;

    public NhaCungCap() {
    }

    public NhaCungCap(int maNCC, String tenNCC, String diaChi, String dienThoai) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
    }

    @Id
    @GeneratedValue
	@Column(name = "MaNCC")
    public int getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }
    @Column(name = "TenNCC")
    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }
    @Column(name = "DiaChi")
    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    @Column(name = "DienThoai")
    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }
}
