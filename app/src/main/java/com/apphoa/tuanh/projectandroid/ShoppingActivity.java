package com.apphoa.tuanh.projectandroid;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apphoa.tuanh.projectandroid.Adapter.Item_Adapter;
import com.apphoa.tuanh.projectandroid.Database.FlowerAction;
import com.apphoa.tuanh.projectandroid.Database.MySqlHelper;
import com.apphoa.tuanh.projectandroid.Model.Cart;
import com.apphoa.tuanh.projectandroid.Model.Flower;
import com.apphoa.tuanh.projectandroid.Model.Funtion;
import com.apphoa.tuanh.projectandroid.Model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShoppingActivity extends MainActivity {
    GridView viewHoa;
    Item_Adapter adapter;
    List<Flower> data ;
    static Flower fl = new Flower();
    MySqlHelper sqlHelper = new MySqlHelper(this);
    FlowerAction flowerAction = new FlowerAction(sqlHelper);
    DatabaseReference referenceFlower;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        viewHoa = (GridView)findViewById(R.id.gridHoa);
        data = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Funtion f =  new Funtion();
        if(f.isOnline(getBaseContext())){
            referenceFlower = FirebaseDatabase.getInstance().getReference("Flower");
            referenceFlower.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    data.clear();

                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                        Flower flower = postSnapshot.getValue(Flower.class);
                        data.add(flower);
                    }
                    for(Flower tem : data){
                        flowerAction.AddFlower(tem);
                    }
                    adapter = new Item_Adapter(ShoppingActivity.this,R.layout.layout_hoa_item,data);
                    viewHoa.setAdapter(adapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        else {
            data = flowerAction.getAllFlower();
            adapter = new Item_Adapter(ShoppingActivity.this,R.layout.layout_hoa_item,data);
            viewHoa.setAdapter(adapter);
        }

    }

    public void BestSeller(View v){
        // database chưa có nhiều loại
        adapter = new Item_Adapter(this,R.layout.layout_hoa_item,data);
        viewHoa.setAdapter(adapter);
    }
    public void Singel(View v){
        data = flowerAction.getTypeFlower("dried");
        adapter = new Item_Adapter(this,R.layout.layout_hoa_item,data);
        viewHoa.setAdapter(adapter);
    }
    public void Order(View v){
        data = flowerAction.getTypeFlower("order");
        adapter = new Item_Adapter(this,R.layout.layout_hoa_item,data);
        viewHoa.setAdapter(adapter);
    }
    public void ChiTiet(View v){
        Integer index = (Integer) v.getTag() ;
        fl = data.get(index);
       AlertDialog.Builder builder =  new AlertDialog.Builder(this);
       LayoutInflater inflater = getLayoutInflater();
       final View dialog = inflater.inflate(R.layout.datail_flower,null);
       builder.setView(dialog);
        TextView txtname = (TextView)dialog.findViewById(R.id.textView_name_detail_flower);
        TextView txtloai = (TextView)dialog.findViewById(R.id.textView_loai_detail_flower);
        TextView txtGia = (TextView)dialog.findViewById(R.id.textView_gia_detail_flower);
        TextView txtTinhtrang = (TextView)dialog.findViewById(R.id.textView_status_detail_flower);
        TextView txtCount = (TextView)dialog.findViewById(R.id.textView_count_detail_flower);
        TextView txtMota = (TextView)dialog.findViewById(R.id.textView_mota_detail_flower);
        ImageView img = (ImageView)dialog.findViewById(R.id.Image_detail_flower);
        Button btnOrder = (Button)dialog.findViewById(R.id.button_mua_detail_flower);
        txtname.setText(fl.getTenHoa());
        txtloai.setText(fl.getLoai());
        txtGia.setText(fl.getGia());
        txtTinhtrang.setText(fl.getStatus());
        txtCount.setText(String.valueOf(fl.getSoluong()));
        txtMota.setText(fl.getMoTa());
        Funtion f = new Funtion();
        img.setImageResource(f.getMipmapResIdByName(fl.getHinhAnh(),this));
        if(!f.isOnline(this))
            btnOrder.setEnabled(false);
        builder.setTitle(fl.getTenHoa());
        final AlertDialog b = builder.create();
        b.show();
    }
    public void Buy (View v)
    {
        Integer index;
        index = (Integer) v.getTag();
        Cart.AddToCart(fl);
        Toast.makeText(this,"da them vao gio hang",Toast.LENGTH_SHORT).show();
    }
    public void GoToCart (View v)
    {
        Intent intent = new Intent(ShoppingActivity.this, CartActivity.class);
        startActivity(intent);
    }
    public void AddtoCart(View v){
        Integer index;
        index = (Integer) v.getTag();
        fl = data.get(index);
        Cart.AddToCart(fl);
        Toast.makeText(this,"da them vao gio hang",Toast.LENGTH_SHORT).show();
    }
}
