package com.choubao.www.serviceactivitycommunication.byBind;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

import com.choubao.www.serviceactivitycommunication.R;

public class bind extends Activity {
    private MsgService msgService;
    private int progress = 0;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind);


        //绑定Service
        Intent intent = new Intent(this,MsgService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);


        mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
        Button mButton = (Button) findViewById(R.id.button1);
        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始下载
                msgService.startDownLoad();
                //监听进度
                listenProgress();
            }
        });

    }


    /**
     * 监听进度，每秒钟获取调用MsgService的getProgress()方法来获取进度，更新UI
     */
    public void listenProgress(){
        new Thread(new Runnable() {

            @Override
            public void run() {
                while(progress < MsgService.MAX_PROGRESS){
                    progress = msgService.getProgress();
                    mProgressBar.setProgress(progress);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //返回一个MsgService对象
            msgService = ((MsgService.MsgBinder)service).getService();

        }
    };

    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }


}
