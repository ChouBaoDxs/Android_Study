package com.choubao.www.serviceactivitycommunication.byBroadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

import com.choubao.www.serviceactivitycommunication.R;

public class broadcast extends Activity {
    private ProgressBar mProgressBar;
    private Intent mIntent;
    private MsgReceiver msgReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        //动态注册广播接收器
        msgReceiver = new MsgReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.communication.RECEIVER");
        registerReceiver(msgReceiver, intentFilter);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar3);
        Button mButton = (Button) findViewById(R.id.button3);
        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动服务
                mIntent = new Intent(broadcast.this,MsgService3.class);
                startService(mIntent);
            }
        });

    }


    @Override
    protected void onDestroy() {
        //停止服务
        stopService(mIntent);
        //注销广播
        unregisterReceiver(msgReceiver);
        super.onDestroy();
    }


    /**
     * 广播接收器
     * @author len
     *
     */
    public class MsgReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            //拿到进度，更新UI
            int progress = intent.getIntExtra("progress", 0);
            mProgressBar.setProgress(progress);
        }

    }

}