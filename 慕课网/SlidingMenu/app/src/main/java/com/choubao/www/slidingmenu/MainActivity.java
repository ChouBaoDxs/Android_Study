package com.choubao.www.slidingmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    private SlidingMenu slidingLeftMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去标题栏
        getSupportActionBar().hide();//去导航栏
        setContentView(R.layout.activity_main);
        slidingLeftMenu= (SlidingMenu) findViewById(R.id.slidingMenu);
    }

    public void toggleMenu(View view) {
        slidingLeftMenu.toggle();
    }

    public void startChouTi(View view) {
        startActivity(new Intent(MainActivity.this,ChouTiMenuActivity.class));
    }
}
