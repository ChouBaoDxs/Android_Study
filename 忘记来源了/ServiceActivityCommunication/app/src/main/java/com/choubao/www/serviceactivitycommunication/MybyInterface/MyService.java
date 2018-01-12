package com.choubao.www.serviceactivitycommunication.MybyInterface;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;

import com.choubao.www.serviceactivitycommunication.MainActivity;
import com.choubao.www.serviceactivitycommunication.R;

public class MyService extends Service {
    /**
     * 进度条的最大值
     */
    public static final int MAX_PROGRESS = 100;
    /**
     * 进度条的进度值
     */
    private int progress = 0;

    /**
     * 更新进度的回调接口
     */
    private OnMyListener onMyListener;


    /**
     * 注册回调接口的方法，供外部调用
     * @param onMyListener
     */
    public void setOnMyListener(OnMyListener onMyListener) {
        this.onMyListener = onMyListener;
    }

    /**
     * 增加get()方法，供Activity调用
     * @return 下载进度
     */
    public int getProgress() {
        return progress;
    }

    /**
     * 模拟下载任务，每秒钟更新一次
     */
    public void startDownLoad(){
        new Thread(new Runnable() {

            @Override
            public void run() {
                while(progress < MAX_PROGRESS){
                    progress += 5;

                    //进度发生变化通知调用方法
                    if(onMyListener != null){
                        onMyListener.onProgress(progress);
                    }

                    Update_Notification("当前进度",""+progress);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }


    /**
     * 返回一个Binder对象
     */
    @Override
    public IBinder onBind(Intent intent) {
        return new MyService.MsgBinder();
    }

    public class MsgBinder extends Binder {
        /**
         * 获取当前Service的实例
         * @return
         */
        public MyService getService(){
            return MyService.this;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //停止前台服务
        //在Service的onDestory中使用stopForeground方法来停止正在运行的前台服务。
        stopForeground(true);// 停止前台服务--参数：表示是否移除之前的通知
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
}
