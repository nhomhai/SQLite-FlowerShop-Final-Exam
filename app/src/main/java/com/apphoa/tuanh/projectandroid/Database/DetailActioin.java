package com.apphoa.tuanh.projectandroid.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apphoa.tuanh.projectandroid.Model.Detail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TuAnh on 4/30/2018.
 */

public class DetailActioin {
    private MySqlHelper mysql;
    private DetailOrder detailOrder;
    public DetailActioin(MySqlHelper m){
        this.mysql = m;
        this.detailOrder = m.getDetail();
    }
    public void AddDetail(Detail detail){
        SQLiteDatabase db = mysql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(detailOrder.getCOL_ID_DETAIL(),detail.getIdDetail());
        values.put(detailOrder.getCOL_ID_ORDER(),detail.getIdOrder());
        values.put(detailOrder.getCOL_HINHANH(),detail.getHinhAnh());
        values.put(detailOrder.getCOL_GIA(),detail.getGia());
        values.put(detailOrder.getCOL_LOAI(),detail.getLoai());
        values.put(detailOrder.getCOL_TEN(),detail.getTen());
        values.put(detailOrder.getCOL_SOLUONG(),detail.getSoluong());
        db.insert(detailOrder.getTABLE_NAME(),null,values);
        db.close();
    }
    public List<Detail> GetDetailByIdOrder(String idOder){
        List<Detail> list = new ArrayList<>();
        String query = "select * from "+ detailOrder.getTABLE_NAME()+" where "+ detailOrder.getCOL_ID_ORDER()+ " = '"+ idOder+ "';";
        SQLiteDatabase db = mysql.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            /*COL_ID_ORDER + " TEXT primary, "+
            COL_TEN+" TEXT, "+
            COL_LOAI +" TEXT, "+
            COL_HINHANH +" TEXT, "+
            COL_SOLUONG + " INTEGER, "+
            COL_GIA+" TEXT)";*/
            do{
                Detail detail=new Detail();
                detail.setIdOrder(cursor.getString(0));
                detail.setTen(cursor.getString(1));
                detail.setLoai(cursor.getString(2));
                detail.setHinhAnh(cursor.getString(3));
                detail.setSoluong(cursor.getInt(4));
                detail.setGia(cursor.getString(5));
                detail.setIdDetail(cursor.getString(6));
                list.add(detail);
            }
            while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return list;
    }
    // xóa các detail trong đơn hàng
    public void DeleteDetail(Detail detail){
        SQLiteDatabase db =mysql.getWritableDatabase();
        db.delete(detailOrder.getTABLE_NAME(),detailOrder.getCOL_ID_ORDER()+ "=?",new String[]{String.valueOf(detail.getIdOrder())});
        db.close();
    }
}
