package com.apphoa.tuanh.projectandroid.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.apphoa.tuanh.projectandroid.Model.CartItem;
import com.apphoa.tuanh.projectandroid.Model.Flower;
import com.apphoa.tuanh.projectandroid.Model.Funtion;
import com.apphoa.tuanh.projectandroid.R;

import java.util.List;

/**
 * Created by TuAnh on 4/20/2018.
 */

public class CartAdapter extends ArrayAdapter<CartItem> {
    private List<CartItem> listCart;
    private int resource;
    private Context context;

    public int getResource() {
        return resource;
    }

    public List<CartItem> getListData() {
        return listCart;
    }

    public CartAdapter(@NonNull Context context, int resource, @NonNull List<CartItem> objects) {
        super(context, resource, objects);
        this.listCart = objects;
        this.resource = resource;
        this.context = context;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layinf = LayoutInflater.from(context);
        convertView = layinf.inflate(R.layout.cart_item, parent, false);
        // khai báo các view
        ImageView img = (ImageView) convertView.findViewById(R.id.imageView_image_cart_item);
        TextView txtName = (TextView)convertView.findViewById(R.id.textView_name_listitem_cart);
        TextView txtPrice = (TextView)convertView.findViewById(R.id.textView_price_listitem_cart);
        EditText edtCount = (EditText)convertView.findViewById(R.id.editText_sl_listitem_cart);
        Button btnAddItem = (Button)convertView.findViewById(R.id.button_add_listitem_cart);
        Button btnSubItem = (Button)convertView.findViewById(R.id.button_sub_listitem_cart);
        Button btnremove = (Button)convertView.findViewById(R.id.button_remove_listitem_cart);
        Button btnupdate = (Button)convertView.findViewById(R.id.button_update_cartItem);
        TextView txtTotal = (TextView)convertView.findViewById(R.id.textView_total_cartItem);

        //setTag() cho các btn
        btnAddItem.setTag((Integer) position);
        btnSubItem.setTag((Integer) position);
        btnremove.setTag((Integer) position);
        btnupdate.setTag((Integer)position);
        CartItem cartItem = listCart.get(position);
        Flower flower = cartItem.getFlower();
        // set image lên img
        Funtion f = new Funtion();
        img.setImageResource(f.getMipmapResIdByName(flower.getHinhAnh(),context));
        //double gia = Double.parseDouble(flower.getGia());
        txtName.setText(flower.getTenHoa());
        txtPrice.setText(flower.getGia());
        int sl = cartItem.getCount();
        edtCount.setText(String.valueOf(cartItem.getCount()));
        String total = String.valueOf(cartItem.getCount()*Double.parseDouble(flower.getGia()));
        txtTotal.setText("Total: "+total);
        return  convertView;
    }
    @Override
    public int getPosition(@Nullable CartItem item) {
        return super.getPosition(item);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
