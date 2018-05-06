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
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    DatabaseReference ref;
    MySqlHelper db = new MySqlHelper(this);
    UserAction userAction = new UserAction(db);
    SessionManagement sessionManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sessionManagement = new SessionManagement(this);
        if(sessionManagement.isLoggedIn()){
            Toast.makeText(this,"Logout to Register",Toast.LENGTH_SHORT).show();
        }
        ref = FirebaseDatabase.getInstance().getReference("Users");
    }

    public void register(View v)
    {
        EditText userid = (EditText) findViewById(R.id.editText_register_userID);
        EditText pass = (EditText) findViewById(R.id.editText_register_password);
        EditText address = (EditText) findViewById(R.id.editText_register_address);
        EditText email = (EditText) findViewById(R.id.editText_register_email);
        EditText fullname = (EditText) findViewById(R.id.editText_register_fullname);
        EditText phone = (EditText) findViewById(R.id.editText_register_phone);

        String userid_text = userid.getText().toString();
        String password_text = pass.getText().toString();
        String address_text = address.getText().toString();
        String email_text = email.getText().toString();
        String fullname_text = fullname.getText().toString();
        String phone_text = phone.getText().toString();

        final User user = new User();
        user.setPassword(password_text);
        user.setAddress(address_text);
        user.setEmail(email_text);
        user.setTen(fullname_text);
        user.setPhone(phone_text);
        user.setPermission(0);
        user.setPoint(0);

        String id = ref.push().getKey();
        user.setId(id);
        ref.child(id).setValue(user);
        //thêm user vào sqlite
        userAction.AddUser(user);
        // showAlert()
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("FlowerShop");
        builder.setMessage("Bạn đã đăng kí thành công");
        builder.setCancelable(false);
        builder.setNegativeButton("OK!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        ///
        // thêm user đã login vào session
        sessionManagement.createLoginSession(user.getId(),user.getTen(),user.getAddress(),user.getPhone());
        //thêm user đã login vào sql lite
        MySqlHelper mySqlHelper = new MySqlHelper(this);
        UserAction userAction = new UserAction(mySqlHelper);
        userAction.AddUser(user);
        Intent intent =  new Intent(getBaseContext(),HomeActivity.class);
        startActivity(intent);
    }
}
