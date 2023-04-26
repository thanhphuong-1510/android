package com.example.ktra21.model;

import java.io.Serializable;

public class CongViec implements Serializable {
    private Integer ma;
    private String ten;
    private String noidung;
    private String ngayHT;
    private String tinhtrang;
    private Boolean congtac;

    public CongViec(Integer ma, String ten, String noidung, String ngayHT, String tinhtrang, Boolean congtac) {

        this.ma = ma;
        this.ten = ten;
        this.noidung = noidung;
        this.ngayHT = ngayHT;
        this.tinhtrang = tinhtrang;
        this.congtac = congtac;
    }

    public CongViec(String ten, String noidung, String ngayHT, String tinhtrang, Boolean congtac) {
        this.ten = ten;
        this.noidung = noidung;
        this.ngayHT = ngayHT;
        this.tinhtrang = tinhtrang;
        this.congtac = congtac;
    }

    public Integer getMa() {
        return ma;
    }

    public void setMa(Integer ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getNgayHT() {
        return ngayHT;
    }

    public void setNgayHT(String ngayHT) {
        this.ngayHT = ngayHT;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public Boolean getCongtac() {
        return congtac;
    }

    public void setCongtac(Boolean congtac) {
        this.congtac = congtac;
    }

    public CongViec() {
    }
}
