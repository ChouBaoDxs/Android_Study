package com.example.choubao.learnbroadcastrecevicer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {
    public static final String ACTION="com.example.choubao.learnbroadcastrecevicer.intent.action.MyReceiver";
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("接收到了消息,消息的内容是:"+intent.getStringExtra("data"));
        //abortBroadcast();//中断广播,后续的接收器就不会再接收到消息
    }
}
