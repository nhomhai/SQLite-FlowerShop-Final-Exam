package com.apphoa.tuanh.projectandroid.Model;

import java.io.Serializable;

/**
 * Created by TuAnh on 3/19/2018.
 */

public class Order implements Serializable {
    private String id;
    private String User;//id của user
    private String Time;
    private String Address;
    private String Phone;
    private String Name;//tên ng đặt hàng
    private String Status;
    private String TongTien;

    public Order(){}
    public Order(String id, String user, String time, String address, String phone, String name, String status,String tongTien) {
        this.id = id;
        User = user;
        Time = time;
        Address = address;
        Phone = phone;
        Name = name;
        Status = status;
        TongTien=tongTien;
    }

    public String getTongTien() {
        return TongTien;
    }

    public void setTongTien(String tongTien) {
        TongTien = tongTien;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
