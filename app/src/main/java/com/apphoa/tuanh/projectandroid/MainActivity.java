package com.apphoa.tuanh.projectandroid;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.apphoa.tuanh.projectandroid.Adapter.Item_Adapter;
import com.apphoa.tuanh.projectandroid.Adapter.Search_Adapter;
import com.apphoa.tuanh.projectandroid.Database.FlowerAction;
import com.apphoa.tuanh.projectandroid.Database.MySqlHelper;
import com.apphoa.tuanh.projectandroid.Model.Flower;
import com.apphoa.tuanh.projectandroid.Model.Funtion;
import com.apphoa.tuanh.projectandroid.Model.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SessionManagement sessionManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // load file menu vô "menu"
        getMenuInflater().inflate(R.menu.menu_home,menu);
        // kiểm tra, nếu đã login thì cho log out
         sessionManagement= new SessionManagement(this);
        if(sessionManagement.isLoggedIn())
        {
            MenuItem item = (MenuItem) menu.findItem(R.id.mnLogin);
            item.setTitle("Logout");
        }


        return  true;
    }
    // xử lí action cho menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       // int id = item.getItemId(); // lấy id của item vừa đc click
        String title = item.getTitle().toString();
        switch (title){
            case "Home":
                Intent intHome = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intHome);
                break;
            case "About us":
                Intent intAbout = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intAbout);
                break;
            case "Manager Account":
                Intent intManager = new Intent(MainActivity.this, Mannager_AccountActivity.class);
                startActivity(intManager);
                break;
            case "Map":
                Intent maps = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/place/Học+Viện+Công+Nghệ+Bưu+Chính+Viễn+Thông+Cơ+Sở+2/@10.848037,106.7843943,17z/data=!3m1!4b1!4m5!3m4!1s0x31752772b245dff1:0xb838977f3d419d!8m2!3d10.848037!4d106.786583"));
                startActivity(maps);
                break;
            case "Login":
                Intent login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(login);
                break;
            case "Logout":
                sessionManagement.logoutUser();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void cart(View v){
        Intent cart = new Intent(getBaseContext(),CartActivity.class);
        startActivity(cart);
    }
    public void Search(View v){
        Intent intent = new Intent(getBaseContext(),SearchActivity.class);
        startActivity(intent);
    }
    public  void Home(View v){
        Intent intent = new Intent(getBaseContext(),HomeActivity.class);
        startActivity(intent);
    }
    public  void User(View v){
        Intent intent = new Intent(getBaseContext(),Mannager_AccountActivity.class);
        startActivity(intent);
    }
}
