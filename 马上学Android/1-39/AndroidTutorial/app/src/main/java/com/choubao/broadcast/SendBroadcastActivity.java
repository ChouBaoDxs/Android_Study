package com.choubao.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.choubao.androidtutorial.R;

/**
 * Created by choubao on 16/12/2.
 */

public class SendBroadcastActivity extends Activity {
    private Button btnSendBroadcast;
    private EditText etSendBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_broadcast);

        btnSendBroadcast= (Button) findViewById(R.id.btnSendBroadcast);
        etSendBroadcast= (EditText) findViewById(R.id.etSendBroadcast);

        btnSendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送广播
                String strMsg=etSendBroadcast.getText().toString();
                Intent broadcastIntent=new Intent();
                broadcastIntent.setAction("com.choubao.action.NOTIFA");
                broadcastIntent.putExtra("msg",strMsg);
                sendBroadcast(broadcastIntent);
            }
        });
    }
}
