package com.apphoa.tuanh.projectandroid.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apphoa.tuanh.projectandroid.Model.Detail;
import com.apphoa.tuanh.projectandroid.Model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TuAnh on 4/30/2018.
 */

public class OrderAction {
    private MySqlHelper mysql;
    OrderTable orderTable;

    public OrderAction(MySqlHelper mysql) {
        this.mysql = mysql;
        this.orderTable = mysql.getOrder();
    }

    public MySqlHelper getMysql() {
        return mysql;
    }

    public void setMysql(MySqlHelper mysql) {
        this.mysql = mysql;
    }

    public OrderTable getOrderTable() {
        return orderTable;
    }

    public void setOrderTable(OrderTable orderTable) {
        this.orderTable = orderTable;
    }
    public void AddOrder(Order order){
        SQLiteDatabase db = mysql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(orderTable.getCOL_ID(),order.getId());
        values.put(orderTable.getCOL_ADDR(),order.getAddress());
        values.put(orderTable.getCOL_NAME(),order.getName());
        values.put(orderTable.getCOL_PHONE(),order.getPhone());
        values.put(orderTable.getCOL_STATUS(),order.getStatus());
        values.put(orderTable.getCOL_TIME(),order.getTime());
        values.put(orderTable.getCOL_USER(),order.getUser());
        values.put(orderTable.getCOL_TONGTIEN(),order.getTongTien());
        db.insert(orderTable.getTABLE_NAME(),null,values);
        db.close();
    }
    public List<Order> GetOrderByUserId(String idUser){
        List<Order> list = new ArrayList<>();
        SQLiteDatabase db = mysql.getReadableDatabase();
        String query = "select * from "+ orderTable.getTABLE_NAME()+" where "+orderTable.getCOL_USER()+ " = '"+ idUser+ "';";
        Cursor cursor = db.rawQuery(query,null,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                        /*COL_ID + " TEXT primary key, "+
                        COL_USER + " INTEGER, "+
                        COL_STATUS + " TEXT, "+
                        COL_ADDR + " TEXT, "+
                        COL_PHONE+ " TEXT, "+
                        COL_NAME+ "TEXT, "+
                        COL_TIME +" TEXT )";*/
                Order order = new Order();
                order.setId(cursor.getString(0));
                order.setUser(cursor.getString(1));
                order.setStatus(cursor.getString(2));
                order.setAddress(cursor.getString(3));
                order.setPhone(cursor.getString(4));
                order.setName(cursor.getString(5));
                order.setTime(cursor.getString(6));
                order.setTongTien(cursor.getString(7));
                list.add(order);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
    //public Order GetOrderById()
    public void DeleteOrder(Order order){
        SQLiteDatabase db = mysql.getWritableDatabase();
        db.delete(orderTable.getTABLE_NAME(),orderTable.getCOL_ID()+ "=?",new String[]{String.valueOf(order.getId())});
        db.close();
    }
}

