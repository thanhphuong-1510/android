package com.example.ktra22.model;

import java.io.Serializable;

public class NhanVien implements Serializable {
    private Integer ma;
    private String ten;
    private String sdt;

    private Integer dob;
    private Boolean gt;
    private String kynang;

    public NhanVien() {
    }

    public NhanVien(Integer ma, String ten, String sdt, Integer dob, Boolean gt, String kynang) {
        this.ma = ma;
        this.ten = ten;
        this.sdt = sdt;
        this.dob = dob;
        this.gt = gt;
        this.kynang = kynang;
    }

    public NhanVien(String ten, String sdt, Integer dob, Boolean gt, String kynang) {
        this.ten = ten;
        this.sdt = sdt;
        this.dob = dob;
        this.gt = gt;
        this.kynang = kynang;
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

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Integer getDob() {
        return dob;
    }

    public void setDob(Integer dob) {
        this.dob = dob;
    }

    public Boolean getGt() {
        return gt;
    }

    public void setGt(Boolean gt) {
        this.gt = gt;
    }

    public String getKynang() {
        return kynang;
    }

    public void setKynang(String kynang) {
        this.kynang = kynang;
    }
}
