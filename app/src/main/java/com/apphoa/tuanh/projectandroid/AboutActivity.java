package com.apphoa.tuanh.projectandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        Intent intent = this.getIntent();
        //face = Typeface.createFromAsset(getAssets(),"fonts/BEAUCESC.TTF");

        String value1 = intent.getStringExtra("value1");
        String value2 = intent.getStringExtra("value2");
        TextView tv1 = (TextView) findViewById(R.id.textView_about_us);
        TextView tv2 = (TextView) findViewById(R.id.textView_about_us_contact);
        tv1.setText(value1);
        tv2.setText(value2);
    }
    public void back(View view)
    {
        this.onBackPressed();
    }
}
