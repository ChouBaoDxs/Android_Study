package com.choubao.broadcast;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by choubao on 16/12/2.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    private static final String CALLACTION=Intent.ACTION_NEW_OUTGOING_CALL;

    @Override
    public void onReceive(Context context, Intent intent) {
//        String strMsg=intent.getExtras().getString("msg");
//        Toast.makeText(context, "接受到的广播消息是:"+strMsg, Toast.LENGTH_SHORT).show();
        if (intent.getAction().equals(CALLACTION)) {
            //调出拨号界面了
            String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            Log.i("broadcast", "有人在打电话:" + phoneNumber);
        } else {
            //有电话呼入
            Log.i("broadcast","有电话呼入");
            TelephonyManager telephonyManager=(TelephonyManager)context.getSystemService(Service.TELEPHONY_SERVICE);
            telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }

    PhoneStateListener phoneStateListener = new PhoneStateListener(){
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch(state){
                case TelephonyManager.CALL_STATE_IDLE:
                    Log.i("broadcast","挂断");
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Log.i("broadcast","接听");
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    Log.i("broadcast","有电话呼入，来电号码:"+incomingNumber);
                    break;
            }
        }
    };
}
