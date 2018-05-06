package com.apphoa.tuanh.projectandroid.Admin;

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
import com.apphoa.tuanh.projectandroid.Model.Flower;
import com.apphoa.tuanh.projectandroid.Model.SessionManagement;
import com.apphoa.tuanh.projectandroid.Model.User;
import com.apphoa.tuanh.projectandroid.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_flowerActivity extends AppCompatActivity {
    DatabaseReference ref;
    SessionManagement sessionManagement ;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flower);
        Intent intent = this.getIntent();
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
    }

    public void add (View v)
    {
        EditText name = (EditText) findViewById(R.id.edittext_name_addflower);
        EditText price = (EditText) findViewById(R.id.edittext_price_addflower);
        EditText image = (EditText) findViewById(R.id.edittext_image_addflower);
        EditText status = (EditText) findViewById(R.id.edittext_status_addflower);
        EditText species = (EditText) findViewById(R.id.edittext_species_addflower);
        EditText detail = (EditText) findViewById(R.id.edittext_detail_addflower);

        String name_text = name.getText().toString();
        String price_text = price.getText().toString();
        String image_text = image.getText().toString();
        String status_text = status.getText().toString();
        String species_text = species.getText().toString();
        String detail_text = detail.getText().toString();

        final Flower flower = new Flower();
        flower.setTenHoa(name_text);
        flower.setHinhAnh(image_text);
        flower.setGia(price_text);
        flower.setStatus(status_text);
        flower.setLoai(species_text);
        flower.setMoTa(detail_text);
        if(name_text.isEmpty()==true||price_text.isEmpty()==true||image_text.isEmpty()==true||status_text.isEmpty()==true||species_text.isEmpty()==true)
        {
            showAlertDialog("bạn cần điền đầy đủ thông tin hoa!");
        }
        else
        {
            String id = ref.push().getKey().toString();
            flower.setID(id);
            ref.child(id).setValue(flower);
            showAlertDialog("thêm hoa thành công!");
            Intent sucess = new Intent(add_flowerActivity.this, FlowerManagerActivity.class);
            startActivity(sucess);
        }
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
    public void back (View v)
    {
        this.onBackPressed();
    }

}
