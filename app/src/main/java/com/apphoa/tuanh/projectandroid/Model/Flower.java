package com.apphoa.tuanh.projectandroid.Model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by TuAnh on 3/12/2018.
 */
@IgnoreExtraProperties
public class Flower implements Serializable
{
    private String  ID;
    private String Loai;
    private String HinhAnh;
    private String TenHoa;
    private String Gia;
    private String MoTa;
    private String Status;
    private int soluong;
    public Flower(){};
    public Flower( String loai, String tenHoa, String hinhAnh, String gia, String status, int count,String mota) {
        HinhAnh = hinhAnh;
        TenHoa = tenHoa;
        Gia = gia;
        Loai = loai;
        Status = status;
        soluong = count;
        MoTa = mota;
        ID = "null";
    }

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getLoai() {
        return Loai;
    }
    public void setLoai(String loai) {
        Loai = loai;
    }
    public String getStatus() {
        return Status;
    }
    public void setStatus(String status) {
        Status = status;
    }
    public int getSoluong() {
        return soluong;
    }
    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    public String getHinhAnh() {
        return HinhAnh;
    }
    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }
    public String getTenHoa() {
        return TenHoa;
    }
    public void setTenHoa(String tenHoa) {
        TenHoa = tenHoa;
    }
    public String getGia() {
        return Gia;
    }
    public void setGia(String gia) {
        Gia = gia;
    }
    public String getMoTa() {
        return MoTa;
    }
    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    @Override
    public String toString() {
        return "Flower{"+
                "ID= "+ ID+'\'' +
                ",Loai='" + Loai + '\'' +
                "HinhAnh='" + HinhAnh + '\'' +
                "Status='" + Status + '\'' +
                ", TenHoa='" + TenHoa + '\'' +
                ", Gia=" + Gia +
                ",soluong='" + soluong + '\'' +
                '}';
    }
}
