package com.apphoa.tuanh.projectandroid.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.apphoa.tuanh.projectandroid.Database.DetailActioin;
import com.apphoa.tuanh.projectandroid.Database.MySqlHelper;
import com.apphoa.tuanh.projectandroid.Model.Detail;
import com.apphoa.tuanh.projectandroid.Model.Flower;
import com.apphoa.tuanh.projectandroid.Model.Order;
import com.apphoa.tuanh.projectandroid.R;

import junit.framework.Test;

import java.util.List;

/**
 * Created by TuAnh on 4/30/2018.
 */

public class Order_Adapter extends ArrayAdapter {
    private List<Order> listData;
    private int resource;
    private Context context;
    public Order_Adapter(@NonNull Context context, int resource,List<Order> orders) {
        super(context, resource,orders);
        this.listData = orders;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layinf = LayoutInflater.from(context);
        convertView = layinf.inflate(R.layout.order_item,parent,false);
        TextView txtTinhTrang = (TextView) convertView.findViewById(R.id.textView_order_tinhtrang);
        TextView txtTongTien = (TextView) convertView.findViewById(R.id.textView_order_tongtien);
        Button btnDeleteOrder = (Button) convertView.findViewById(R.id.button_order_huy);
        TextView txtTime = (TextView) convertView.findViewById(R.id.textView_order_Time);
        Button btnDetail = (Button) convertView.findViewById(R.id.button_order_detail) ;

        Order order = listData.get(position);

        txtTinhTrang.setText(order.getStatus());
        txtTongTien.setText(order.getTongTien());
        txtTime.setText("Thời gian: "+order.getTime());
        convertView.setTag((Integer) position);
        btnDeleteOrder.setTag((Integer)position);
        btnDetail.setTag((Integer)position);
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
