package com.example.choubao.androidtutorial;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ThreadTutorialActivity extends Activity implements View.OnClickListener,Runnable {
    //声明UI的控件变量
    private ProgressDialog progressDialog;
    private Button btnTestANR,btnThread01,btnThread02,btnThread03;

    //声明按钮监听器
    //private ButtonListener listener;

    //自定义消息变量
    private static final int STOP=1;

    //定义Handler对象
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            //处理消息
            if (msg.what==STOP) {
                progressDialog.dismiss();
                Toast.makeText(ThreadTutorialActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_tutorial);

        btnTestANR= (Button) findViewById(R.id.btnTestANR);
        btnThread01= (Button) findViewById(R.id.btnThread01);
        btnThread02= (Button) findViewById(R.id.btnThread02);
        btnThread03= (Button) findViewById(R.id.btnThread03);

        btnTestANR.setOnClickListener(this);
        btnThread01.setOnClickListener(this);
        btnThread02.setOnClickListener(this);
        btnThread03.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnTestANR:
                //阻塞主线程10秒，出现ANR
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnThread01:
                //线程使用方式一：
                //显示加载对话框
                progressDialog=ProgressDialog.show(ThreadTutorialActivity.this,"提示","创建线程方式一加载中...",true,false);
                //创建自定义线程
                LoadingThread loadingThread=new LoadingThread();
                //启动线程
                loadingThread.start();
                break;
            case R.id.btnThread02:
                //线程使用方式二：
                //显示加载对话框
                progressDialog=ProgressDialog.show(ThreadTutorialActivity.this,"提示","创建线程方式二加载中...",true,false);
                Thread thread=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(6000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Message msg=new Message();
                        msg.what=STOP;
                        handler.sendMessage(msg);
                    }
                });
                //开启线程
                thread.start();
                break;
            case R.id.btnThread03:
                //线程使用方式三：
                //显示加载对话框
                progressDialog=ProgressDialog.show(ThreadTutorialActivity.this,"提示","创建线程方式三加载中...",true,false);
                //创建线程对象
                Thread t=new Thread(ThreadTutorialActivity.this);
                //启动线程
                t.start();
                break;
        }
    }

    //方式一
    //创建类继承Thread并重写run()方法
    private class LoadingThread extends Thread{
        @Override
        public void run() {
            //super.run();
            //执行耗时操作
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //创建消息对象
            Message msg=new Message();
            //设置消息常量
            msg.what=STOP;
            //发送消息
            handler.sendMessage(msg);
        }
    }

    //ThreadTutorialActivity类实现Runable接口，需要重写run()方法
    //方式三
    @Override
    public void run() {
        //执行耗时操作
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //创建消息对象
        Message msg=new Message();
        //设置消息常量
        msg.what=STOP;
        //发送消息
        handler.sendMessage(msg);
    }
}
