package com.choubao.www.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


//不要阻塞线程
//不要在主线程之外的子线程访问UI工具类
/*
* handler机制
* 1.Message消息对象，内部使用链表数据结构实现一个消息池，用于重复利用，避免大量创建消息对象，造成内存浪费
* 2.Handler消息处理者，通过该对象把消息存入消息队列，最后通过handlerMannager方法处理消息
* 3.MessageQueue消息队列，用于存储Message对象的数据结构，先进先出
* 4.Looper消息队列的处理者，用于循环检查检查消息队列，从消息队列中一个一个地取出消息对象，传入handlerMessage方法
* */
public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView) findViewById(R.id.textView);
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 100:
                    textView.setText("下载完成");
                    break;
            }
        }
    };

    public void downloadClick(View v) {
        //使用线程模拟下载操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                //下载完成后，更新UI状态
                //textView.setText("下载完成");  //在子线程里操作UI会报错
                handler.sendEmptyMessage(100);//发送一个空消息 标记为100 把100存到了what里

//                Message m=new Message();//差不多  下面的比较好
                Message msg=handler.obtainMessage();//获取一个消息对象
                msg.what=100;
                //msg.obj=;//这个可以存任何数据
                handler.sendMessage(msg);//发送消息
                handler.sendEmptyMessageAtTime(110,System.currentTimeMillis()+3000);//在指定时间后发送消息 这里是3秒后再发送
                handler.sendEmptyMessageDelayed(110,2000);//延迟2秒发送
//                handler.sendMessageAtTime();
//                handler.sendMessageDelayed();
            }
        }).start();
    }
}
