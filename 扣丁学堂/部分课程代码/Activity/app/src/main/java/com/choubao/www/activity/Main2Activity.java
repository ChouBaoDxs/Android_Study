package com.choubao.www.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView= (TextView) findViewById(R.id.textView_receive_info);

        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("data");
        String info=bundle.getString("info");

//        String info=intent.getStringExtra("info");
        // 这个18是缺省值
//        int age=intent.getIntExtra("age",18);

        Cat cat= (Cat) intent.getSerializableExtra("cat");

        Dog dog=intent.getParcelableExtra("dog");

        textView.setText(info+"\n"+cat.toString()+"\n"+dog.toString());
    }
}
