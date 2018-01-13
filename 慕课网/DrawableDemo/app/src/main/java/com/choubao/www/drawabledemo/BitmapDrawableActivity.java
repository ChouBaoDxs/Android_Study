package com.choubao.www.drawabledemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class BitmapDrawableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_drawable);
        Log.i("BitmapDrawableActivity",findViewById(R.id.activity_bitmap_drawable).getBackground().toString());
    }
}
