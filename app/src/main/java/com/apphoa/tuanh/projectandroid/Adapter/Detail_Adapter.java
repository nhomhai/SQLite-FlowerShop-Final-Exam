package com.apphoa.tuanh.projectandroid.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apphoa.tuanh.projectandroid.Model.Detail;
import com.apphoa.tuanh.projectandroid.Model.Funtion;
import com.apphoa.tuanh.projectandroid.Model.Order;
import com.apphoa.tuanh.projectandroid.R;

import java.util.List;

/**
 * Created by TuAnh on 4/30/2018.
 */

public class Detail_Adapter extends ArrayAdapter {
    private List<Detail> listData;
    private int resource;
    private Context context;
    public Detail_Adapter(@NonNull Context context, int resource, List<Detail> orders) {
        super(context, resource,orders);
        this.listData = orders;
        this.resource = resource;
        this.context = context;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layinf = LayoutInflater.from(context);
        convertView = layinf.inflate(R.layout.detail_item,parent,false);
        ImageView img = (ImageView) convertView.findViewById(R.id.imageView_detail);
        TextView txtten = (TextView)convertView.findViewById(R.id.textView_detail_tenhoa);
        TextView txtloai = (TextView)convertView.findViewById(R.id.textView_detail_loai);
        TextView txtsoluong = (TextView)convertView.findViewById(R.id.textView_detail_soluong);
        TextView txtgia = (TextView)convertView.findViewById(R.id.textView_detail_gia);
        Detail detail = listData.get(position);
        Funtion f = new Funtion();
        img.setImageResource(f.getMipmapResIdByName(detail.getHinhAnh(),context));
        txtgia.setText("Giá: "+detail.getGia());
        txtloai.setText("Loại: "+detail.getLoai());
        txtsoluong.setText("Số lượng: "+String.valueOf(detail.getSoluong()));
        txtten.setText("Tên: "+detail.getTen());
        return convertView;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return super.getPosition(item);
    }
}
