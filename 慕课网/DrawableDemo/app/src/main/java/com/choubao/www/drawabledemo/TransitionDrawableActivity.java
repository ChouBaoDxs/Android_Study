package com.choubao.www.drawabledemo;

import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TransitionDrawableActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_drawable);
        iv= (ImageView) findViewById(R.id.iv);
    }

    public void click1(View view) {
        TransitionDrawable drawable= (TransitionDrawable) iv.getDrawable();
        drawable.startTransition(3000);//3秒
    }

    public void click2(View view) {
        TransitionDrawable drawable= (TransitionDrawable) iv.getDrawable();
        drawable.reverseTransition(3000);//3秒
    }
}
