package com.example.choubao.learnbroadcastrecevicer;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnSendMsg).setOnClickListener(this);
        findViewById(R.id.btnReg).setOnClickListener(this);
        findViewById(R.id.btnUnreg).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnSendMsg:
                /*Intent i=new Intent(this,MyReceiver.class);
                i.putExtra("data","choubao");
                sendBroadcast(new Intent(i));*/
                Intent i=new Intent(MyReceiver.ACTION);
                i.putExtra("data","choubao");
                //sendBroadcast(new Intent(i));//这种广播方式不可被中断
                sendOrderedBroadcast(i,null);//这种广播方式可以被中断
                break;
            case R.id.btnReg:                   //注册接收器
                if(receiver==null){
                    receiver=new MyReceiver();
                    registerReceiver(receiver,new IntentFilter(MyReceiver.ACTION));
                }
                break;
            case R.id.btnUnreg:                 //注销接收器
                if(receiver!=null){
                    unregisterReceiver(receiver);
                    receiver=null;
                }
                break;
        }
    }

    private MyReceiver receiver=null;
}
