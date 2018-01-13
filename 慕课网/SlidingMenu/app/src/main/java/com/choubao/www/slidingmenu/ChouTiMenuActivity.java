package com.choubao.www.slidingmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class ChouTiMenuActivity extends AppCompatActivity {

    private SlidingMenu_ChouTi activity_chou_ti_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去标题栏
        getSupportActionBar().hide();//去导航栏
        setContentView(R.layout.activity_chou_ti_menu);
        activity_chou_ti_menu= (SlidingMenu_ChouTi) findViewById(R.id.slidingMenu_ChouTi);
    }

    public void toggleMenu(View view) {
        activity_chou_ti_menu.toggle();
    }
}
