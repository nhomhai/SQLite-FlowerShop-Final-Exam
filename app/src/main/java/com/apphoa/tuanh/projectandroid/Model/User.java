package com.apphoa.tuanh.projectandroid.Model;

/**
 * Created by TuAnh on 3/19/2018.
 */

public class User {
    private String id;
    private String Ten;
    private String Password;
    private String Address;
    private String Email;
    private long Point;
    private int Permission;
    private String Phone;
    public User(){}
    public User(String id, String ten, String password, String address, String email, long point, int permission, String phone) {
        this.id = id;
        Ten = ten;
        Password = password;
        Address = address;
        Email = email;
        Point = point;
        Permission = permission;
        Phone = phone;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public long getPoint() {
        return Point;
    }

    public void setPoint(long point) {
        Point = point;
    }

    public int getPermission() {
        return Permission;
    }

    public void setPermission(int permission) {
        Permission = permission;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
