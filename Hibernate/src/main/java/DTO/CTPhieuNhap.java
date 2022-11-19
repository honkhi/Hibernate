package DTO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ctphieunhap")
public class CTPhieuNhap implements Serializable {
    private int maPN;
    private int maSP;
    private int soLuong;
    private int donGia;
    private int thanhTien;

    public CTPhieuNhap() {
    }

    public CTPhieuNhap(int maPN, int maSP, int soLuong, int donGia, int thanhTien) {
        this.maPN = maPN;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    @Id
    @Column(name="MaPN")
    public int getMaPN() {
        return maPN;
    }

    public void setMaPN(int maPN) {
        this.maPN = maPN;
    }
    @Id
    @Column(name="MaSP")
    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }
    @Column(name="SoLuong")
    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    @Column(name="DonGia")
    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }
    @Column(name="ThanhTien")
    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }
}
