package com.apphoa.tuanh.projectandroid.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.apphoa.tuanh.projectandroid.Model.Flower;

import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TuAnh on 3/25/2018.
 */

public class FlowerAction  {
    private MySqlHelper mysql;
    private FlowerTable flowerTable;

    public FlowerAction(MySqlHelper mysql) {
        //mysql = new MySqlHelper();
        this.mysql = mysql;
        this.flowerTable = mysql.getFlower();
    }

    public MySqlHelper getMysql() {
        return mysql;
    }

    public void setMysql(MySqlHelper mysql) {
        this.mysql = mysql;
    }

    public FlowerTable getFlowerTable() {
        return flowerTable;
    }

    public void setFlowerTable(FlowerTable flowerTable) {
        this.flowerTable = flowerTable;
    }

    //add a flower
    public void AddFlower(Flower fl) {
        SQLiteDatabase db = mysql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(flowerTable.getCOL_ID(),fl.getID());
        values.put(flowerTable.getCOL_COUNT(), fl.getSoluong());
        values.put(flowerTable.getCOL_GIA(), fl.getGia());
        values.put(flowerTable.getCOL_HINHANH(), fl.getHinhAnh());
        values.put(flowerTable.getCOL_LOAI(), fl.getLoai());
        values.put(flowerTable.getCOL_STATUS(), fl.getStatus());
        values.put(flowerTable.getCOL_TEN(), fl.getTenHoa());
        values.put(flowerTable.getCOL_MOTA(),fl.getMoTa());
        db.insert(flowerTable.getTABLE_NAME(), null, values);
        db.close();
    }

    // get Flower from id
    public Flower getById(int id){
        SQLiteDatabase db = mysql.getReadableDatabase();
        Flower fl;
        String query = "select * from "+ flowerTable.getTABLE_NAME()+ "where "+ flowerTable.getCOL_ID()+  " = '"+ id+ "';";
        Cursor cursor = db.rawQuery(query,new String[]{String.valueOf(id)});
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            fl = new Flower(cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getInt(6),
                    cursor.getString(7));
            fl.setID(cursor.getString(0));
            cursor.close();
            db.close();
            return fl;
        }
        return null;

    }

    // get Flower from image
    public Flower getByImange(String image){
        SQLiteDatabase db = mysql.getReadableDatabase();
        Flower fl = null;
        String query = "select * from " + flowerTable.getTABLE_NAME()+" where "+ flowerTable.getCOL_HINHANH() + " =?;";
        Cursor cursor = db.rawQuery(query,new String[]{image});
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            fl = new Flower(cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getInt(6),
                    cursor.getString(7));
            fl.setID(cursor.getString(0));
        }
        db.close();
        return fl;
    }

    //get all flower in db

    public List<Flower> getAllFlower(){
        List<Flower> list =  new ArrayList<>();
        String query = "select * from "+ flowerTable.getTABLE_NAME() +";";
        SQLiteDatabase db = mysql.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            do{
                /*COL_ID + " TEXT , "+
            COL_LOAI +" TEXT, "+
            COL_TEN+" TEXT, "+
            COL_HINHANH +" TEXT,  "+
            COL_GIA+" TEXT,"+
            COL_STATUS + " TEXT, "+
            COL_COUNT+ " INTEGER, "+
            COL_MOTA + " TEXT)";*/
                Flower fl = new Flower();
                fl.setID(cursor.getString(0));
                fl.setLoai(cursor.getString(1));
                fl.setTenHoa(cursor.getString(2));
                fl.setHinhAnh(cursor.getString(3));
                fl.setGia(cursor.getString(4));
                fl.setStatus(cursor.getString(5));
                fl.setSoluong(cursor.getInt(6));
                fl.setMoTa(cursor.getString(7));
                list.add(fl);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
    // get ListFlower from Loai
    public List<Flower> getTypeFlower(String loai){
        List<Flower> list =  new ArrayList<>();
        String query = "select * from "+ flowerTable.getTABLE_NAME() + " where "+ flowerTable.getCOL_LOAI()+"=?;";
        SQLiteDatabase db = mysql.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,new String[]{loai});
        if(cursor.getCount() == 0) return  list;
        if(cursor.moveToFirst()) {
            //lấy cursor (id,tên, loại, hình ảnh, count,status, giá,mô tả)
            // hàm new flower(loại, ten, hình ảnh, giá, status)

            do{
                Flower fl = new Flower();
                fl.setID(cursor.getString(0));
                fl.setLoai(cursor.getString(1));
                fl.setTenHoa(cursor.getString(2));
                fl.setHinhAnh(cursor.getString(3));
                fl.setGia(cursor.getString(4));
                fl.setStatus(cursor.getString(5));
                fl.setSoluong(cursor.getInt(6));
                fl.setMoTa(cursor.getString(7));
                list.add(fl);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
    // Update a Flower
    public int Update(Flower fl){
        SQLiteDatabase db = mysql.getWritableDatabase();
       // Flower value = getById(fl.getID());
        /*String query = "select * from "+ flowerTable.getTABLE_NAME()+ " where "+ flowerTable.getCOL_TEN() + " =? and "+ flowerTable.getCOL_HINHANH()+ "=?";
        //Cursor cursor = db.rawQuery(query,new String[]{fl.getTenHoa(),fl.getHinhAnh()});
        Cursor cursor= db.query(flowerTable.getTABLE_NAME(),new String[]{flowerTable.getCOL_TEN()}
        ,flowerTable.getCOL_TEN()+"=? and "+flowerTable.getCOL_HINHANH(),new String[]{fl.getTenHoa(),fl.getHinhAnh()}
        ,null,null,null );
        if(cursor == null){*/
            ContentValues newValue = new ContentValues();
            newValue.put(flowerTable.getCOL_TEN(),fl.getTenHoa());
            newValue.put(flowerTable.getCOL_COUNT(), fl.getSoluong());
            newValue.put(flowerTable.getCOL_GIA(), fl.getGia());
            newValue.put(flowerTable.getCOL_HINHANH(), fl.getHinhAnh());
            newValue.put(flowerTable.getCOL_LOAI(), fl.getLoai());
            newValue.put(flowerTable.getCOL_STATUS(), fl.getStatus());
            newValue.put(flowerTable.getCOL_MOTA(),fl.getMoTa());
            db.update(flowerTable.getTABLE_NAME(),newValue,flowerTable.getCOL_ID()+"="+fl.getID(),null);
            return  1;
     /*   }
        else return 0;*/

    }

    // Delete a Flower

    public void DeleteFlower(Flower fl){
        SQLiteDatabase db = mysql.getWritableDatabase();
        db.delete(flowerTable.getTABLE_NAME(),flowerTable.getCOL_ID()+ "=?",new String[]{String.valueOf(fl.getID())});
        db.close();
    }

    public ArrayList<Flower> Search(String s_Search){
         ArrayList<Flower> kq = new ArrayList<>();
        SQLiteDatabase db = mysql.getReadableDatabase();
        String query = "select * from "+ flowerTable.getTABLE_NAME()+" where ";
        String where = flowerTable.getCOL_TEN()+ " =?  or "+ flowerTable.getCOL_LOAI()+" =?  or "+flowerTable.getCOL_MOTA()+" =? ";
        //Cursor cursor = db.query(flowerTable.getTABLE_NAME(),new String[]{flowerTable.getCOL_ID()},where,)
        return kq;
    }
}
