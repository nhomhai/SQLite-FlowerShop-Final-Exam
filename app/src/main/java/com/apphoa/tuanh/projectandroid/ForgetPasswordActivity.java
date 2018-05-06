package com.apphoa.tuanh.projectandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.apphoa.tuanh.projectandroid.Model.Funtion;

public class ForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
    }
    public void OK (View v)
    {
        EditText userid = (EditText) findViewById(R.id.editText_userID_forgetpassword);
        String text = userid.getText().toString();
        Funtion f =  new Funtion();
        if(text.isEmpty()==true)
        {
           f. showAlertDialog("bạn chưa nhập userid",getBaseContext());
        }
        // chưa viết hàm checked radiogroup. lười zZ
        else
            f.showAlertDialog("vui lòng chờ mật khẩu gửi về trong giây lát",getBaseContext());
    }
}
