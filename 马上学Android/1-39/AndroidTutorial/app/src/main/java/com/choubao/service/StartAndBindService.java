package com.choubao.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by choubao on 16/12/4.
 */

public class StartAndBindService extends Service{
    private static final String TAG="StartAndBindService";
    private MyBinderClass mybinder=new MyBinderClass();

    @Override
    public void onCreate() {
        Log.i(TAG,"StartAndBindService->onCreat()");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"StartAndBindService->onDestroy()");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"StartAndBindService->onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"StartAndBindService->onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {

        return mybinder;
    }

    public class MyBinderClass extends Binder {
        public void Show(){
            Log.i(TAG,"StartAndBindService->Show()");
        }
    }
}
