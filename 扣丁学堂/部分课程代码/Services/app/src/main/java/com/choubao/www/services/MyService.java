package com.choubao.www.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


/*
* started service:
* 1.服务同时只会被创建一次，可以通过外部调用stopService或调用stopSelf来终止服务
* 2.当执行一个已启动的服务，会直接调用onStartCommand方法来执行业务
* 3.默认情况下，服务与主线程在同一个进程中的线程执行，如果服务执行一个比较耗时的操作，我们必须使用子线程来完成工作，避免主线程阻塞
* 4.使用started service启动的一个服务，在没有关闭之前会一直在后台运行
*
* */
public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("my service create");
    }

    //在该方法中实现服务的核心业务                                startId的意思是这个service被执行了几次，基本不用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        System.out.println(intent.getStringExtra("info"));

        //使用线程来执行耗时的工作
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<50;i++) {
                    System.out.println("onStartCommand-"+i+"-"+Thread.currentThread().getName());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //自己停止 终止服务
                    if (i==30) {
                        MyService.this.stopSelf();
                        break;
                    }
                }
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("my service destroy");
    }
}
