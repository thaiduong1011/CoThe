package com.example.admin.thoikhoabieu;

/**
 * Created by admin on 4/25/2018.
 */

public class InforTimeTable {
    private String MaMH;
    private String TenMH;
    private String TenPhong;
    private String NgayHoc;
    private int TietBD;
    private String GiangVien;
    private String Lop;

    public String getThu() {
        return Thu;
    }

    public void setThu(String thu) {
        Thu = thu;
    }

    private String Thu;

    public InforTimeTable(){

    }

    public InforTimeTable(String maMH, String tenMH, String tenPhong, String ngayHoc, int tietBD, String giangVien, String lop) {
        MaMH = maMH;
        TenMH = tenMH;
        TenPhong = tenPhong;
        NgayHoc = ngayHoc;
        TietBD = tietBD;
        GiangVien = giangVien;
        Lop = lop;
    }

    public InforTimeTable(String tenMH, int tietBD, String ngay, String phong){
        this.TenMH = tenMH;
        this.TietBD = tietBD;
        this.NgayHoc = ngay;
        this.TenPhong = phong;
    }

    public InforTimeTable(String tenMH, String tenPhong) {
        TenMH = tenMH;
        TenPhong = tenPhong;
    }

    public String getMaMH() {
        return MaMH;
    }

    public void setMaMH(String maMH) {
        MaMH = maMH;
    }

    public String getTenMH() {
        return TenMH;
    }

    public void setTenMH(String tenMH) {
        TenMH = tenMH;
    }

    public String getTenPhong() {
        return TenPhong;
    }

    public void setTenPhong(String tenPhong) {
        TenPhong = tenPhong;
    }

    public int getTietBD() {
        return TietBD;
    }

    public void setTietBD(int tietBD) {
        TietBD = tietBD;
    }

    public String getNgayHoc() {
        return NgayHoc;
    }

    public void setNgayHoc(String ngayHoc) {
        NgayHoc = ngayHoc;
    }

    public String getGiangVien() {
        return GiangVien;
    }

    public void setGiangVien(String giangVien) {
        GiangVien = giangVien;
    }

    public String getLop() {
        return Lop;
    }

    public void setLop(String lop) {
        Lop = lop;
    }

    @Override
    public String toString() {
        return "InforTimeTable{" +
                "MaMH='" + MaMH + '\'' +
                ", TenMH='" + TenMH + '\'' +
                ", TenPhong='" + TenPhong + '\'' +
                ", TietBD=" + TietBD +
                ", NgayHoc=" + NgayHoc +
                ", GiangVien='" + GiangVien + '\'' +
                ", Lop='" + Lop + '\'' +
                '}';
    }
}
