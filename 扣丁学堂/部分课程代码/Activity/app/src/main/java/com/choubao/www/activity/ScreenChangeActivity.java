package com.choubao.www.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

/**
 * 屏幕切换时，默认情况下会重新创建Activity
 * 为了保存当前Activity的状态，我们可以重写onSaveInstanceState来保存相关的数据
 * 然后在onCreate方法中还原数据
 *
 */
public class ScreenChangeActivity extends Activity {

    int index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_change);
        System.out.println("ScreenChangeActivity-onCreate");

        //还原状态值index
        if (savedInstanceState != null) {
            index=savedInstanceState.getInt("index",0);
        }
    }

    public void indexAdd(View v) {
        index++;
        System.out.println("index="+index);
    }

    //横竖屏切换时会调用这个函数
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("onSaveInstanceState");
        //保存状态值index
        outState.putInt("index",index);
    }

    //在清单文件中设置android:configChanges="keyboard|screenSize" 可以防止重新创建，也可以不需要onSaveInstanceState了
    //这里面也可以重新载入布局
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        System.out.println("onConfigurationChanged");
    }
}
