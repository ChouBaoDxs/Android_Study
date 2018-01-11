package com.choubao.www.alphastausbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //下面这样做可以在全屏的情况下显示状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView=getWindow().getDecorView();
            int option=View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            int option=View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;//把屏幕下方的导航栏也藏起来
//            int option=View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_STABLE;//把屏幕下方的导航栏也变成透明
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);//设置状态栏背景为透明
//            getWindow().setNavigationBarColor(Color.TRANSPARENT);//设置导航栏背景为透明
        }
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.hide();


        //下面是直接设置全屏
//        View decorView=getWindow().getDecorView();  //获取当前界面的DecorView
//        int option=View.SYSTEM_UI_FLAG_FULLSCREEN;  //全屏
//        decorView.setSystemUiVisibility(option);
//        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
//        actionBar.hide();
    }

    public void startOtherActivity(View view) {
        startActivity(new Intent(MainActivity.this,OtherActivity.class));
    }
}
