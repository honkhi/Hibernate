package DTO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "giamgia")
public class GiamGia {

    private int maGiam;
    private String tenGiamGia;
    private int phanTramGiam;
    private int dieuKien;
    private Date ngayBD;
    private Date ngayKT;

    public GiamGia() {
    }

    public GiamGia(int maGiam, String tenGiamGia, int phanTramGiam, int dieuKien, Date ngayBD, Date ngayKT) {
        this.maGiam = maGiam;
        this.tenGiamGia = tenGiamGia;
        this.phanTramGiam = phanTramGiam;
        this.dieuKien = dieuKien;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
    }

    @Id
    @GeneratedValue
	@Column(name = "MaGiam")
    public int getMaGiam() {
        return maGiam;
    }

    public void setMaGiam(int maGiam) {
        this.maGiam = maGiam;
    }
    @Column(name = "TenGiamGia")
    public String getTenGiamGia() {
        return tenGiamGia;
    }

    public void setTenGiamGia(String tenGiamGia) {
        this.tenGiamGia = tenGiamGia;
    }
    @Column(name = "PhanTramGiam")
    public int getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(int phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }
    @Column(name = "DieuKien")
    public int getDieuKien() {
        return dieuKien;
    }

    public void setDieuKien(int dieuKien) {
        this.dieuKien = dieuKien;
    }
    @Column(name = "NgayBD")
    public Date getNgayBD() {
        return ngayBD;
    }
    
    public void setNgayBD(Date ngayBD) {
        this.ngayBD = ngayBD;
    }
    @Column(name = "NgayKT")
    public Date getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(Date ngayKT) {
        this.ngayKT = ngayKT;
    }
}
