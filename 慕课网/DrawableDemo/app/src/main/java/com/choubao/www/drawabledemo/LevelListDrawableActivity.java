package com.choubao.www.drawabledemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class LevelListDrawableActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_list_drawable);
        iv= (ImageView) findViewById(R.id.iv);
        iv.setImageLevel(8);
    }

    public void click8(View view) {
        iv.setImageLevel(8);
    }

    public void click15(View view) {
        iv.setImageLevel(15);
    }
}
