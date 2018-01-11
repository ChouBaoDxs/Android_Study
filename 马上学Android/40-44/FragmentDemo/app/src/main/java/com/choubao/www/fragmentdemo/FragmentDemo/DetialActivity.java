package com.choubao.www.fragmentdemo.FragmentDemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.choubao.www.fragmentdemo.R;

public class DetialActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detial);
        TextView textView=(TextView)findViewById(R.id.tv_news_detail);
        textView.setText(getIntent().getStringExtra("detail"));
    }
}
