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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends MainActivity {
    DatabaseReference ref;
    ArrayList<User> list_user;
    HashMap<String,String> user_session,user_session2;
    SessionManagement session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new SessionManagement(this);
        //kiểm tra có mạng hay không
        Funtion f = new Funtion();
        if(!f.isOnline(this))
        //nếu không có mạng, thì hiện thông báo và k cho login
        {
            Toast.makeText(this,"You are Offline, Connect to Login",Toast.LENGTH_SHORT).show();
            finish();
        }
        ref = FirebaseDatabase.getInstance().getReference("Users");
        list_user = new ArrayList<>();
    }
    public void showAlertDialog(String s)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("FlowerShop");
        builder.setMessage(s);
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
    @Override
    protected void onStart() {
        super.onStart();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list_user.clear();
                for(DataSnapshot user_snapshot : dataSnapshot.getChildren())
                {
                    User user = user_snapshot.getValue(User.class);
                    list_user.add(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void forget_password(View v){
        Intent intent = new Intent(getBaseContext(),ForgetPasswordActivity.class);
        startActivity(intent);
    }

    public void login (View v)
    {
        User user = new User();
        final String text1;
        final String text2;
        String id;
        String password;
        EditText userid = (EditText) findViewById(R.id.editText_userID_login);
        EditText pass = (EditText) findViewById(R.id.editText_password_login);
        text1 = userid.getText().toString();
        text2 = pass.getText().toString();
        int dem = 0;
        if(text1.isEmpty()==true||text2.isEmpty()==true)
        {
            showAlertDialog("yêu cầu nhập đầy đủ UserID và Password");
            userid.setText(null);
            pass.setText(null);
        }
        else
        {
            for (User user_test : list_user) {
                id = user_test.getTen();
                password = user_test.getPassword();
                if ((text1.compareTo(id) == 0) && (text2.compareTo(password) == 0)) {
                    dem++;
                    showAlertDialog("bạn đã đăng nhập thành công");
                    // thêm user đã login vào session
                    session.createLoginSession(user_test.getId(),user_test.getTen(),user_test.getAddress(),user_test.getPhone());
                    //thêm user đã login vào sql lite
                    MySqlHelper mySqlHelper = new MySqlHelper(this);
                    UserAction userAction = new UserAction(mySqlHelper);
                    userAction.AddUser(user_test);

                    Intent gotohome = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(gotohome);
                    break;
                }
            }
            if (dem == 0) {
                showAlertDialog("UserID hoặc Password không đúng");
            }
        }
    }
    public void register(View v){
        Intent intent = new Intent(getBaseContext(),RegisterActivity.class);
        startActivity(intent);
    }
}
