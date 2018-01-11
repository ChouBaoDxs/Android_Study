package com.example.choubao.myclock;

import android.content.Context;
import android.icu.text.DecimalFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by choubao on 16/11/20.
 */

public class TimeView extends LinearLayout {

    public TimeView(Context context, AttributeSet attrs,int defStyle){
        super(context,attrs,defStyle);
    }

    public TimeView(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    public TimeView(Context context){
        super(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        tvTime=(TextView) findViewById(R.id.tvTime);
        tvTime.setText("Hello");
        timerHandler.sendEmptyMessage(0);
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {  //可见属性发生改变时会执行的函数
        super.onVisibilityChanged(changedView, visibility);
        if (visibility==View.VISIBLE){
            timerHandler.sendEmptyMessage(0);
        }else{
            timerHandler.removeMessages(0);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void refreshTime(){
        Calendar c=Calendar.getInstance();

        DecimalFormat df=new DecimalFormat("#00");
        tvTime.setText(String.format("%s:%s:%s",df.format(c.get(Calendar.HOUR_OF_DAY)),df.format(c.get(Calendar.MINUTE)),df.format(c.get(Calendar.SECOND))));
    }

    private Handler timerHandler=new Handler(){
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void handleMessage(Message msg) {
            refreshTime();
            if(getVisibility()==View.VISIBLE){          //如果当前时钟是不可见的,就不刷新时间了
                timerHandler.sendEmptyMessageDelayed(0,1000);
            }
        }
    };
    private TextView tvTime;
}
