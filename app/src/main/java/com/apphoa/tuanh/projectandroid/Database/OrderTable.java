package com.apphoa.tuanh.projectandroid.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by TuAnh on 3/18/2018.
 */

public class OrderTable  {
    private String TABLE_NAME = "Order_type";
    private String COL_ID = "id";
    private String COL_USER = "User";
    private String COL_TIME = "_Time";
    private String COL_ADDR = "Address";
    private String COL_PHONE = "Phone";
    private String COL_NAME = "Name";
    private String COL_STATUS = "Status";
    private String COL_TONGTIEN = "TongTien";
    private String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("+
            COL_ID + " TEXT primary key, "+
            COL_USER + " TEXT, "+
            COL_STATUS + " TEXT, "+
            COL_ADDR + " TEXT, "+
            COL_PHONE+ " TEXT, "+
            COL_NAME+ " TEXT, "+
            COL_TIME +" TEXT, "+
            COL_TONGTIEN + " TEXT)";
    public OrderTable() {
    }

    public String getCOL_TONGTIEN() {
        return COL_TONGTIEN;
    }

    public void setCOL_TONGTIEN(String COL_TONGTIEN) {
        this.COL_TONGTIEN = COL_TONGTIEN;
    }

    public String getTABLE_NAME() {
        return TABLE_NAME;
    }

    public void setTABLE_NAME(String TABLE_NAME) {
        this.TABLE_NAME = TABLE_NAME;
    }

    public String getCOL_ID() {
        return COL_ID;
    }

    public void setCOL_ID(String COL_ID) {
        this.COL_ID = COL_ID;
    }

    public String getCOL_USER() {
        return COL_USER;
    }

    public void setCOL_USER(String COL_USER) {
        this.COL_USER = COL_USER;
    }

    public String getCOL_TIME() {
        return COL_TIME;
    }

    public void setCOL_TIME(String COL_TIME) {
        this.COL_TIME = COL_TIME;
    }

    public String getCOL_ADDR() {
        return COL_ADDR;
    }

    public void setCOL_ADDR(String COL_ADDR) {
        this.COL_ADDR = COL_ADDR;
    }

    public String getCOL_PHONE() {
        return COL_PHONE;
    }

    public void setCOL_PHONE(String COL_PHONE) {
        this.COL_PHONE = COL_PHONE;
    }

    public String getCOL_NAME() {
        return COL_NAME;
    }

    public void setCOL_NAME(String COL_NAME) {
        this.COL_NAME = COL_NAME;
    }

    public String getCOL_STATUS() {
        return COL_STATUS;
    }

    public void setCOL_STATUS(String COL_STATUS) {
        this.COL_STATUS = COL_STATUS;
    }

    public String getCREATE_TABLE() {
        return CREATE_TABLE;
    }

    public void setCREATE_TABLE(String CREATE_TABLE) {
        this.CREATE_TABLE = CREATE_TABLE;
    }
}
