package com.choubao.www.progressbar;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private HorizontalProgressbarWithProgress mHProgress;
    private RoundProgressBarWithProgress mRProgress;
    private static final int MSG_UPDATE=0x1;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int progress=mHProgress.getProgress();
            mHProgress.setProgress(++progress);
            mRProgress.setProgress(++progress);
            if (progress >= 100) {
                mHandler.removeMessages(MSG_UPDATE);
            }
            mHandler.sendEmptyMessageDelayed(MSG_UPDATE,100);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHProgress= (HorizontalProgressbarWithProgress) findViewById(R.id.id_progress01);
        mRProgress= (RoundProgressBarWithProgress) findViewById(R.id.id_progress02);
        mHandler.sendEmptyMessage(MSG_UPDATE);
    }
}
