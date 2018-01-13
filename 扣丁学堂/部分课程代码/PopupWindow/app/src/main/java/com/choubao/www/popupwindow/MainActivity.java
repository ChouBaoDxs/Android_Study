package com.choubao.www.popupwindow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //按钮事件方法
    public void showWindow(View v) {
        View view=getLayoutInflater().inflate(R.layout.popup_window_layout,null);
        //创建PopupWindow(窗体的视图,宽,高)
        PopupWindow popupWindow=new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        //设置背景
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.btn_default));
        //设置弹出样式 这里的是从左边飞进来
        popupWindow.setAnimationStyle(android.R.style.Animation_Translucent);
        //设置背景透明度
        popupWindow.getBackground().setAlpha(100);
        //设置点击菜单外部时退出菜单
        popupWindow.setOutsideTouchable(true);
        //获取当前窗口焦点
        popupWindow.setFocusable(true);
        //设置可以被触摸
        popupWindow.setTouchable(true);
        //设置软键盘弹出时模式
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        //
        popupWindow.showAtLocation(v, Gravity.BOTTOM,0,0);

        //获取屏幕尺寸
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height=dm.heightPixels;
        int width=dm.widthPixels;
    }
}
