package com.apphoa.tuanh.projectandroid.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apphoa.tuanh.projectandroid.Model.Flower;
import com.apphoa.tuanh.projectandroid.Model.Funtion;
import com.apphoa.tuanh.projectandroid.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by TuAnh on 4/29/2018.
 */

public class Search_Adapter extends ArrayAdapter {
    private List<Flower> listData;
    private int resource;
    private Context context;
    private  List<Flower> listSeach = null;

    public int getResource() {
        return resource;
    }

    public List<Flower> getListData() {
        return listData;
    }

    public Search_Adapter(@NonNull Context context, int resource, @NonNull List<Flower> objects) {
        super(context, resource, objects);
        this.listSeach = objects;
        this.resource = resource;
        this.context = context;
        this.listData = new ArrayList<Flower>();
        this.listData.addAll(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layinf = LayoutInflater.from(context);
        convertView = layinf.inflate(R.layout.search_item, parent, false);
        ImageView img = (ImageView) convertView.findViewById(R.id.img_searchItem);
        TextView txtTen = (TextView)convertView.findViewById(R.id.textView_ten_searchItem);
        TextView txtTinhTrang = (TextView)convertView.findViewById(R.id.textView_tinhtrang_searchItem);
        convertView.setTag((Integer)position);
        Flower  fl = listSeach.get(position);
        Funtion f= new Funtion();
        img.setImageResource(f.getMipmapResIdByName(fl.getHinhAnh(),context));
        txtTen.setText(fl.getTenHoa());
        txtTinhTrang.setText(fl.getStatus());
        return convertView;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return super.getPosition(item);
    }
    public  void  filter(String searh){
        searh = searh.toLowerCase(Locale.getDefault());
        listSeach.clear();// đầu tiên xóa list chứa kq
        if(searh.length()==0)
            listSeach.addAll(listData);//nếu như không tìm gì thì nó hiện tất cả các hoa lên
        else {
            for (Flower flower: listData){ //tìm trong list data, nếu như có flower thảo yêu cầu của chuỗi search thì thêm cái
                if(flower.getTenHoa().toLowerCase(Locale.getDefault()).contains(searh)){//flower đó vô listSearch
                    listSeach.add(flower);
                }
            }
        }
        notifyDataSetChanged();
    }
}
