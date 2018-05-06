package com.apphoa.tuanh.projectandroid.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TuAnh on 3/13/2018.
 */

public class MySqlHelper extends SQLiteOpenHelper {
    public static  final String DB_NAME = "FlowerShop";
    public  final UserTable user = new UserTable();
    public  final FlowerTable flower = new FlowerTable();
    public  final OrderTable order = new OrderTable();
    public  final DetailOrder detail = new DetailOrder();
    private Context context;
    private static  int VERSION = 1;

    public  String getDbName() {
        return DB_NAME;
    }

    public  UserTable getUser() {
        return user;
    }

    public
    FlowerTable getFlower() {
        return flower;
    }

    public  OrderTable getOrder() {
        return order;
    }

    public
    DetailOrder getDetail() {
        return detail;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public  int getVERSION() {
        return VERSION;
    }

    public  void setVERSION(int VERSION) {
        this.VERSION = VERSION;
    }

    // hàm khởi tạo database

    public MySqlHelper(Context context) {
        super(context,DB_NAME,null,VERSION);
    }

    // tạo bảng
    @Override
    public void onCreate(SQLiteDatabase db) {
        // tạo bảng user
        String create_user = user.getCreateTable();
        db.execSQL(create_user);
        //tạo bảng flower
        String create_flower = flower.getCREATE_TABLE();
        db.execSQL(create_flower);
        // tạo bảng order
        String create_order = order.getCREATE_TABLE();
        db.execSQL(create_order);
        //tạo bảng chi biết dơn hàng Detail_order
        String create_detail = detail.getCREATE_TABLE();
        db.execSQL(create_detail);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ flower.getTABLE_NAME()+user.getTableName()+order.getTABLE_NAME()+detail.getTABLE_NAME());
        onCreate(db);

    }

}
