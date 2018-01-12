package com.choubao.www.servicetest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

//Android Service完全解析，关于服务你所需知道的一切
//http://www.jianshu.com/p/0fa614784110

public class MainActivity extends Activity implements View.OnClickListener {

    private Button startService;

    private Button stopService;

    private Button bindService;

    private Button unbindService;

    private Button startQiantaiService;
    private Button stopQiantaiService;

    private MyService.MyBinder myBinder;

    /*
    这里我们首先创建了一个ServiceConnection的匿名类，在里面重写了onServiceConnected()方法
    和onServiceDisconnected()方法，这两个方法分别会在Activity与Service建立关联和解除关联的
    时候调用。在onServiceConnected()方法中，我们又通过向下转型得到了MyBinder的实例，有了这个
    实例，Activity和Service之间的关系就变得非常紧密了。现在我们可以在Activity中根据具体的场景
    来调用MyBinder中的任何public方法，即实现了Activity指挥Service干什么Service就去干什么的
    功能。
    */
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder) service;
            myBinder.startDownload();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService = (Button) findViewById(R.id.start_service);
        stopService = (Button) findViewById(R.id.stop_service);
        bindService = (Button) findViewById(R.id.bind_service);
        unbindService = (Button) findViewById(R.id.unbind_service);
        startQiantaiService= (Button) findViewById(R.id.start_qiantai_service);
        stopQiantaiService= (Button) findViewById(R.id.stop_qiantai_service);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        startQiantaiService.setOnClickListener(this);
        stopQiantaiService.setOnClickListener(this);

        Log.d("MyService", "MainActivity thread id is " + Thread.currentThread().getId());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
                Log.d("MyService", "click Stop Service button");
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.bind_service:
                /*
                bindService()方法将Activity和Service进行绑定。bindService()方法接收
                三个参数，第一个参数就是刚刚构建出的Intent对象，第二个参数是前面创建出的
                ServiceConnection的实例，第三个参数是一个标志位，这里传入BIND_AUTO_CREATE
                表示在Activity和Service建立关联后自动创建Service，这会使得MyService中的
                onCreate()方法得到执行，但onStartCommand()方法不会执行。
                */
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                Log.d("MyService", "click Unbind Service button");
                unbindService(connection);
                break;

//            http://www.jianshu.com/p/5505390503fa
            case R.id.start_qiantai_service:
                Intent startQiantaiIntent = new Intent(this, Qiantai_Service.class);
                startService(startQiantaiIntent);
                break;
            case R.id.stop_qiantai_service:
                Intent stopQiantaiIntent = new Intent(this, Qiantai_Service.class);
                stopService(stopQiantaiIntent);
                break;
            default:
                break;
        }
    }

}


