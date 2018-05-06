package com.apphoa.tuanh.projectandroid.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.apphoa.tuanh.projectandroid.Model.Flower;
import com.apphoa.tuanh.projectandroid.R;

import java.util.List;

/**
 * Created by TuAnh on 4/30/2018.
 */

public class CustomListAdapter_FlowerManager extends BaseAdapter {
    private List<Flower> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter_FlowerManager(Context aContext, List<Flower> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        CustomListAdapter_FlowerManager.ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.flower_manager,parent,false);
            holder = new CustomListAdapter_FlowerManager.ViewHolder();
            holder.imageview = (ImageView) convertView.findViewById(R.id.imageView_flower_manager);
            holder.flowername = (TextView) convertView.findViewById(R.id.textView_flowername);
            holder.price = (TextView) convertView.findViewById(R.id.textView_flowerprice);
            holder.delete = (Button) convertView.findViewById(R.id.button_delete_flower_manager);
            holder.edit = (Button) convertView.findViewById(R.id.button_edit_flower_manager);
            convertView.setTag(holder);
        } else {
            holder = (CustomListAdapter_FlowerManager.ViewHolder) convertView.getTag();
        }
        holder.delete.setTag((Integer) position);
        holder.edit.setTag((Integer)position);
        Flower flower = this.listData.get(position);
        holder.flowername.setText(flower.getTenHoa());
        holder.price.setText("" + flower.getGia());

        int imageId = this.getMipmapResIdByName(flower.getHinhAnh());

        holder.imageview.setImageResource(imageId);

        return (convertView);
    }

    public int getMipmapResIdByName(String resName)
    {
        String pkgName = context.getPackageName();

        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }
    static class ViewHolder {
        ImageView imageview;
        TextView flowername;
        TextView price;
        Button delete;
        Button edit;
    }
}
