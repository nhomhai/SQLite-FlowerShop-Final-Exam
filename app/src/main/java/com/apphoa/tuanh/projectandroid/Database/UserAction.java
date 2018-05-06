package com.apphoa.tuanh.projectandroid.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apphoa.tuanh.projectandroid.Model.Flower;
import com.apphoa.tuanh.projectandroid.Model.User;

/**
 * Created by TuAnh on 3/25/2018.
 */

public  class UserAction {
    private MySqlHelper mysql;
    UserTable userTable;

    public UserAction(MySqlHelper mysql) {
        this.mysql = mysql;
        this.userTable=mysql.getUser();
    }

    public MySqlHelper getMysql() {
        return mysql;
    }

    public void setMysql(MySqlHelper mysql) {
        this.mysql = mysql;
    }

    public UserTable getUserTable() {
        return userTable;
    }

    public void setUserTable(UserTable userTable) {
        this.userTable = userTable;
    }

    // add new user
    public void AddUser(User u){
        SQLiteDatabase db = mysql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(userTable.getColId(),u.getId());
        values.put(userTable.getColTen(),u.getTen());
        values.put(userTable.getColAddr(),u.getAddress());
        values.put(userTable.getColEmail(),u.getEmail());
        values.put(userTable.getColPass(),u.getPassword());
        values.put(userTable.getColPermisson(),u.getPermission());
        values.put(userTable.getColPhone(),u.getPhone());
        values.put(userTable.getColPoint(),u.getPoint());
        db.insert(userTable.getTableName(),null,values);
        db.close();
    }

    // select user by ID
    public User GetUserById(String id){
        User u = new User();
        SQLiteDatabase db = mysql.getReadableDatabase();
        String query = "select * from "+userTable.getTableName()+" where "+ userTable.getColId()+ " = '"+ id+ "';";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.getCount() > 0)
         {
            cursor.moveToFirst();
                    /*COL_ID +" TEXT primary key , "+
                    COL_TEN+" TEXT, "+
                    COL_PASS +" TEXT, "+
                    COL_ADDR+ " TEXT," +
                    COL_EMAIL+ " TEXT, "+
                    COL_POINT+" long, "+
                    COL_PHONE+ " TEXT, "+
                    COL_PERMISSON+ " INTEGER)";*/
                u.setId(cursor.getString(0));
                u.setTen(cursor.getString(1));
                u.setPassword(cursor.getString(2));
                u.setAddress(cursor.getString(3));
                u.setEmail(cursor.getString(4));
                u.setPoint(cursor.getLong(5));
                u.setPhone(cursor.getString(6));
                u.setPermission(cursor.getInt(7));
        }
        cursor.close();
        db.close();
        return  u;
    }
    public  void UpdateUser(User user){
        SQLiteDatabase db =  mysql.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(userTable.getColId(),user.getId());
        values.put(userTable.getColTen(),user.getTen());
        values.put(userTable.getColPass(),user.getPassword());
        values.put(userTable.getColAddr(),user.getAddress());
        values.put(userTable.getColEmail(),user.getEmail());
        values.put(userTable.getColPoint(),user.getPoint());
        values.put(userTable.getColPhone(),user.getPhone());
        values.put(userTable.getColPermisson(),user.getPermission());
        db.update(userTable.getTableName(),values,userTable.getColId()+"= '"+user.getId()+"'",null);
        db.close();

    }
    public void DeleteFlower(User user){
        SQLiteDatabase db = mysql.getWritableDatabase();
        db.delete(userTable.getTableName(),userTable.getColId()+ "=?",new String[]{String.valueOf(user.getId())});
        db.close();
    }

}
