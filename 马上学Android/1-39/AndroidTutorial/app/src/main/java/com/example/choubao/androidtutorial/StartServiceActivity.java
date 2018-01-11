package com.example.choubao.androidtutorial;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.choubao.service.BindService;
import com.choubao.service.StartAndBindService;
import com.choubao.service.StartService;

public class StartServiceActivity extends Activity {

    private BindService.MyBinder myBinder=null;

    private ButtonListener listener;
    private Button btnStart,btnStop,btnBind,btnUnbind;
    //解决Service not registered
    private boolean isBind=false;

    private ServiceConnection serviceConnection=new ServiceConnection() {
        //绑定时执行这个
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myBinder= (BindService.MyBinder) iBinder;
            myBinder.StartDownload();
        }
        //解除绑定时执行这个
        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service);

        this.findViewById(R.id.btnStartService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动服务
                Intent intent=new Intent(StartServiceActivity.this,StartService.class);
                Log.i("StartServiceActivity","StartServiceActivity->"+Thread.currentThread().getId());
                startService(intent);
            }
        });

        this.findViewById(R.id.btnStopService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //停止服务
                Intent intent=new Intent(StartServiceActivity.this,StartService.class);
                stopService(intent);
            }
        });

        this.findViewById(R.id.btnBindService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //绑定服务
                Intent intent=new Intent(StartServiceActivity.this,BindService.class);
                isBind=bindService(intent,serviceConnection,BIND_AUTO_CREATE);
            }
        });

        this.findViewById(R.id.btnUnbindService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //解除绑定服务
                if(isBind){
                    unbindService(serviceConnection);
                    isBind=false;
                }
            }
        });



        listener=new ButtonListener();
        btnStart= (Button) findViewById(R.id.btnStart_StartBindService);
        btnStop= (Button) findViewById(R.id.btnStop_StartBindService);
        btnBind= (Button) findViewById(R.id.btnBind_StartBindService);
        btnUnbind= (Button) findViewById(R.id.btnUnbind_StartBindService);

        btnStart.setOnClickListener(listener);
        btnStop.setOnClickListener(listener);
        btnBind.setOnClickListener(listener);
        btnUnbind.setOnClickListener(listener);
    }


    private boolean isBind2=false;
    private class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btnStart_StartBindService:
                    Intent intent=new Intent(StartServiceActivity.this,StartAndBindService.class);
                    startService(intent);
                    break;
                case R.id.btnStop_StartBindService:
                    Intent stopIntent=new Intent(StartServiceActivity.this,StartAndBindService.class);
                    stopService(stopIntent);
                    break;
                case R.id.btnBind_StartBindService:
                    Intent bindIntent=new Intent(StartServiceActivity.this,StartAndBindService.class);
                    isBind2=bindService(bindIntent,conn,BIND_AUTO_CREATE);
                    break;
                case R.id.btnUnbind_StartBindService:
                    if(isBind2){
                        unbindService(conn);
                        isBind2=false;
                    }
                    break;
            }
        }
    }

    private ServiceConnection conn=new ServiceConnection() {
        //绑定时执行这个
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            StartAndBindService.MyBinderClass myBinderClass=(StartAndBindService.MyBinderClass)iBinder;
            myBinderClass.Show();
        }
        //解除绑定时执行这个
        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}



/*

public class StartServiceActivity extends Activity implements View.OnClickListener {
    private Button btnStartService,btnStopService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service);

        btnStartService= (Button) findViewById(R.id.btnStartService);
        btnStopService= (Button) findViewById(R.id.btnStopService);

        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnStartService:

                break;
            case R.id.btnStopService:

                break;
        }
    }
}
*/
