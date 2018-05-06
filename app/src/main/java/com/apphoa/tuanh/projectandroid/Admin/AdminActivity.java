package com.apphoa.tuanh.projectandroid.Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.apphoa.tuanh.projectandroid.Database.MySqlHelper;
import com.apphoa.tuanh.projectandroid.Database.UserAction;
import com.apphoa.tuanh.projectandroid.Model.SessionManagement;
import com.apphoa.tuanh.projectandroid.Model.User;
import com.apphoa.tuanh.projectandroid.R;

public class AdminActivity extends AppCompatActivity {
    SessionManagement sessionManagement ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
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
    }
    public void flower(View v)
    {
        Intent flower_manager = new Intent(AdminActivity.this,FlowerManagerActivity.class);
        startActivity(flower_manager);
    }
}
