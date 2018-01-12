package com.choubao.www.serviceactivitycommunication.MybyInterface;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.choubao.www.serviceactivitycommunication.R;

public class MyActivity extends Activity {

    private MyService msgService;
    private ProgressBar mProgressBar;
    private TextView tv4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        //绑定Service
        Intent intent = new Intent(this,MyService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);


        mProgressBar = (ProgressBar) findViewById(R.id.progressBar4);
        Button mButton = (Button) findViewById(R.id.button4);
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //开始下载
                msgService.startDownLoad();
            }
        });


        tv4= (TextView) findViewById(R.id.tv4);

    }


    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //返回一个MsgService对象
            msgService = ((MyService.MsgBinder)service).getService();

            //注册回调接口来接收下载进度的变化
            msgService.setOnMyListener(new OnMyListener() {

                @Override
                public void onProgress(int progress) {
                    mProgressBar.setProgress(progress);

//                    tv4.setText(msgService.getProgress());    //子线程不能改变UI
                    Log.e("2333333",""+msgService.getProgress());
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
