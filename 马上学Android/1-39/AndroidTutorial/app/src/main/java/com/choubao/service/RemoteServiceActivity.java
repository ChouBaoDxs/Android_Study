package com.choubao.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.choubao.androidtutorial.IMyService;
import com.example.choubao.androidtutorial.R;

public class RemoteServiceActivity extends Activity implements View.OnClickListener {
    private Button btnBindAIDLService;
    private Button btnInvokeAIDLService;
    private Button btnUnbindAIDLService;

    private TextView tvIPC;

    private Button btnStartForeService;
    private Button btnStopForeService;

    private boolean isBind=false;
    private boolean isBindFore=false;
    private IMyService myService=null;

    private StartService foreService;

    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            myService=IMyService.Stub.asInterface(iBinder);
            btnInvokeAIDLService.setEnabled(true);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private ServiceConnection mConn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            foreService=((StartService.ForeBinder)iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_service);

        tvIPC= (TextView) findViewById(R.id.tvIPC);
        btnBindAIDLService= (Button) findViewById(R.id.btnBindAIDLService);
        btnBindAIDLService.setOnClickListener(this);

        btnInvokeAIDLService= (Button) findViewById(R.id.btnInvokeAIDLService);
        btnInvokeAIDLService.setOnClickListener(this);

        btnUnbindAIDLService= (Button) findViewById(R.id.btnUnbindAIDLService);
        btnUnbindAIDLService.setOnClickListener(this);

        btnStartForeService= (Button) findViewById(R.id.btnStartForeService);
        btnStartForeService.setOnClickListener(this);

        btnStopForeService= (Button) findViewById(R.id.btnStopForeService);
        btnStopForeService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnBindAIDLService:
                Intent aidlIntent=new Intent(this,AIDLService.class);
                isBind=bindService(aidlIntent,serviceConnection,BIND_AUTO_CREATE);
                btnInvokeAIDLService.setEnabled(true);
                break;
            case R.id.btnInvokeAIDLService:
                try {
                    String aidlStr=myService.getValue();
                    tvIPC.setText(aidlStr);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnUnbindAIDLService:
                if(isBind){
                    unbindService(serviceConnection);
                    myService=null;
                    btnInvokeAIDLService.setEnabled(false);
                    isBind=false;
                    tvIPC.setText(" ");
                }
                break;
            case R.id.btnStartForeService:
                Intent i=new Intent(this,StartService.class);
                isBindFore=bindService(i,mConn,BIND_AUTO_CREATE);
                break;

            case R.id.btnStopForeService:
                if(isBindFore){
                    unbindService(mConn);
                    isBindFore=false;
                }
                break;
        }
    }
}
