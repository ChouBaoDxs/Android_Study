package com.choubao.www.dateandtime;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by choubao on 17/3/10.
 */

public class TimePickerFragment extends android.support.v4.app.DialogFragment implements TimePickerDialog.OnTimeSetListener{

    int hour,minute;
    private MainActivity mainActivity;//*********************************

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //通过getActivity方法获取该Fragment所依赖的Activity对象
        mainActivity= (MainActivity) getActivity();//*********************************
    }

    //创建对话框的事件方法:该方法会在MainActivity的按钮事件中调用show方法时，会检查是否已存在Dialog,
    //当不存在时会触发该事件，否则直接显示
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //Calendar:日历
        //获取当前的系统时间
        Calendar c=Calendar.getInstance();
        hour=c.get(Calendar.HOUR_OF_DAY);
        minute=c.get(Calendar.MINUTE);

        //创建时间对话框，参数为(上下文,设置时间事件,小时,分钟,是否为24小时制)
        TimePickerDialog dialog=new TimePickerDialog(getActivity(),this,hour,minute,true);
        return dialog;
    }

    //时间对话框中的"完成"按钮的单击事件
    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        this.hour=i;
        this.minute=i1;
        //把用户选择的时间显示到TextView组件上
        mainActivity.setTimeValue(hour,minute);//*********************************
    }
}
