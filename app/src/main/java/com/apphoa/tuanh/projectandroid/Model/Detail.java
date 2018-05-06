package com.apphoa.tuanh.projectandroid.Model;

import java.io.Serializable;

/**
 * Created by TuAnh on 3/19/2018.
 */

public class Detail implements Serializable {
    private  String idDetail;
    private String id;
    private String Ten;
    private String Loai;
    private String Gia;
    private String HinhAnh;
    private int Soluong;

    public Detail( String inDetail,String idOrder, String ten, String loai, String gia, String hinhAnh,int soluong) {
        this.idDetail = inDetail;
        this.id = idOrder;
        Ten = ten;
        Loai = loai;
        Gia = gia;
        HinhAnh = hinhAnh;
        Soluong = soluong;
    }
    public Detail(){}

    public String getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(String idDetail) {
        this.idDetail = idDetail;
    }

    public String getIdOrder() {
        return id;
    }

    public void setIdOrder(String idOrder) {
        this.id = idOrder;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getLoai() {
        return Loai;
    }

    public void setLoai(String loai) {
        Loai = loai;
    }

    public String getGia() {
        return Gia;
    }

    public void setGia(String gia) {
        Gia = gia;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }


    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int soluong) {
        Soluong = soluong;
    }
}
