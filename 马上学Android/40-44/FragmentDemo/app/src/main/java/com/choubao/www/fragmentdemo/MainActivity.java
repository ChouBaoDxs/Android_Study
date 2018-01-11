package com.choubao.www.fragmentdemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.choubao.www.fragmentdemo.FragmentDemo.NewsActivity;
import com.choubao.www.fragmentdemo.OpenFileDemo.OpenFileActivity;
import com.choubao.www.fragmentdemo.SharedPreferencesDemo.SharedPreferencesActivity;

public class MainActivity extends Activity implements LeftFragment.onSendDataButtonClickListener{

    @Override
    public void onSendDataButtonClicked(String string) {
        RightFragment rightFragment= (RightFragment) getFragmentManager().findFragmentById(R.id.fg_right);
        if (rightFragment != null) {
            rightFragment.updataTextView(string);
        }else{
            //找不到就替换右边的Fragment
            RightFragment newRightFragment=new RightFragment();
            FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
            Bundle bundle=new Bundle();
            bundle.putString("data",string);
            newRightFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fl_right,newRightFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MainActivity","onCreate()");
        setContentView(R.layout.activity_main);
        Button btnChangeRightFragment= (Button) findViewById(R.id.btnChangeRightFragment);
        //点击按钮动态添加Fragment
        btnChangeRightFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnotherRightFragment anotherRightFragment=new AnotherRightFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fl_right,anotherRightFragment);
                fragmentTransaction.addToBackStack(null);//模拟栈
                fragmentTransaction.commit();
            }
        });

        //点击按钮去NewsActivity
        Button btnGoToNewsActivity=(Button)findViewById(R.id.btnGoToNewsActivity);
        btnGoToNewsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, NewsActivity.class);
                startActivity(intent);
            }
        });

        //点击按钮去SharedPreferenceActivity
        Button btnGoToSharedPreferenceActivity=(Button)findViewById(R.id.btnGoToSharedPreferenceActivity);
        btnGoToSharedPreferenceActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, SharedPreferencesActivity.class);
                startActivity(intent);
            }
        });

        //点击按钮去OpenFileActivity
        Button btnGoToOpenFileActivity=(Button)findViewById(R.id.btnGoToOpenFileActivity);
        btnGoToOpenFileActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, OpenFileActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity","onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity","onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity","onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity","onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity","onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity","onDestroy()");
    }

}
