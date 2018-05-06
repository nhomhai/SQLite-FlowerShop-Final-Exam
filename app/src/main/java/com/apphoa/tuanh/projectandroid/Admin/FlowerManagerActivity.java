package com.apphoa.tuanh.projectandroid.Admin;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.apphoa.tuanh.projectandroid.Adapter.CustomListAdapter_FlowerManager;
import com.apphoa.tuanh.projectandroid.Adapter.Search_Adapter;
import com.apphoa.tuanh.projectandroid.Database.MySqlHelper;
import com.apphoa.tuanh.projectandroid.Database.UserAction;
import com.apphoa.tuanh.projectandroid.Model.Flower;
import com.apphoa.tuanh.projectandroid.Model.SessionManagement;
import com.apphoa.tuanh.projectandroid.Model.User;
import com.apphoa.tuanh.projectandroid.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FlowerManagerActivity extends AppCompatActivity {
    SessionManagement sessionManagement ;
    DatabaseReference ref;
    static String id;
    static String image;

    ArrayList<Flower> listdata = new ArrayList<>();
    public static final int rq = 100;
    @Override
    protected void onStart() {
        super.onStart();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void  onDataChange(DataSnapshot dataSnapshot) {
                listdata.clear();
                for(DataSnapshot user_snapshot : dataSnapshot.getChildren())
                {
                    Flower flower = user_snapshot.getValue(Flower.class);
                    listdata.add(flower);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower_manager);
        sessionManagement= new SessionManagement(this);
        sessionManagement.checkLogin();
        if(sessionManagement.isLoggedIn()){
            // ktra quyền của user
            MySqlHelper mySqlHelper = new MySqlHelper(this);
            UserAction userAction = new UserAction(mySqlHelper);
            String UserID = sessionManagement.getUserID();
            User u = userAction.GetUserById(UserID);
            if (u.equals(null)) finish();
            if(u.getPermission() == 0) {
                Toast.makeText(this,"You do not have Permission!",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        ref = FirebaseDatabase.getInstance().getReference("Flower");

        final Spinner spinner7 = (Spinner) findViewById(R.id.spinner7);
        final String choice7[] = {"All of Flower","Best Seller Flower","Single Flower","Dried Flower"};
        class activity implements android.widget.AdapterView.OnItemSelectedListener {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                String text = spinner7.getSelectedItem().toString();
                if(text.compareTo("All of Flower")==0)
                {
                    ListView listView = (ListView) findViewById(R.id.listview_flower_manager);
                    listView.setAdapter(new Search_Adapter(FlowerManagerActivity.this,R.layout.search_item,listdata));
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,choice7);
        spinner7.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner7.setOnItemSelectedListener(new activity());
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == rq ) {
            String name = data.getStringExtra("name");
            String price = data.getStringExtra("price");
            String status = data.getStringExtra("status");
            String detail = data.getStringExtra("detail");
            String species = data.getStringExtra("species");
            Flower fl = new Flower();
            fl.setTenHoa(name);
            fl.setGia(price);
            fl.setStatus(status);
            fl.setLoai(species);
            fl.setMoTa(detail);
            fl.setID(id);
            fl.setHinhAnh(image);
            ref.child(id).setValue(fl);
            ListView listView = (ListView) findViewById(R.id.listview_flower_manager);
            listView.setAdapter(new CustomListAdapter_FlowerManager(this,listdata));
        }
    }
    public void add_flower(View v)
    {
        Intent add_flower = new Intent(this,add_flowerActivity.class);
        startActivity(add_flower);
    }
    public void delete (View v)
    {
        Integer index = (Integer)v.getTag();
        ArrayList<Flower> data = listdata;
        Flower f = data.get(index);
        listdata.remove(f);
        ref.child(f.getID()).removeValue();
        ListView listView = (ListView) findViewById(R.id.listview_flower_manager);
        listView.setAdapter(new CustomListAdapter_FlowerManager(this,listdata));
    }
    public void edit_info_flower (View v)
    {
        Integer index = (Integer)v.getTag();
        ArrayList<Flower> data = listdata;
        Flower f = data.get(index);
        id = f.getID();
        image = f.getHinhAnh();
        Intent edit = new Intent(this,edit_info_flower.class);
        this.startActivityForResult(edit,rq);

    }

}
