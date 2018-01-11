package com.choubao.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by choubao on 16/12/4.
 */

public class Service01 extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //执行耗时的后台操作
                //比如下载大文件...
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public class Bind extends Binder{
        public void FileDownload(){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //下载文件
                }
            });
        }
    }
}
