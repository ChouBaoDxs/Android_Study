package com.choubao.www.serviceactivitycommunication.byInterface;

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

public class interFace extends Activity {
    private MsgService2 msgService;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_face);


        //绑定Service
        Intent intent = new Intent(this,MsgService2.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);


        mProgressBar = (ProgressBar) findViewById(R.id.progressBar2);
        Button mButton = (Button) findViewById(R.id.button2);
        mButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //开始下载
                msgService.startDownLoad();
            }
        });

    }


    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //返回一个MsgService对象
            msgService = ((MsgService2.MsgBinder)service).getService();

            //注册回调接口来接收下载进度的变化
            msgService.setOnProgressListener(new OnProgressListener() {

                @Override
                public void onProgress(int progress) {
                    mProgressBar.setProgress(progress);

                }
            });

        }
    };

    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }


}