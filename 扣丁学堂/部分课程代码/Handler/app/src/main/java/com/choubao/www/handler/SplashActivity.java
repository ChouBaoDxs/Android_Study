package com.choubao.www.handler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/*
* 闪屏页的作用
* 1.欢迎界面
* 2.初始化工作
* */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
                //还可以加载很多东西 执行很多操作
            }
        },2000);
    }

    private void startMainActivity() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private Handler handler=new Handler();
/*
    private final MyHandler handler=new MyHandler(this);

    private static class MyHandler extends Handler {
        private WeakReference<SplashActivity> weakReference;
        public MyHandler(SplashActivity activity) {
            weakReference=new WeakReference<SplashActivity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }*/
}
