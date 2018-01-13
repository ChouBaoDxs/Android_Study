package com.choubao.www.imageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView= (ImageView) findViewById(R.id.imageView);

        //imageView.setImageResource(R.drawable.weixin);
        //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }
}
