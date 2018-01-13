package com.choubao.www.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //间接法（通过data，一般与action配合使用，此组合是最常见的使用方式）
    //type属性，表示数据的类型
    public void dataClick(View v) {
        Intent intent=new Intent();
        //intent.setAction("com.choubao.action.MY_ACTION");
        intent.setAction(Intent.ACTION_VIEW);
        Uri data=Uri.parse("http://www.baidu.com");
 //       intent.setData(data);//会把type属性设置为空
//        intent.setType();//会把data属性设置为空
        //要使用data和type，必须同时设置，匹配时必须两者同时匹配才可以
        intent.setDataAndType(data,"text/html");
        startActivity(intent);
    }

    //间接法（通过action动作和category类别）
    //清单文件中必须要加<category android:name="android.intent.category.DEFAULT"></category>
    public void actionClick(View v) {
        //Intent intent=new Intent("com.choubao.action.MY_ACTION");
        Intent intent=new Intent();
        intent.setAction("com.choubao.action.MY_ACTION");
        //Category:类别
        intent.addCategory("com.choubao.action.MY_CATEGROY");
        startActivity(intent);
    }

    //直接查找法（通过组件名称）
    public void componentClick(View v) {
/*        Intent intent=new Intent();
        ComponentName componentName=new ComponentName(this,Main2Activity.class);
        intent.setComponent(componentName);*/

        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);
    }

    public void Activity_launchMode(View v) {
        Intent intent=new Intent(this,MainAActivity.class);
        startActivity(intent);
    }

    public void flagClick(View v) {
        Intent intent =new Intent(this,Main5Activity.class);
        //设置Activity的启动模式
        //Intent.FLAG_ACTIVITY_NEW_TASK     在新的任务栈中启动Acivity  如果有，那么就在本任务中启动    这个用的比较多
        //Intent.FLAG_ACTIVITY_CLEAR_TASK    相当于singleTask
        //Intent.FLAG_ACTIVITY_CLEAR_TOP        相当于singleTop
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void intentExample(View v) {
        Intent intent=new Intent(this,Main6Activity.class);
        startActivity(intent);
    }

}
