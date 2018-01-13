package com.choubao.www.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //启动一个服务
    public void startClick(View v) {
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("info", "法律真是有趣");
        startService(intent);
    }

    //停止一个服务
    public void stopClick(View v) {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    //启动一个intent服务
    public void startIntentServiceClick(View v) {
        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("info", "法定符合说 具体符合说");
        startService(intent);
    }


    private ICat cat;
    private boolean mBound = false;//是否绑定

    //绑定服务的连接回调接口
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //绑定成功后回调的方法
            cat = ICat.Stub.asInterface(service);
            System.out.println(cat);
            mBound = true;
            Toast.makeText(MainActivity.this, "绑定成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //服务异常时调用
            mBound = false;
        }
    };

    public void callClick(View v) {
        if (cat == null) {
            return;
        } else {
            try {
                cat.setName("喵喵");
//                System.out.println(cat.desc());
                Toast.makeText(MainActivity.this, cat.desc(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, cat.getPerson().toString(), Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    //绑定一个服务
    public void boundClick(View v) {
        Intent intent = new Intent(this, MyBoundService.class);
        //异步绑定,绑定成功后会回调onServiceConnected
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    //解除绑定服务
    public void unBoundClick(View v) {
        if (mBound == true) {
            unbindService(conn);
            Toast.makeText(MainActivity.this, "解除绑定成功", Toast.LENGTH_SHORT).show();
        }
    }

    //*******************************************************************************************************
    Messenger mService;
    boolean flag = false;

    private ServiceConnection conn2 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);
            flag = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            flag = false;
            mService = null;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent service = new Intent(this, MessengerService.class);
        bindService(service, conn2, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (flag = true) {
            unbindService(conn2);
            flag=false;
        }
    }

    //使用Messenger
    public void messengerClick(View v) {
        //获取(创建)一个消息对象
        Message msg=Message.obtain();
        msg.what=MessengerService.SAY_HELLO;
        msg.obj="诽谤侮辱侵人权，暴力干涉加侵占";
        try {
            mService.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
