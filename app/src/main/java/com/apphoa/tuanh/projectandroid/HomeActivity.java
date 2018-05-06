package com.apphoa.tuanh.projectandroid;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.apphoa.tuanh.projectandroid.Database.FlowerAction;
import com.apphoa.tuanh.projectandroid.Database.MySqlHelper;
import com.apphoa.tuanh.projectandroid.Model.Flower;
import com.apphoa.tuanh.projectandroid.Model.Funtion;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class HomeActivity extends MainActivity {

    //--------------------------VIDEO-------------------
    private VideoView videoView;
    private int position = 0;
    private MediaController mediaController;

    public int getRawResIdByName(String resName) {
        String pkgName = this.getPackageName();

        int resID = this.getResources().getIdentifier(resName, "raw", pkgName);
        Log.i("AndroidVideoView", "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
    }
    //-------------------------------------------------------------------------
    DatabaseReference ref;
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) { // lưu lại vị trí play
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("CurrentPosition", videoView.getCurrentPosition());
        videoView.pause();
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        position = savedInstanceState.getInt("CurrentPosition");
        videoView.seekTo(position);
    }
    //----------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // them Flower len fire base

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        videoView = (VideoView) findViewById(R.id.videoView3);
        mediaController = new MediaController(HomeActivity.this);
        // thêm flower lên frebase
        /*ref = FirebaseDatabase.getInstance().getReference("Flower");
        Funtion f = new Funtion();
        List<Flower> temp = f.getListDired();
        for(Flower t: temp){
            String id = ref.push().getKey();
            t.setID(id);
            ref.child(id).setValue(t);
        }*/
        int id = this.getRawResIdByName("myvideo");
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + id));
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.requestFocus();
        videoView.start();
    }

    public void shoppingnow(View v){
        Intent shopping = new Intent(getBaseContext(),ShoppingActivity.class);
        startActivity(shopping);
    }
    public void store(View v){
        Intent store = new Intent(getBaseContext(),StoredSystemActivity.class);
        startActivity(store);
    }
    public void gallery(View v){
        Intent gallery = new Intent(getBaseContext(),HomeActivity.class);
        startActivity(gallery);
    }
    public void wedding (View v){
        Intent wedding = new Intent(getBaseContext(),HomeActivity.class);
        startActivity(wedding);
    }
    //Chỉ thoát app khi ờ home
    private static long back_pressed;
    @Override
    public void onBackPressed(){
        if (back_pressed + 2000 > System.currentTimeMillis()){
            //super.onBackPressed();
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
        else{
            Toast.makeText(getBaseContext(), "Press once again to exit", Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();
        }
    }
}
