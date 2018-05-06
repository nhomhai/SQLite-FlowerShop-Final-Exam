package com.apphoa.tuanh.projectandroid.Model;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TuAnh on 3/12/2018.
 */

public class Funtion {

    public int getMipmapResIdByName(String resName, Context context)  {
        String pkgName = context.getPackageName();
        // Trả về 0 nếu không tìm thấy.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("itemHoa_Adapter", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }
    public List<Flower> getListDired() {

        List<Flower> list = new ArrayList<Flower>(); // xem tạm đay di
        Flower dried1 = new Flower("dried","hoa khô 01","dried1","300.000","con hang",20,"hoa khô");
        Flower dried2 = new Flower("order","hoa khô 02","dried2","300.000","con hang",30,"khoa kho 2");
        Flower dried3 = new Flower("dried","hoa khô 03","dried3","300.000","con hang",10,"hoa khô 3");
        Flower dried4 = new Flower("dried","hoa khô 04","dried4","400.000","con hang",14,"hoa khô 4");
        Flower dried5 = new Flower("order","hoa khô 05","dried5","350.000","con hang",11,"hoa kho 5");
        Flower dried6 = new Flower("dried","hoa khô 06","dried6","400.000","con hang",12,"hoa ko 6");
        Flower dried7 = new Flower("dried","hoa khô 07","dried7","400.000","con hang",13,"hoa kho 7");
        Flower dried8 = new Flower("order","hoa khô 08","dried8","500.000","con hang",14,"hoa kho 8");
        Flower dried9 = new Flower("dried","hoa khô 09","dried9","500.000","con hang",15,"hoa kho 9");
        Flower driedcombo = new Flower("order","combo hoa khô","dcombo","1.200.000","con hang",16,"com bo hoa khô");

        list.add(dried1);
        list.add(dried2);
        list.add(dried3);
        list.add(dried4);
        list.add(dried5);
        list.add(dried6);
        list.add(dried7);
        list.add(dried8);
        list.add(dried9);
        list.add(driedcombo);

        return list;
    }
    public void showAlertDialog(String s,Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("FlowerShop");
        builder.setMessage(s);
        builder.setCancelable(false);
        builder.setNegativeButton("OK!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public boolean isOnline(Context mContext)
    {
        try
        {
            ConnectivityManager cm = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo().isConnectedOrConnecting();
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
