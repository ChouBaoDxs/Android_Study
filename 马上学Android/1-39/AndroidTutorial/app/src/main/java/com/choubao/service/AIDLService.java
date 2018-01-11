package com.choubao.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.choubao.androidtutorial.IMyService;

public class AIDLService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return new MyServiceInl();
    }
    public class MyServiceInl extends IMyService.Stub{

        @Override
        public String getValue() throws RemoteException {
            return "I am from Remote Service!";
        }
    }
}

