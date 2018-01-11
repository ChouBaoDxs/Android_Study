package com.example.choubao.myclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by choubao on 16/11/20.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("闹钟执行了");
        AlarmManager am=(AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.cancel(PendingIntent.getBroadcast(context,getResultCode(),new Intent(context,AlarmReceiver.class),0));//取消掉当前所执行的闹钟

        //System.out.println(">>>");
        Intent i=new Intent(context,PlayAlarmAty1.class);
        //System.out.println(">>>");
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //System.out.println("<<<");
        context.startActivity(i);//////////////////////////
        //System.out.println(">>>");////////////////////////
    }
}
