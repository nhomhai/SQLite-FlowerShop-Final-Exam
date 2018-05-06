package com.apphoa.tuanh.projectandroid;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.apphoa.tuanh.projectandroid.Database.DetailActioin;
import com.apphoa.tuanh.projectandroid.Database.MySqlHelper;
import com.apphoa.tuanh.projectandroid.Database.OrderAction;
import com.apphoa.tuanh.projectandroid.Database.UserAction;
import com.apphoa.tuanh.projectandroid.Model.Cart;
import com.apphoa.tuanh.projectandroid.Model.CartItem;
import com.apphoa.tuanh.projectandroid.Model.Detail;
import com.apphoa.tuanh.projectandroid.Model.Flower;
import com.apphoa.tuanh.projectandroid.Model.Funtion;
import com.apphoa.tuanh.projectandroid.Model.Order;
import com.apphoa.tuanh.projectandroid.Model.SessionManagement;
import com.apphoa.tuanh.projectandroid.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CheckoutActivity extends MainActivity {
    ArrayList<CartItem> cart = Cart.getCart();

    HashMap<String, String>  user_session = new HashMap<>();
    EditText edtHoTen;
    EditText edtDiaChi;
    EditText edtSdt;
    static String payment = "";
    MySqlHelper mySqlHelper = new MySqlHelper(this);
    OrderAction  orderAction = new OrderAction(mySqlHelper);
    DetailActioin detailActioin = new DetailActioin(mySqlHelper);
    UserAction userAction = new UserAction(mySqlHelper);
    SessionManagement sessionManagement;
    User user;
    DatabaseReference refOrder;
    DatabaseReference refDeatailOrder, user_change_point;
    List<User> temp ;

    String UserID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        //lấy user session
        sessionManagement = new SessionManagement(this);
        temp = new ArrayList<>();
        user_change_point = FirebaseDatabase.getInstance().getReference("Users");
        if(sessionManagement.isLoggedIn())
        {
            UserID = sessionManagement.getUserID();
            user = userAction.GetUserById(UserID);
        }

        refOrder = FirebaseDatabase.getInstance().getReference("Orders");
        refDeatailOrder = FirebaseDatabase.getInstance().getReference("DetailOrder");

        //check CART
        if(cart == null){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Cart Empty");
            builder.setMessage("Your cart is EMPTY so you can't Order!!, return to Home !\n We love you!!");
            builder.setCancelable(false);
            builder.setNegativeButton("OK!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent=  new Intent(getBaseContext(),HomeActivity.class);
                    startActivity(intent);
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        //khai báo view
        edtHoTen = (EditText) findViewById(R.id.editText_hoten_checkout);
        edtDiaChi = (EditText) findViewById(R.id.editText_diachi_checkout);
        edtSdt = (EditText)findViewById(R.id.editText_sdt_checkout);
        // khai báo / setup spinner payment method
        final String choice3[] = {"Payment Methods ", "VISA/MasterCard", "Cash on Delivery"};
        final Spinner spinner_cart = (Spinner) findViewById(R.id.spinner);

        class activity implements android.widget.AdapterView.OnItemSelectedListener {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = spinner_cart.getSelectedItem().toString();
                switch (text){
                    case "VISA/MasterCard":
                        visa();
                        payment =text;
                        break;
                    case "Cash on Delivery":
                        payment = text;
                        break;
                    default:
                        payment="";
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,choice3);
        spinner_cart.setAdapter(adapter1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_cart.setOnItemSelectedListener(new activity());
// check xem có user nào login hay không
        if(user==null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Login to  shopping more convinient!!");
            builder.setCancelable(false);
            // nếu
            builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent login = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(login);
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

    }

    public void order(View v){
        Funtion f = new Funtion();
        // nếu online thì cho đặt hàng
        if(f.isOnline(this)){
            String name = edtHoTen.getText().toString();
            String Diachi = edtDiaChi.getText().toString();
            String sdt = edtSdt.getText().toString();
            //lấy ngày giờ
            SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy ");
            Date time = new Date();
            String _time = formatTime.format(time.getTime());
            Order order = new Order();
            if(!payment.isEmpty())
            {
                //thêm order lên firebase
                String id = refOrder.push().getKey();
                order.setId(id);
                order.setAddress(Diachi);
                order.setName(name);
                order.setPhone(sdt);
                order.setTime(_time);
                order.setStatus(payment);
                order.setTongTien(String.valueOf(Cart.Sum()));
                if(user==null)
                    order.setUser("khach");
                else
                    //order.setUser(user.getId());
                    order.setUser(UserID);

                refOrder.child(id).setValue(order);
                // thay doi diem tich luy:


                double diem = Cart.Sum() * 0.1;
                long ld  = (long) diem;
                long x = user.getPoint() +ld;
                user.setPoint(x);
                // cap nhat user len firebase
                user_change_point.child(user.getId()).setValue(user);
                userAction.UpdateUser(user);
                // thêm order vô SQLite
                orderAction.AddOrder(order);

                // lưu cartItem vô fisebase
                for(CartItem c:cart) {
                    String id_detail = refDeatailOrder.push().getKey();
                    Detail d = new Detail();
                    d.setIdDetail(id_detail);
                    d.setIdOrder(id);
                    Flower temp = c.getFlower();
                    d.setGia(temp.getGia());
                    d.setHinhAnh(temp.getHinhAnh());
                    d.setLoai(temp.getLoai());
                    d.setTen(temp.getTenHoa());
                    d.setSoluong(c.getCount());
                    refDeatailOrder.child(id_detail).setValue(d);

                    // lưu cartItem (DetailOrder) vào SQLite
                    detailActioin.AddDetail(d);
                }

                // show Alert()
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Ordered");
                builder.setMessage("Your Order moved to Flower shop!!, Continue shopping");
                builder.setCancelable(false);
                builder.setNegativeButton("OK!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Cart.EmptyCart();
                        Intent intent=  new Intent(getBaseContext(),HomeActivity.class);
                        startActivity(intent);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
            else
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Ordered");
                builder.setMessage("You must chose the payment method!!");
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
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Offline");
            builder.setMessage("You are offline now, it impossible!!");
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

    }
    public void home(View v){
        Intent intent=  new Intent(getBaseContext(),HomeActivity.class);
        startActivity(intent);
    }
    public void visa(){
        AlertDialog.Builder builder =  new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialog = inflater.inflate(R.layout.visa_cart,null);
        builder.setView(dialog);
        final AlertDialog b = builder.create();
        b.show();
    }
    public void visa_done(View v){
        Toast.makeText(getBaseContext(),"Thank you!!",Toast.LENGTH_SHORT);
    }


}
