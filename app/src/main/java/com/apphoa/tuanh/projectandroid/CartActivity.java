package com.apphoa.tuanh.projectandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apphoa.tuanh.projectandroid.Adapter.CartAdapter;
import com.apphoa.tuanh.projectandroid.Database.FlowerAction;
import com.apphoa.tuanh.projectandroid.Model.Cart;
import com.apphoa.tuanh.projectandroid.Model.CartItem;
import com.apphoa.tuanh.projectandroid.Model.Flower;

import java.util.ArrayList;

public class CartActivity extends MainActivity {
    ArrayList<CartItem> list_cart = Cart.getCart();
    GridView gridCart;
    CartAdapter adapter;
    TextView txtTotalsum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        if(list_cart.isEmpty())
        {   Toast.makeText(this,"Cart is Empty!!",Toast.LENGTH_SHORT).show();
            finish();
        }
        gridCart = (GridView)this.findViewById(R.id.gridCart);
        adapter = new CartAdapter(this,R.layout.cart_item,list_cart);
        gridCart.setAdapter(adapter);
        txtTotalsum =(TextView) this.findViewById(R.id.txtSumTotal);
        Double sum = Cart.Sum();
        txtTotalsum.setText(String.valueOf(sum));

    }
    public void Checkout(View v){
        Intent intent = new Intent(getBaseContext(),CheckoutActivity.class);
        startActivity(intent);
    }
    public void AddItemCart(View v){
        Integer index = (Integer)v.getTag();
        CartItem temp = Cart.getFlower(index);
        temp.setCount(temp.getCount()+1);
        if(temp.getCount()==0)
            Cart.Remove(index);
        Intent call = new Intent(getBaseContext(),CartActivity.class);
        startActivity(call);
    }
    public void SubItemCart(View v){
        Integer index = (Integer)v.getTag();
        CartItem temp = Cart.getFlower(index);
        temp.setCount(temp.getCount()-1);
        if(temp.getCount()==0)
            Cart.Remove(index);
        Intent call = new Intent(getBaseContext(),CartActivity.class);
        startActivity(call);
    }
    public  void  Remove(View v){
        Integer index = (Integer)v.getTag();
        //CartItem temp = Cart.getFlower(index);
        Cart.Remove(index);
        Intent call = new Intent(getBaseContext(),CartActivity.class);
        startActivity(call);
    }
    public void Update(View view){
        Integer index = (Integer)view.getTag();
        CartItem temp = Cart.getFlower(index);
        Flower flo = temp.getFlower();
        View v = null;
        v = adapter.getView(index,view,gridCart);
        EditText edtCount = (EditText)v.findViewById(R.id.editText_sl_listitem_cart);
        int tem = Integer.parseInt(edtCount.getText().toString());
        temp.setCount(tem);
        if(temp.getCount()==0) Cart.Remove(index);
        Intent itent = new Intent(getBaseContext(),CartActivity.class);
        startActivity(itent);
    }
}
