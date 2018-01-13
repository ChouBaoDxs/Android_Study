package com.choubao.www.notification;

import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //接收Activity1传递来的信息
        String msg=getIntent().getStringExtra("msg");
        TextView tv= (TextView) findViewById(R.id.textView);
        tv.setText(msg);

        //表示打开这个界面后，取消指定id的通知
        NotificationManager nm= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.cancel(MainActivity.NID_1);
    }
}
