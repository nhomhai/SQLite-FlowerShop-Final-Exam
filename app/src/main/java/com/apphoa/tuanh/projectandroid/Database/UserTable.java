package com.apphoa.tuanh.projectandroid.Database;

/**
 * Created by TuAnh on 3/18/2018.
 */

public class UserTable  {
    private String TABLE_NAME = "User";
    private String COL_ID = "id"; // username
    private String COL_TEN ="Ten";
    private String COL_PASS = "Password";
    private String COL_ADDR = "Address";
    private String COL_EMAIL = "Email";
    private String COL_POINT = "Point";
    private String COL_PERMISSON = "Permission";
    private String COL_PHONE = "Phone";
    private String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("+
            COL_ID +" TEXT primary key , "+
            COL_TEN+" TEXT, "+
            COL_PASS +" TEXT, "+
            COL_ADDR+ " TEXT," +
            COL_EMAIL+ " TEXT, "+
            COL_POINT+" long, "+
            COL_PHONE+ " TEXT, "+
            COL_PERMISSON+ " INTEGER)";

    public UserTable() {

    }

    public  String getTableName() {
        return TABLE_NAME;
    }

    public  String getColId() {
        return COL_ID;
    }

    public  String getColTen() {
        return COL_TEN;
    }

    public  String getColPass() {
        return COL_PASS;
    }

    public  String getColAddr() {
        return COL_ADDR;
    }

    public  String getColEmail() {
        return COL_EMAIL;
    }

    public  String getColPoint() {
        return COL_POINT;
    }

    public  String getColPermisson() {
        return COL_PERMISSON;
    }

    public  String getColPhone() {
        return COL_PHONE;
    }

    public  String getCreateTable() {
        return CREATE_TABLE;
    }
}
