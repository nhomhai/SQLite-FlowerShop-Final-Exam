package com.apphoa.tuanh.projectandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.apphoa.tuanh.projectandroid.Model.Funtion;
import com.apphoa.tuanh.projectandroid.Model.SessionManagement;

public class ChangePasswordActivity extends MainActivity {
    SessionManagement sessionManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chang_password);
        sessionManagement =new SessionManagement(this);
        sessionManagement.checkLogin();
        Funtion f = new Funtion();
        if(!f.isOnline(this)){
            Toast.makeText(this,"you are offline now",Toast.LENGTH_LONG).show();
            finish();
        }
    }
    public void back (View v)
    {
        this.onBackPressed();
    }
    public void showAlertDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("FlowerShop");
        builder.setMessage("bạn cần điền đầy đủ thông tin!");
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
        builder.setMessage("bạn đã đổi mật khẩu thành công");
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
    public void showAlertDialog_error()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("FlowerShop");
        builder.setMessage("mật khẩu mới không trùng nhau!");
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
    public void change (View v)
    {
        EditText userid = (EditText) findViewById(R.id.editText_userID_changepassword);
        EditText pass = (EditText) findViewById(R.id.editText_newpassword_changepassword);
        EditText newpass = (EditText) findViewById(R.id.editText_newpassword_changepassword);
        EditText retype = (EditText) findViewById(R.id.editText_retypenew_changepassword);

        String text1 = userid.getText().toString();
        String text2 = pass.getText().toString();
        String text3 = newpass.getText().toString();
        String text4 = retype.getText().toString();


        if(text1.isEmpty()==true||text2.isEmpty()==true||text3.isEmpty()==true||text4.isEmpty()==true)
        {
            showAlertDialog();
        }
        else
        {
            if(text3.compareTo(text4)!=0)
            {
                showAlertDialog_error();
                newpass.setText(null);
                retype.setText(null);
            }
            else
                showAlertDialog_success();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent back = new Intent(getBaseContext(),Mannager_AccountActivity.class);
        startActivity(back);
    }
}
