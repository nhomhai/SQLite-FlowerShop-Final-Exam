package com.apphoa.tuanh.projectandroid.Database;

/**
 * Created by TuAnh on 3/18/2018.
 */

public class DetailOrder  {
    private String TABLE_NAME = "DetailOrder";
    private String COL_ID_ORDER = "idOrder";
    private String COL_TEN ="Ten";
    private String COL_LOAI = "Loai";
    private String COL_GIA = "Gia";
    private String COL_HINHANH= "HinhAnh";
    private String COL_SOLUONG = "SoLuong";
    private  String COL_ID_DETAIL = "IdDetail";
    private String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("+
            COL_ID_ORDER + " TEXT , "+
            COL_TEN+" TEXT, "+
            COL_LOAI +" TEXT, "+
            COL_HINHANH +" TEXT, "+
            COL_SOLUONG + " INTEGER , "+
            COL_GIA+" TEXT , "+
            COL_ID_DETAIL+ " TEXT);";

    public DetailOrder() {
    }

    public String getTABLE_NAME() {
        return TABLE_NAME;
    }

    public void setTABLE_NAME(String TABLE_NAME) {
        this.TABLE_NAME = TABLE_NAME;
    }

    public String getCOL_ID_ORDER() {
        return COL_ID_ORDER;
    }

    public void setCOL_ID_ORDER(String COL_ID_ORDER) {
        this.COL_ID_ORDER = COL_ID_ORDER;
    }

    public String getCOL_TEN() {
        return COL_TEN;
    }

    public void setCOL_TEN(String COL_TEN) {
        this.COL_TEN = COL_TEN;
    }

    public String getCOL_LOAI() {
        return COL_LOAI;
    }

    public void setCOL_LOAI(String COL_LOAI) {
        this.COL_LOAI = COL_LOAI;
    }

    public String getCOL_GIA() {
        return COL_GIA;
    }

    public void setCOL_GIA(String COL_GIA) {
        this.COL_GIA = COL_GIA;
    }

    public String getCOL_HINHANH() {
        return COL_HINHANH;
    }

    public void setCOL_HINHANH(String COL_HINHANH) {
        this.COL_HINHANH = COL_HINHANH;
    }

    public String getCREATE_TABLE() {
        return CREATE_TABLE;
    }

    public void setCREATE_TABLE(String CREATE_TABLE) {
        this.CREATE_TABLE = CREATE_TABLE;
    }

    public String getCOL_SOLUONG() {
        return COL_SOLUONG;
    }

    public void setCOL_SOLUONG(String COL_SOLUONG) {
        this.COL_SOLUONG = COL_SOLUONG;
    }

    public String getCOL_ID_DETAIL() {
        return COL_ID_DETAIL;
    }
}
