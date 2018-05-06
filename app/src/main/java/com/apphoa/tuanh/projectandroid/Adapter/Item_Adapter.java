package com.apphoa.tuanh.projectandroid.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.apphoa.tuanh.projectandroid.Model.Flower;
import com.apphoa.tuanh.projectandroid.Model.Funtion;
import com.apphoa.tuanh.projectandroid.R;

import java.util.List;

/**
 * Created by TuAnh on 3/12/2018.
 */

public class Item_Adapter extends ArrayAdapter<Flower>{
    private List<Flower> listData;
    private int resource;
    private Context context;

    public int getResource() {
        return resource;
    }

    public List<Flower> getListData() {
        return listData;
    }

    public Item_Adapter(@NonNull Context context, int resource, @NonNull List<Flower> objects) {
        super(context, resource, objects);
        this.listData = objects;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layinf = LayoutInflater.from(context);
        convertView = layinf.inflate(R.layout.layout_hoa_item,parent,false);

         // khai báo các View trong layout_hoa_item
        ImageView imgHoa = (ImageView) convertView.findViewById(R.id.imgHoa);
        TextView txtTen = (TextView) convertView.findViewById(R.id.txtTenHoa);
        TextView txtGia = (TextView) convertView.findViewById(R.id.txtGia);
        Button btnChiTiet = (Button) convertView.findViewById(R.id.btnChiTiet);
        TextView txtStatus =(TextView) convertView.findViewById(R.id.txtStatus);
        Button btnOrder = (Button) convertView.findViewById(R.id.btnOrder);

        //set tag cho btn để gọi view chi tiết
        btnChiTiet.setTag((Integer) position);
        btnOrder.setTag((Integer) position);

        // lấy obj flower tại vị trí position
        Flower fl = listData.get(position);

        //gán giá trị fl lên view layout_hoa_item
        txtTen.setText(fl.getTenHoa());
        txtGia.setText(fl.getGia());
        txtStatus.setText(fl.getStatus());
        Funtion f = new Funtion();
        // lấy id của hình ảnh trong res/mipmap
        int index = f.getMipmapResIdByName(fl.getHinhAnh(),context);
        imgHoa.setImageResource(index);
        Funtion func = new Funtion();
        if(!func.isOnline(context))
            btnOrder.setEnabled(false);
        return convertView;

    }

    @Override
    public int getPosition(@Nullable Flower item) {
        return super.getPosition(item);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

}
