package com.choubao.www.serviceactivitycommunication.byBroadcast;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MsgService3 extends Service {
    /**
     * 进度条的最大值
     */
    public static final int MAX_PROGRESS = 100;
    /**
     * 进度条的进度值
     */
    private int progress = 0;

    private Intent intent = new Intent("com.example.communication.RECEIVER");

    /**
     * 模拟下载任务，每秒钟更新一次
     */
    public void startDownLoad(){
        new Thread(new Runnable() {

            @Override
            public void run() {
                while(progress < MAX_PROGRESS){
                    progress += 5;
                    //发送Action为com.example.communication.RECEIVER的广播
                    intent.putExtra("progress", progress);
                    sendBroadcast(intent);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startDownLoad();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}