package com.choubao.www.guaguaka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GuaGuaKa mGuaGuaKa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGuaGuaKa = (GuaGuaKa) findViewById(R.id.guaGuaKa);
        mGuaGuaKa.setOnGuaGuaKaCompleteListener(new GuaGuaKa.OnGuaGuaKaCompleteListener() {
            @Override
            public void complete() {
                Toast.makeText(getApplicationContext(),"用户刮得差不多了",Toast.LENGTH_SHORT).show();
            }
        });

        //mGuaGuaKa.setText("开心");
    }
}
