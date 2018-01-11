package com.example.choubao.myclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.DecimalFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TimePicker;

/**
 * Created by choubao on 16/11/20.
 */

public class AlarmView extends LinearLayout {

    public AlarmView(Context context, AttributeSet attrs, int defStyle) {
        super(context,attrs,defStyle);
        init();
    }

    public AlarmView(Context context, AttributeSet attrs){
        super(context,attrs);
        init();
    }

    public AlarmView(Context context) {
        super(context);
        init();
    }

    private void init() {
        alarmManager= (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        btAddAlarm=(Button) findViewById(R.id.btnAddAlarm);
        lvAlarmList=(ListView) findViewById(R.id.lvAlarmList);

        adapter=new ArrayAdapter<AlarmView.AlarmData>(getContext(),android.R.layout.simple_expandable_list_item_1);
        lvAlarmList.setAdapter(adapter);

        readSaveAlarmList();//读取已经保存的闹钟数据
        //adapter.add(new AlarmData(System.currentTimeMillis()));

        btAddAlarm.setOnClickListener(new OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                addAlarm();
            }
        });

        lvAlarmList.setOnItemClickListener(new AdapterView.OnItemClickListener() {      //删除闹钟的操作
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {
                new AlertDialog.Builder(getContext()).setTitle("操作清理").setItems(new CharSequence[]{"删除"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                deleteAlarm(position);
                                break;
                            default:
                                break;
                        }
                    }
                }).setNegativeButton("取消",null).show();
                //return true;
            }
        });
    }

    private void deleteAlarm(int position){
        AlarmData ad=adapter.getItem(position);
        adapter.remove(ad);
        saveAlarmList();

        alarmManager.cancel(PendingIntent.getBroadcast(getContext(),ad.getID(),new Intent(getContext(),AlarmReceiver.class),0));
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addAlarm() {

        TimePickerDialog.OnTimeSetListener otsl=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar calendar=Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);
                calendar.set(Calendar.SECOND,0);//秒数要清零
                calendar.set(Calendar.MILLISECOND,0);//毫秒数也要清零

                Calendar currentTime=Calendar.getInstance();
                //System.out.println(calendar);
                //System.out.println(currentTime);

                if(calendar.getTimeInMillis()<=currentTime.getTimeInMillis()){      //如果设定的时刻小于当前时刻,则推迟一天
                    calendar.setTimeInMillis(calendar.getTimeInMillis()+24*60*60*1000);
                    //System.out.println(calendar);
                }

                AlarmData ad=new AlarmData(calendar.getTimeInMillis());
                adapter.add(ad);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,//闹钟运行方式
                        ad.getTime(),//闹钟启动时间
                        5*60*1000,//闹钟间隔
                        PendingIntent.getBroadcast(getContext(),
                                ad.getID(), //请求码
                                new Intent(getContext(),AlarmReceiver.class),0));
                saveAlarmList();
            }
        };

        Calendar c=Calendar.getInstance();
        TimePickerDialog tpd=new TimePickerDialog(getContext(),otsl,c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),true);
        tpd.show();
    }

    private void saveAlarmList(){
        SharedPreferences.Editor editor=getContext().getSharedPreferences(AlarmView.class.getName(),Context.MODE_PRIVATE).edit();

        StringBuffer sb=new StringBuffer();
        for(int i=0;i<adapter.getCount();i++) {
            sb.append(adapter.getItem(i).getTime()).append(",");    //append() 在原来的内容后添加 setText()是把原来的内容冲掉
        }
        if(sb.length()>1) {
            String content = sb.toString().substring(0, sb.length() - 1);

            editor.putString(KEY_ALARM_LIST, content);

            //System.out.println(content);
        }else{
            editor.putString(KEY_ALARM_LIST,null);
        }
        editor.commit();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void readSaveAlarmList(){
        SharedPreferences sp=getContext().getSharedPreferences(AlarmView.class.getName(),Context.MODE_PRIVATE);
        String content=sp.getString(KEY_ALARM_LIST,null);
        if(content!=null){
            String[] timeStrings=content.split(",");
            for (String string:timeStrings) {
                adapter.add(new AlarmData(Long.parseLong(string)));
            }
        }
    }


    private static final String KEY_ALARM_LIST="alarmList";
    private Button btAddAlarm;
    private ListView lvAlarmList;
    private ArrayAdapter<AlarmData> adapter;
    private AlarmManager alarmManager;

    private static class AlarmData{
        @RequiresApi(api = Build.VERSION_CODES.N)
        public AlarmData(long time){
            this.time=time;
            date=Calendar.getInstance();
            date.setTimeInMillis(time);

            DecimalFormat df=new DecimalFormat("#00");
            timeLable=String.format("%d月%d日 %s:%s",
                    date.get(Calendar.MONTH)+1,         //这里的MONTH是从0开始的
                    date.get(Calendar.DAY_OF_MONTH),
                    df.format(date.get(Calendar.HOUR_OF_DAY)),
                    df.format(date.get(Calendar.MINUTE)));
        }

        public long getTime() {
            return time;
        }

        public String getTimeLable() {
            return timeLable;
        }

        @Override
        public String toString() {
            return getTimeLable();
        }
        public int getID(){
            return (int)(getTime()/1000/60);
        }
        private String timeLable="";
        private long time=0;
        private Calendar date;
    }
}
