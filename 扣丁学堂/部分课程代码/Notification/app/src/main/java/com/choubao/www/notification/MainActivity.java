package com.choubao.www.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    public static final int NID_1=0x1;
    private static final int NID_2 =0x2;
    private static final int NID_3 =0x3;
    private static final int NID_4 =0x4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //发送一个普通的通知
    public void sendNotification1(View v) {
//        Notification n=new Notification();  //API 11之前的方式

//        Notification.Builder builder=new Notification.Builder(this);    //API 11之后的方式

        //v4支持包
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        //设置相关的属性
        builder.setSmallIcon(R.mipmap.ic_launcher);//设置小图标
        builder.setContentTitle("你有一条新消息");//设置标题
        builder.setContentText("阿姨洗铁路");//设置内容
//        builder.setOngoing(true);//设置为常驻通知（清除不掉）

        //builder.setAutoCancel(true);//设置自动清除 点击完后消失 也可以在Activity2去掉

        builder.setDefaults(Notification.DEFAULT_LIGHTS);//设置呼吸灯
        builder.setDefaults(Notification.DEFAULT_SOUND);//设置响铃
        builder.setDefaults(Notification.DEFAULT_VIBRATE);//设置震动
        builder.setDefaults(Notification.DEFAULT_ALL);//设置以上三个
        builder.setNumber(10);
        builder.setTicker("新消息");//会在屏幕顶部显示文本

        //定义一个意图，当点击通知时要打开一个界面(Activity)
        Intent intent =new Intent(this,Main2Activity.class);
        intent.putExtra("msg","阿姨洗铁路");//传递消息给Activity2
        //参数：上下文，请求编码（现在没用），意图，创建PendingIntent的方式 常用的是：PendingIntent.FLAG_UPDATE_CURRENT
//        PendingIntent.FLAG_CANCEL_CURRENT:取消当前的pi，创建新的
//        PendingIntent.FLAG_NO_CREATE:如果有就使用，没有不创建
//        PendingIntent.FLAG_ONE_SHOT:只使用一次
//        PendingIntent.UPDATE_CURRENT：如果有，更新Intent,没有就创建

        PendingIntent pi=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        //设置通知的事件
        builder.setContentIntent(pi);

        //创建一个通知对象
        Notification n=builder.build();

        //获取系统的通知管理器，然后发送通知
        NotificationManager nm= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(NID_1,n);
    }

    //发送一个大视图通知
    public void sendNotification2(View v) {
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        //设置相关的属性
        builder.setSmallIcon(R.mipmap.ic_launcher);//设置小图标
        builder.setContentTitle("你有一条大消息");//设置标题
        builder.setContentText("简介");//设置内容

        //设置大视图样式
        NotificationCompat.InboxStyle style=new NotificationCompat.InboxStyle();
        style.setBigContentTitle("大标题");
        style.addLine("第一行内容");
        style.addLine("第二行内容");
        style.addLine("第三行内容");
        style.setSummaryText("设置提示文本");
        builder.setStyle(style);

        Notification n=builder.build();

        NotificationManager nm= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(NID_2,n);
    }

    //发送一个带进度条的通知
    public void sendNotification3(View v) {
        final NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        //设置相关的属性
        builder.setSmallIcon(R.mipmap.ic_launcher);//设置小图标
        builder.setContentTitle("切换中...");//设置标题
        builder.setContentText("正在从学渣模式切换成学霸模式...");//设置内容
        builder.setProgress(100,0,false);//false代表是确定的进度 true的话就是一直动

        final NotificationManager nm= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(NID_3,builder.build());

        //模拟更新进度条的线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int progress=0;progress<=100;progress+=5) {
                    builder.setProgress(100,progress,false);
                    nm.notify(NID_3,builder.build());

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                builder.setProgress(0,0,false);//移除进度条
                builder.setContentText("切换完成！");
                nm.notify(NID_3,builder.build());
            }
        }).start();
    }

    //发送一个自定义视图的通知  //不知道为什么，显示不出来
    public void sendNotification4(View v) {
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        //设置相关的属性
        builder.setSmallIcon(R.mipmap.ic_launcher);//设置小图标
//        builder.setContentTitle("你有一条新消息");//设置标题
//        builder.setContentText("阿姨洗铁路");//设置内容
        builder.setOngoing(true);//设置为常驻通知

        //创建一个远程的视图
        RemoteViews views=new RemoteViews(getPackageName(),R.layout.custom_layout);

        views.setTextViewText(R.id.textView_songName,"大鱼");
//        views.setImageViewResource();
        views.setTextViewText(R.id.button_play,"暂停");
//        views.setOnClickPendingIntent();//设置按钮的单击事件

        builder.setContent(views);
        builder.setTicker("臭包音乐播放器");
        //获取系统的通知管理器，然后发送通知
        NotificationManager nm= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(NID_4,builder.build());
    }
}
