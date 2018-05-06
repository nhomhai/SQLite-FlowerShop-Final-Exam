package com.apphoa.tuanh.projectandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.apphoa.tuanh.projectandroid.Adapter.Detail_Adapter;
import com.apphoa.tuanh.projectandroid.Database.DetailActioin;
import com.apphoa.tuanh.projectandroid.Database.MySqlHelper;
import com.apphoa.tuanh.projectandroid.Database.OrderAction;
import com.apphoa.tuanh.projectandroid.Database.UserAction;
import com.apphoa.tuanh.projectandroid.Model.Detail;
import com.apphoa.tuanh.projectandroid.Model.Funtion;
import com.apphoa.tuanh.projectandroid.Model.Order;
import com.apphoa.tuanh.projectandroid.Model.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailOrderActivity extends MainActivity {
    SessionManagement sessionManagement;
    List<Detail> list_detail;
    DatabaseReference detail_order;
    MySqlHelper mySqlHelper = new MySqlHelper(this);
    UserAction userAction = new UserAction(mySqlHelper);
    DetailActioin detailActioin = new DetailActioin(mySqlHelper);
    OrderAction orderAction = new OrderAction(mySqlHelper);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_list);
        sessionManagement = new SessionManagement(this);
        sessionManagement.checkLogin();
        String UserID = sessionManagement.getUserID();
        list_detail = new ArrayList<>();
        Intent intent = getIntent();
        final Order order = (Order)intent.getSerializableExtra("order");
        /// get detail order
        Funtion f = new Funtion();
        if (f.isOnline(this)) {
            detail_order = FirebaseDatabase.getInstance().getReference("DetailOrder");
            detail_order.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot s : dataSnapshot.getChildren()) {
                        Detail detail = s.getValue(Detail.class);
                        if(detail.getIdOrder().compareTo(order.getId()) ==0)
                        list_detail.add(detail);
                    }
                    ListView detail = (ListView) findViewById(R.id.listView_detail);
                    Detail_Adapter detail_adapter = new Detail_Adapter(getBaseContext(), R.layout.detail_item, list_detail);
                    detail.setAdapter(detail_adapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        } else {
            // nếu không online, thì lấy list_order và list_detail từ sqlite khi xem đơn hàng
            list_detail = detailActioin.GetDetailByIdOrder(order.getId());

            ListView detail = (ListView) findViewById(R.id.listView_detail);
            Detail_Adapter detail_adapter = new Detail_Adapter(getBaseContext(), R.layout.detail_item, list_detail);
            detail.setAdapter(detail_adapter);
        }
    }
}
