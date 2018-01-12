package com.choubao.www.serviceactivitycommunication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.choubao.www.serviceactivitycommunication.MybyInterface.MyActivity;
import com.choubao.www.serviceactivitycommunication.byBind.bind;
import com.choubao.www.serviceactivitycommunication.byBroadcast.broadcast;
import com.choubao.www.serviceactivitycommunication.byInterface.interFace;


//Android Service与Activity之间通信的几种方式
//http://blog.csdn.net/xiaanming/article/details/9750689/
public class MainActivity extends Activity implements View.OnClickListener{

    private Button btn1,btn2,btn3,btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1= (Button) findViewById(R.id.btn1);
        btn2= (Button) findViewById(R.id.btn2);
        btn3= (Button) findViewById(R.id.btn3);
        btn4= (Button) findViewById(R.id.btn4);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                Intent i=new Intent(this,bind.class);
                startActivity(i);
                break;
            case R.id.btn2:
                Intent i2=new Intent(this,interFace.class);
                startActivity(i2);
                break;
            case R.id.btn3:
                Intent i3=new Intent(this, broadcast.class);
                startActivity(i3);
                break;
            case R.id.btn4:
                Intent i4=new Intent(this, MyActivity.class);
                startActivity(i4);
                break;
        }
    }
}
