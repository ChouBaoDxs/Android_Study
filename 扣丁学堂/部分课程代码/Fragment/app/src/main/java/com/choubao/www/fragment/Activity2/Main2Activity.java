package com.choubao.www.fragment.Activity2;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.choubao.www.fragment.Activity.ContentFragment;
import com.choubao.www.fragment.R;

public class Main2Activity extends AppCompatActivity {

    ContentFragment contentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addContentLayou();
    }

    //通过代码添加Fragment
    private void addContentLayou() {
        android.app.FragmentManager fm=getFragmentManager();
        //开启一个事务
        FragmentTransaction ft=fm.beginTransaction();
        contentFragment=new ContentFragment();
        //添加fragment
        ft.add(R.id.content_layout,contentFragment);
//        ft.remove();
//        ft.replace();
        ft.commit();//提交
    }
}
