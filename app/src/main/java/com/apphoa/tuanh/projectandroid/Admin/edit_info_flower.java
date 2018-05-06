package com.apphoa.tuanh.projectandroid.Admin;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.apphoa.tuanh.projectandroid.Database.MySqlHelper;
import com.apphoa.tuanh.projectandroid.Database.UserAction;
import com.apphoa.tuanh.projectandroid.Model.SessionManagement;
import com.apphoa.tuanh.projectandroid.Model.User;
import com.apphoa.tuanh.projectandroid.R;

public class edit_info_flower extends AppCompatActivity {
    SessionManagement sessionManagement ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info_flower);
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
    public void update(View v)
    {

        EditText name = (EditText) findViewById(R.id.editText_editflower_name);
        EditText price = (EditText) findViewById(R.id.editText_editflower_price);
        EditText status = (EditText) findViewById(R.id.editText_editflower_status);
        EditText detail = (EditText) findViewById(R.id.editText_editflower_detail);
        EditText species = (EditText) findViewById(R.id.editText_editflower_species);

        String text_name = name.getText().toString();
        String text_price = price.getText().toString();
        String text_stt = status.getText().toString();
        String text_detail = detail.getText().toString();
        String text_species = species.getText().toString();

        Intent data = new Intent(this,FlowerManagerActivity.class);

        data.putExtra("name",text_name);
        data.putExtra("price",text_price);
        data.putExtra("status",text_stt);
        data.putExtra("detail",text_detail);
        data.putExtra("species",text_species);

        this.setResult(Activity.RESULT_OK, data);
        super.finish();
    }
}
