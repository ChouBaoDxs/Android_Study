package com.choubao.www.intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/*
* Activity的四种启动模式
* standrad:默认的模式，以这种模式加载必定会构造一个新的Activity实例放到目标task中的Activity栈顶，不管当前task的栈顶是什么情况。
* singleTop:这种模式和默认模式类似，区别在于会判断新Activity与当前task栈顶的Activity是不是同一个，相同的话就不构造新的Activity，并调用这个Activity的newInstance()方法
* singleTask:会创建一个新的task来加载这个activity，并且这个task种只存在一个Activity的一个实例(以后可以加载其他Activity实例)
* singleInstance:会创建一个新的task，并且这个task中只能存在一个需要加载的这个Activity实例，即除了这个activity之外，不允许其他的Activity，如果已存在该Activity，会切换到该任务栈
*
* 可以
* */

public class MainAActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_a);
    }

    public void startA(View v) {
        Intent intent=new Intent(this,MainAActivity.class);
        startActivity(intent);
    }

    public void startB(View v) {
        Intent intent=new Intent(this,MainBActivity.class);
        startActivity(intent);
    }
}
