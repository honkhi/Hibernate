package DTO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phieunhap")
public class PhieuNhap {

    private int maPN;
    private int maNCC;
    private int maNV;
    private Date ngayLap;
    private int tongTien;

    public PhieuNhap() {
    }

    public PhieuNhap(int maPN, int maNCC, int maNV, Date ngayLap, int tongTien) {
        this.maPN = maPN;
        this.maNCC = maNCC;
        this.maNV = maNV;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
    }

    @Id
    @GeneratedValue
    @Column(name="MaPN")
    public int getMaPN() {
        return maPN;
    }

    public void setMaPN(int maPN) {
        this.maPN = maPN;
    }
    @Column(name="MaNCC")
    public int getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }
    @Column(name="MaNV")
    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }
    @Column(name="NgayLap")
    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }
    @Column(name="TongTien")
    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

}
