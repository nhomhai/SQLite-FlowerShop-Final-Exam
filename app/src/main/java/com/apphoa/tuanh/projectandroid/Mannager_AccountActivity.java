package com.apphoa.tuanh.projectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.apphoa.tuanh.projectandroid.Database.MySqlHelper;
import com.apphoa.tuanh.projectandroid.Database.UserAction;
import com.apphoa.tuanh.projectandroid.Model.SessionManagement;
import com.apphoa.tuanh.projectandroid.Model.User;

public class Mannager_AccountActivity extends MainActivity {
    // lay61 tu72 session
    SessionManagement sessionManagement;
    User user = null;
    MySqlHelper mySqlHelper = new MySqlHelper(this);
    UserAction userAction = new UserAction(mySqlHelper);
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sessionManagement = new SessionManagement(this);
        sessionManagement.checkLogin();
        if(sessionManagement.isLoggedIn()){
            String UserID = sessionManagement.getUserID();
            user  = userAction.GetUserById(UserID);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_account);
        TextView txtName = (TextView) findViewById(R.id.textView_manageraccount_fullname);
        TextView txtAdr = (TextView) findViewById(R.id.textView_manageraccount_address);
        TextView txtEmail = (TextView) findViewById(R.id.textView_manageraccount_email);
        TextView txtPoint = (TextView) findViewById(R.id.textView_manageracout_point);
        txtEmail.setText(user.getEmail());
        txtAdr.setText(user.getAddress());
        txtName.setText(user.getTen());
        txtPoint.setText(String.valueOf(user.getPoint()));
    }
    public void back (View v)
    {
        this.onBackPressed();
    }
    public void changepass (View v)
    {
        Intent changepass = new Intent(getBaseContext(),ChangePasswordActivity.class);
        startActivity(changepass);
    }
    public void edit (View v)
    {
        Intent edit = new Intent(getBaseContext(),EditInfoActivity.class);
        startActivity(edit);
    }
    public void RecentOrder(View v){
        Intent recent = new Intent(getBaseContext(),RecentOderActivity.class);
        startActivity(recent);
    }
}
