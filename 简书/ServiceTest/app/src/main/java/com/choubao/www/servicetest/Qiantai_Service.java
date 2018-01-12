package com.choubao.www.servicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class Qiantai_Service extends Service {
    public Qiantai_Service() {
    }

    public static final String TAG = "Qiantai_Service";

    class MyBinder extends Binder {
        public void startDownload() {
            Log.d("TAG", "startDownload() executed");
            // 执行具体的下载任务
        }
    }
    private MyBinder mBinder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();

        //原文创建的通知，但是22版本以下的才行
//        Notification notification = new Notification(R.drawable.ic_launcher,
//                "有通知到来", System.currentTimeMillis());
//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
//                notificationIntent, 0);
//        notification.setLatestEventInfo(this, "这是通知的标题", "这是通知的内容",
//                pendingIntent);
//        startForeground(1, notification);

        Log.d(TAG, "onCreate() executed");
        Log.d("MyService", "MyService thread id is " + Thread.currentThread().getId());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() executed");

        Update_Notification("标题","内容");

        return super.onStartCommand(intent, flags, startId);
    }


    private void Update_Notification(String title, String content) {
        //新的创建前台服务的方式   http://www.jianshu.com/p/5505390503fa
        Notification.Builder builder = new Notification.Builder(this.getApplicationContext()); //获取一个Notification构造器
        Intent nfIntent = new Intent(this, MainActivity.class);
        builder.setContentIntent(PendingIntent
                .getActivity(this, 0, nfIntent, 0)) // 设置PendingIntent
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(),
                        R.mipmap.ic_launcher)) // 设置下拉列表中的图标(大图标)
                .setContentTitle(title) // 设置下拉列表里的标题
                .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
                .setContentText(content) // 设置上下文内容
                .setWhen(System.currentTimeMillis()); // 设置该通知发生的时间

        Notification notification = builder.build(); // 获取构建好的Notification
        notification.defaults = Notification.DEFAULT_SOUND; //设置为默认的声音

        //开启前台服务
        //在完成Notification通知消息的构建后，在Service的onStartCommand中可以使用
        //startForeground方法来让Android服务运行在前台。
        // 参数一：唯一的通知标识；参数二：通知消息。
        startForeground(110, notification);// 开始前台服务
        //注：当使用的通知ID一致时，只会更新当前Notification。
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() executed");
        //停止前台服务
        //在Service的onDestory中使用stopForeground方法来停止正在运行的前台服务。
        stopForeground(true);// 停止前台服务--参数：表示是否移除之前的通知

//        //销毁时重建Service        //无法用户点击清理内存
//        Intent i=new Intent();
//        i.setClass(this,Qiantai_Service.class);
//        this.startService(i);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }



}
