package com.apphoa.tuanh.projectandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.apphoa.tuanh.projectandroid.Database.MySqlHelper;
import com.apphoa.tuanh.projectandroid.Database.UserAction;
import com.apphoa.tuanh.projectandroid.Model.Funtion;
import com.apphoa.tuanh.projectandroid.Model.SessionManagement;
import com.apphoa.tuanh.projectandroid.Model.User;
import com.google.firebase.database.FirebaseDatabase;

public class EditInfoActivity extends AppCompatActivity {
    SessionManagement sessionManagement;
    User user;
    MySqlHelper mySqlHelper = new MySqlHelper(this);
    UserAction userAction = new UserAction(mySqlHelper);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_info);
        sessionManagement =new SessionManagement(this);
        sessionManagement.checkLogin();
        user = new User();
        Funtion f = new Funtion();
        if(!f.isOnline(this)){
            Toast.makeText(this,"you are offline now",Toast.LENGTH_LONG).show();
            finish();
        }
        else {
            user = userAction.GetUserById(sessionManagement.getUserID());
        }

    }
    public void back (View v)
    {
        this.onBackPressed();
    }
    public void showAlertDialog(String string)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("FlowerShop");
        builder.setMessage(string);
        builder.setCancelable(false);
        builder.setNegativeButton("OK!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void showAlertDialog_success()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("FlowerShop");
        builder.setMessage("bạn đã cập nhật thông tin thành công");
        builder.setCancelable(false);
        builder.setNegativeButton("OK!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void update (View v)
    {
        Funtion f = new Funtion();
        if(f.isOnline(this)){
            EditText fullname = (EditText) findViewById(R.id.editText_editinfo_fullname);
            EditText address = (EditText) findViewById(R.id.editText_editinfo_address);
            EditText email = (EditText) findViewById(R.id.editText_editinfo_email);
            EditText phone = (EditText) findViewById(R.id.editText_editinfo_phone);
            String text1 = fullname.getText().toString();
            String text2 = address.getText().toString();
            String text3 = email.getText().toString();
            String text4 = phone.getText().toString();
            if(text1.isEmpty()==true||text2.isEmpty()==true||text3.isEmpty()==true)
            {
                showAlertDialog("bạn cần điền đầy đủ thông tin!");
            }
            else
            {
                user.setTen(text1);
                user.setAddress(text2);
                user.setEmail(text3);
                user.setPhone(text4);
                userAction.UpdateUser(user);
                FirebaseDatabase.getInstance().getReference("Users").child(user.getId()).setValue(user);
                showAlertDialog_success();
            }
        }
        else {
            showAlertDialog("You are offline now!!");
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent back = new Intent(getBaseContext(),Mannager_AccountActivity.class);
        startActivity(back);
    }

}
