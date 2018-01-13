package com.choubao.www.dateandtime;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView_time;

    private TextView textView_date;

    private TimePicker timePicker;

    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView_time= (TextView) findViewById(R.id.textView_time);

        textView_date= (TextView) findViewById(R.id.textView_date);

        timePicker= (TimePicker) findViewById(R.id.timePicker);

        datePicker= (DatePicker) findViewById(R.id.datePicker);
    }

    public void savetimeClick(View v) {
        int hour=timePicker.getCurrentHour();
        int minute=timePicker.getCurrentMinute();
        Toast.makeText(this,hour+":"+minute,Toast.LENGTH_SHORT).show();
    }

    public void savedateClick(View v) {
        int year=datePicker.getYear();
        int month=datePicker.getMonth();
        int day=datePicker.getDayOfMonth();
        Toast.makeText(this,year+"年"+(month+1)+"月"+day+"日",Toast.LENGTH_SHORT).show();
    }


    //设置日期的按钮时间
    public void setDateClick(View v) {
        DialogFragment dateDialogFragment=new DatePickerFragment();
        dateDialogFragment.show(getSupportFragmentManager(),"DatePicker");
    }

    //month从0开始
    public void setDateValue(int year,int month,int day) {
        textView_date.setText(year+"年"+(month+1)+"月"+day+"日");
    }



    //设置时间按钮单击事件
    public void setTimeClick(View view) {
        DialogFragment timePickerFragment=new TimePickerFragment();
        timePickerFragment.show(getSupportFragmentManager(),"TimePicker");
    }

    public void setTimeValue(int hour,int minute) {
        textView_time.setText(hour+":"+minute);
    }
}
