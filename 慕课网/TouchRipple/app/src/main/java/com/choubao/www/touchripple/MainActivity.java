package com.choubao.www.touchripple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
/*
* 1.实现一个自定义控件
* 2.实现一个Drawable
* 3.显示Drawable
*
* 1.弄清楚Drawable中实现的方法
* 2.添加一些涟漪所需的元素到Drawable
* 3.动态更新Drawable
*
* tips
* 1.首先确定点击位置，设置为启动时的圆心
* 2.圆心时逐渐移动的，结束位置Wie控件的中心区域
* 3.圆半径的最大值为控件中心到边缘的最大长度
* 4.背景颜色透明度有个逐渐加深的过程
*
* 退出动画：
* 1.基本实现退出动画，并设置好退出动画触发的时机
* 2.实现背景减淡的过程
* 3.实现圆形区域减淡的过程
* 4.实现多个半透明颜色快叠加的算法
* */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
