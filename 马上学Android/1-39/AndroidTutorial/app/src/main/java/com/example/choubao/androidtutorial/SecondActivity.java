package com.example.choubao.androidtutorial;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Created by choubao on 16/11/27.
 */

public class SecondActivity extends Activity implements View.OnClickListener {
    private RatingBar rtbMyLevel;
    private SeekBar sbAndroidStudy;
    private TextView tvAndroidStudy;

    private ImageButton ibShe;
    private ProgressBar pbTimeWating;

    private DatePicker dpPicker;
    private TimePicker tpPicker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        rtbMyLevel = (RatingBar) findViewById(R.id.rtbMyLevel);
        sbAndroidStudy = (SeekBar) findViewById(R.id.sbAndroidStudy);
        tvAndroidStudy = (TextView) findViewById(R.id.tvAndroidStudy);

        ibShe = (ImageButton) findViewById(R.id.ibShe);
        pbTimeWating = (ProgressBar) findViewById(R.id.pbTimeWating);

        dpPicker = (DatePicker) findViewById(R.id.dpPicker);
        tpPicker = (TimePicker) findViewById(R.id.tpPicker);

        rtbMyLevel.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(SecondActivity.this, "你给自己的评分是" + rating, Toast.LENGTH_SHORT).show();
            }
        });

        sbAndroidStudy.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvAndroidStudy.setText("你的Android学习进度:" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ibShe.setOnClickListener(this);
        pbTimeWating.setOnClickListener(this);


        dpPicker.setCalendarViewShown(false);
        //DataPicker初始化和绑定监听事件
        dpPicker.init(2016, 11, 31, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) { //这里的月份是从0-11
                Toast.makeText(SecondActivity.this,"你选取的日期为:"+year+"-"+(monthOfYear+1)+"-"+dayOfMonth,Toast.LENGTH_SHORT).show();
            }
        });

        tpPicker.setIs24HourView(true);//设置是否支持24小时
        tpPicker.setCurrentHour(20);
        tpPicker.setCurrentMinute(20);
        tpPicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(SecondActivity.this,"你选择的时间为:"+hourOfDay+":"+minute,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibShe:
                pbTimeWating.setVisibility(View.VISIBLE);
                //创建线程
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start(); //会执行run()函数
                break;
            case R.id.pbTimeWating:

                break;
        }
    }
}
