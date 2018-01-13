package com.choubao.www.handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/*
*handler的内存泄漏问题
* 1.定义一个内部类时，会默认拥有外部类对象的引用，所以建议使用内部类时，最好定义为一个静态内部类  private static Handler handler=new Handler()
* 2.引用的强弱：强引用->软引用->弱引用
*
**/
public class HandlerMemoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_memory);
        //使用handler延迟执行一个Runnable(10分组)
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },1000*60*10);
        finish();//关闭当前的Activity
    }

/*    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };*/

    private MyHandler handler=new MyHandler(this);

    private TextView tv;
    //标准的handler的写法  确保TextView tv存在
    private static class MyHandler extends Handler{
        WeakReference<HandlerMemoryActivity> weakReference;
        public MyHandler(HandlerMemoryActivity activity) {
            weakReference=new WeakReference<HandlerMemoryActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HandlerMemoryActivity activity=weakReference.get();
            if (activity != null) {
                //tv.s
            }
        }
    }
}
