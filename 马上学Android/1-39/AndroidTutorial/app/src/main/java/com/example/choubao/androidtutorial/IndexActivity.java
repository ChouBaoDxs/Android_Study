package com.example.choubao.androidtutorial;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.choubao.barcode.ScanBarcodeActivity;
import com.choubao.broadcast.SendBroadcastActivity;
import com.choubao.service.RemoteServiceActivity;

/**
 * Created by choubao on 16/11/30.
 */

public class IndexActivity extends Activity {
    private Button btnStartFirstActivity,btnStartSecondActivity,btnStartActivityLifeCycle,btnStartCaptureActivity,
            btnStartBroadcastActivity,btnStartDynamicRegisterBroadcastReceiver,btnScanBarcodeActivity,
            btnStartThreadTutorialActivity,btnStartServiceActivity,btnStartRemoteServiceActivity,
            btnStartMenuActivity;
    private ButtonListener listener;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        listener=new ButtonListener();
        FindView();
        SetClickListener();
    }

    private void FindView() {
        btnStartFirstActivity= (Button) findViewById(R.id.btnStartFirstActivity);
        btnStartSecondActivity= (Button) findViewById(R.id.btnStartSecondActivity);
        btnStartActivityLifeCycle= (Button) findViewById(R.id.btnStartActivityLifeCycle);
        btnStartCaptureActivity= (Button) findViewById(R.id.btnStartCaptureActivity);
        btnStartBroadcastActivity= (Button) findViewById(R.id.btnStartBroadcastActivity);
        btnStartDynamicRegisterBroadcastReceiver= (Button) findViewById(R.id.btnStartDynamicRegisterBroadcastReceiver);
        btnScanBarcodeActivity= (Button) findViewById(R.id.btnScanBarcodeActivity);
        btnStartThreadTutorialActivity= (Button) findViewById(R.id.btnStartThreadTutorialActivity);
        btnStartServiceActivity= (Button) findViewById(R.id.btnStartServiceActivity);
        btnStartRemoteServiceActivity= (Button) findViewById(R.id.btnStartRemoteServiceActivity);
        btnStartMenuActivity= (Button) findViewById(R.id.btnStartMenuActivity);
    }
    private void SetClickListener() {
        btnStartFirstActivity.setOnClickListener(listener);
        btnStartSecondActivity.setOnClickListener(listener);
        btnStartActivityLifeCycle.setOnClickListener(listener);
        btnStartCaptureActivity.setOnClickListener(listener);
        btnStartBroadcastActivity.setOnClickListener(listener);
        btnStartDynamicRegisterBroadcastReceiver.setOnClickListener(listener);
        btnScanBarcodeActivity.setOnClickListener(listener);
        btnStartThreadTutorialActivity.setOnClickListener(listener);
        btnStartServiceActivity.setOnClickListener(listener);
        btnStartRemoteServiceActivity.setOnClickListener(listener);
        btnStartMenuActivity.setOnClickListener(listener);
    }
    private class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnStartFirstActivity:
                    Intent intent1=new Intent(IndexActivity.this,FirstActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.btnStartSecondActivity:
                    Intent intent2=new Intent(IndexActivity.this,SecondActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.btnStartActivityLifeCycle:
                    Intent intent3=new Intent(IndexActivity.this,ActivityLifeCycle.class);
                    startActivity(intent3);
                    break;
                case R.id.btnStartCaptureActivity:
                    Intent intent4=new Intent(IndexActivity.this,CaptureActivity.class);
                    startActivity(intent4);
                    break;
                case R.id.btnStartBroadcastActivity:
                    Intent intent5=new Intent(IndexActivity.this,SendBroadcastActivity.class);
                    startActivity(intent5);
                    break;
                case R.id.btnStartDynamicRegisterBroadcastReceiver:
                    Intent intent6=new Intent(IndexActivity.this,DynamicRegisterBroadcastReceiver.class);
                    startActivity(intent6);
                    break;
                case R.id.btnScanBarcodeActivity:
                    Intent intent7=new Intent(IndexActivity.this,ScanBarcodeActivity.class);
                    startActivity(intent7);
                    break;
                case R.id.btnStartThreadTutorialActivity:
                    Intent intent8=new Intent(IndexActivity.this,ThreadTutorialActivity.class);
                    startActivity(intent8);
                    break;
                case R.id.btnStartServiceActivity:
                    Intent intent9=new Intent(IndexActivity.this,StartServiceActivity.class);
                    startActivity(intent9);
                    break;
                case R.id.btnStartRemoteServiceActivity:
                    Intent intent10=new Intent(IndexActivity.this,RemoteServiceActivity.class);
                    startActivity(intent10);
                    break;
                case R.id.btnStartMenuActivity:
                    Intent intent11=new Intent(IndexActivity.this,MenuActivity.class);
                    startActivity(intent11);
                    break;
            }
        }
    }


    private long exitTime=0;
    //应用程序退出的两种方式    两种方式选其一
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //应用程序退出方式一：确认退出对话框
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
            alertDialog.setTitle("提示");
            alertDialog.setMessage("确认退出？");
            alertDialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    return;
                }
            });

            alertDialog.show();
        }
        //应用程序退出方式二：连续按两次返回键退出
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次返回键将退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
