package com.choubao.www.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

public class SharedPreferencesActivity extends AppCompatActivity {

    private SharedPreferences sp;  //那个ViewPager引导页也是用这个来判断是否是第一次启动
    private EditText editText_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        editText_msg= (EditText) findViewById(R.id.editText_msg);

        //获取当前程序的SharedPreferences对象  参数：(文件名，模式)
        sp=getSharedPreferences("msg", Context.MODE_PRIVATE);
    }

    //在该onPause方法执行后,Activity就有可能被kill   所以在这里存储数据
    @Override
    protected void onPause() {
        super.onPause();

        String msg=editText_msg.getText().toString();
        if (TextUtils.isEmpty(msg)) {
            return;
        } else {
            SharedPreferences.Editor editor=sp.edit();
            //              (key,value)
            editor.putString("msg",msg);
            editor.commit();//提交
        }
    }

    //恢复数据
    @Override
    protected void onResume() {
        super.onResume();
        //                  (key,缺省值)
        String msg=sp.getString("msg","");
        editText_msg.setText(msg);
        //删除数据
        SharedPreferences.Editor editor=sp.edit();
        editor.remove("msg");//删除一个
        //editor.clear();//全部清空
        editor.commit();
    }
}
