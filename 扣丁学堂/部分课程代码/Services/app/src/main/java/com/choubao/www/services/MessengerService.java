package com.choubao.www.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public class MessengerService extends Service {
    public MessengerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    public static final int SAY_HELLO=0x1;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case SAY_HELLO:
                    String info= (String) msg.obj;
                    Toast.makeText(getApplicationContext(),info,Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private Messenger messenger=new Messenger(handler);
}
