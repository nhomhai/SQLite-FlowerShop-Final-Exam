package com.apphoa.tuanh.projectandroid;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apphoa.tuanh.projectandroid.Adapter.Search_Adapter;
import com.apphoa.tuanh.projectandroid.Database.FlowerAction;
import com.apphoa.tuanh.projectandroid.Database.MySqlHelper;
import com.apphoa.tuanh.projectandroid.Model.Cart;
import com.apphoa.tuanh.projectandroid.Model.Flower;
import com.apphoa.tuanh.projectandroid.Model.Funtion;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends MainActivity  implements SearchView.OnQueryTextListener {
     List<Flower> data;// list chua data
     List<Flower> SearchList;// list chua flower search
    static Flower fl;
    static String seacrh;
    SearchView searchView;
    ListView listSearch;
    Search_Adapter adapter;
    DatabaseReference referenceFlower;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        listSearch = (ListView) findViewById(R.id.listSearch);
        data =  new ArrayList<>();
        SearchList = new ArrayList<>();
        searchView = (SearchView)findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(this);
        // get data???
        MySqlHelper mySqlHelper = new MySqlHelper(getBaseContext());
        FlowerAction flowerAction = new FlowerAction(mySqlHelper);
        data = flowerAction.getAllFlower();
        SearchList.addAll(data);
// khi chua searh thi2 list rong
        adapter = new Search_Adapter(getBaseContext(),R.layout.search_item, SearchList);
        listSearch.setAdapter(adapter);

        listSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fl = data.get(position);
                ChiTiet(view,fl); // vì gọi trực tiếp cái Alert Builder vô trong hàm oncreate thì nó báo lội ,nên phãi dùng hàm lag82 nhằng như vầy
            }
        });
    }



    public void ChiTiet(View v, Flower fl){
        Integer index = (Integer) v.getTag() ;
        fl = data.get(index);
        AlertDialog.Builder builder =  new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialog = inflater.inflate(R.layout.datail_flower,null);
        builder.setView(dialog);
        TextView txtname = (TextView)dialog.findViewById(R.id.textView_name_detail_flower);
        TextView txtloai = (TextView)dialog.findViewById(R.id.textView_loai_detail_flower);
        TextView txtGia = (TextView)dialog.findViewById(R.id.textView_gia_detail_flower);
        TextView txtTinhtrang = (TextView)dialog.findViewById(R.id.textView_status_detail_flower);
        TextView txtCount = (TextView)dialog.findViewById(R.id.textView_count_detail_flower);
        TextView txtMota = (TextView)dialog.findViewById(R.id.textView_mota_detail_flower);
        ImageView img = (ImageView)dialog.findViewById(R.id.Image_detail_flower);
        txtname.setText(fl.getTenHoa());
        txtloai.setText(fl.getLoai());
        txtGia.setText(fl.getGia());
        txtTinhtrang.setText(fl.getStatus());
        txtCount.setText(String.valueOf(fl.getSoluong()));
        txtMota.setText(fl.getMoTa());
        Funtion f = new Funtion();
        img.setImageResource(f.getMipmapResIdByName(fl.getHinhAnh(),this));

        builder.setTitle(fl.getTenHoa());
        final AlertDialog b = builder.create();
        b.show();
    }

    public void Buy (View v)
    {
        Integer index;
        index = (Integer) v.getTag();
        Cart.AddToCart(fl);
        Toast.makeText(this,"da them vao gio hang",Toast.LENGTH_SHORT).show();
    }
    public void GoToCart (View v)
    {
        Intent intent = new Intent(getBaseContext(), CartActivity.class);
        startActivity(intent);
    }
    public void AddtoCart(View v){
        Integer index;
        index = (Integer) v.getTag();
        fl = data.get(index);
        Cart.AddToCart(fl);
        Toast.makeText(this,"da them vao gio hang",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        seacrh = newText;
        adapter.filter(seacrh);
        return false;
    }
}
