package com.choubao.www.drawabledemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.choubao.www.drawabledemo.CustomDrawable.CircleDrawable;
import com.choubao.www.drawabledemo.CustomDrawable.RoundDrawable;

public class CustomDrawableActivity extends AppCompatActivity {

    private ImageView iv;
    private ImageView iv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_drawable);
        iv= (ImageView) findViewById(R.id.iv);
        iv2= (ImageView) findViewById(R.id.iv2);

        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.background);
        iv.setImageDrawable(new CircleDrawable(bitmap));

        bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.background);
        iv2.setImageDrawable(new RoundDrawable(bitmap,100));
    }
}
