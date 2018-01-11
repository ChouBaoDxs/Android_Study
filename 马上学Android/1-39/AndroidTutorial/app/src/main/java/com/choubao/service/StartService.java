package com.choubao.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.choubao.androidtutorial.R;

public class StartService extends Service {

    private static final String TAG="StartService";

    //通知栏管理器
    private NotificationManager notificationManager;
    private int NOTIFY_MUN=100;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"StartService->onCreat()"+Thread.currentThread().getId());
        notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder nb=new Notification.Builder(this);
        nb.setContentTitle("提示");
        nb.setContentText("这是来自服务的问候！");
        nb.setSmallIcon(R.drawable.she);
        notificationManager.notify(NOTIFY_MUN,nb.getNotification());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.w(TAG,"StartService->onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.w(TAG,"StartService->onDestroy()");
        notificationManager.cancelAll();
    }

    @Override
    public IBinder onBind(Intent intent) {
       return new ForeBinder();
    }

    public class ForeBinder extends Binder {
        public StartService getService(){
            return StartService.this;
        }
    }
}
