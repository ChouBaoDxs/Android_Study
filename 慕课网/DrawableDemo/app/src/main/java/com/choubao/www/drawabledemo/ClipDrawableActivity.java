package com.choubao.www.drawabledemo;

import android.graphics.drawable.ClipDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ClipDrawableActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_drawable);
        iv= (ImageView) findViewById(R.id.iv);
        ClipDrawable drawable= (ClipDrawable) iv.getDrawable();
        drawable.setLevel(5000);//范围：0~10000  这里5000裁剪一半
    }
}
