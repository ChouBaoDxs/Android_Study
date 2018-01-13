package com.choubao.www.canvas_drawable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //setContentView(new MyView(this));
        //setContentView(new MyImageView(this));
        setContentView(new MySurfaceView(this));
    }
}
