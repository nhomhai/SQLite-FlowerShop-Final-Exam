package com.apphoa.tuanh.projectandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apphoa.tuanh.projectandroid.Adapter.Detail_Adapter;
import com.apphoa.tuanh.projectandroid.Adapter.Order_Adapter;
import com.apphoa.tuanh.projectandroid.Admin.FlowerManagerActivity;
import com.apphoa.tuanh.projectandroid.Database.DetailActioin;
import com.apphoa.tuanh.projectandroid.Database.MySqlHelper;
import com.apphoa.tuanh.projectandroid.Database.OrderAction;
import com.apphoa.tuanh.projectandroid.Database.UserAction;
import com.apphoa.tuanh.projectandroid.Model.Cart;
import com.apphoa.tuanh.projectandroid.Model.Detail;
import com.apphoa.tuanh.projectandroid.Model.Funtion;
import com.apphoa.tuanh.projectandroid.Model.Order;
import com.apphoa.tuanh.projectandroid.Model.SessionManagement;
import com.apphoa.tuanh.projectandroid.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecentOderActivity extends MainActivity {
    SessionManagement sessionManagement;
    List<Order> list_oder;
    List<Detail> list_detail;
    DatabaseReference ref; // lấy Order trên firebase
    ListView listView_order;
    Order_Adapter adapter;
    User user;
    String UserID;
    DatabaseReference user_change_point;
    DatabaseReference detail_order;
    Detail_Adapter detail_adapter;
    // vì mỗi lần đk hay đặng nhập,  mua hàng th2i đều đc thêm vào SQLite
    MySqlHelper mySqlHelper = new MySqlHelper(this);
    UserAction userAction = new UserAction(mySqlHelper);
    DetailActioin detailActioin = new DetailActioin(mySqlHelper);
    OrderAction orderAction = new OrderAction(mySqlHelper);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_oder);
        sessionManagement = new SessionManagement(this);
        sessionManagement.checkLogin();
        user_change_point = FirebaseDatabase.getInstance().getReference("Users");
        list_detail = new ArrayList<>();
        list_oder = new ArrayList<>();
        listView_order = (ListView) findViewById(R.id.recentOrder_litsView);
        String name = sessionManagement.getUsername();
        TextView txt = (TextView) findViewById(R.id.textView_recentOrder_note);
        txt.setText(" Những đơn hàng của "+name);
        UserID = sessionManagement.getUserID();
        user = userAction.GetUserById(UserID);// lay user hien tai dang login
        /// get detail order
        Funtion f = new Funtion();
        if(f.isOnline(this))
        {
            detail_order = FirebaseDatabase.getInstance().getReference("DetailOrder");
            detail_order.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot s : dataSnapshot.getChildren()){
                        Detail detail = s.getValue(Detail.class);
                        list_detail.add(detail);
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
            // get list_order
            ref = FirebaseDatabase.getInstance().getReference("Orders");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    list_oder.clear();
                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                        Order order = snap.getValue(Order.class);
                        if(order.getUser().compareTo(UserID)==0)
                            list_oder.add(order);
                    }
                    adapter = new Order_Adapter(RecentOderActivity.this, R.layout.order_item, list_oder);
                    if(list_oder.size() == 0) Toast.makeText(getBaseContext(),"K co don hang",Toast.LENGTH_LONG).show();
                    listView_order.setAdapter(adapter);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
        else {
            // nếu không online, thì lấy list_order và list_detail từ sqlite khi xem đơn hàng
            list_oder = orderAction.GetOrderByUserId(UserID);
            for(Order o : list_oder){
                List<Detail> temp = new ArrayList<>();
                temp = detailActioin.GetDetailByIdOrder(o.getId());
                list_detail.addAll(temp);
            }
            adapter = new Order_Adapter(RecentOderActivity.this, R.layout.order_item, list_oder);
            if(list_oder.size() == 0) Toast.makeText(getBaseContext(),"K co don hang",Toast.LENGTH_LONG).show();
            listView_order.setAdapter(adapter);
        }

    }
    public void DetailOrder(View v){
        int index = (Integer)v.getTag();
        Order order = list_oder.get(index);
        Intent detail = new Intent(getBaseContext(),DetailOrderActivity.class);
        detail.putExtra("order",order);
        startActivity(detail);
    }

    public  void DeleteOrder(View v)
    {
        Funtion f = new Funtion();
        if(f.isOnline(this)){
            int index = (Integer) v.getTag();
            Order or = list_oder.get(index);
            // giam diem tich luy
            double diem = Double.parseDouble(or.getTongTien())*0.1 ;
            long x = user.getPoint() - (long)diem;
            user.setPoint(x);
            // cap nhat user len firebase
            user_change_point.child(user.getId()).setValue(user);
            // cập nhật user trên sqlite
            userAction.UpdateUser(user);
            // xoa  chi tiet don hang
            for(Detail d : list_detail) // so sanh IDOrder cua chi tiet  voi ID cua Order
            {
                if(d.getIdOrder().compareTo(or.getId()) == 0)
                {
                    detail_order.child(d.getIdDetail()).removeValue();
                    // xóa chi tiết đơn hàng này trên sqlite
                    detailActioin.DeleteDetail(d);
                }
            }

            // xoa don hang
            ref.child(or.getId()).removeValue();
            orderAction.DeleteOrder(or);
        }
        else
            Toast.makeText(this,"You are offline!!",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent back = new Intent(getBaseContext(),Mannager_AccountActivity.class);
        startActivity(back);
    }
}
