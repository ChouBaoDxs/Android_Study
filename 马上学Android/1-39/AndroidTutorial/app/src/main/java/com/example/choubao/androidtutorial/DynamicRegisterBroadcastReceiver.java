package com.example.choubao.androidtutorial;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by choubao on 16/12/2.
 */

//Intent.ACTION_BATTERY_CHANGED的详解见drawable目录下的jpg图片

public class DynamicRegisterBroadcastReceiver  extends Activity{
    private TextView tvBatteryInfo;
    private static final String BATTERY_ACTION= Intent.ACTION_BATTERY_CHANGED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_register_broadcast_receiver);

        //获取TextView对象
        tvBatteryInfo=(TextView) findViewById(R.id.tvBatteryInfo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //动态注册广播接收器
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(BATTERY_ACTION);
        registerReceiver(mBatteryBR,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //注销广播接收器
        unregisterReceiver(mBatteryBR);
    }
    private BroadcastReceiver mBatteryBR=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //处理Intent传回的电池信息
            if (intent.getAction().equals(BATTERY_ACTION)) {

                int status=intent.getIntExtra("status",0);//电池状态
                int health=intent.getIntExtra("health",1);//健康状态
                boolean present=intent.getBooleanExtra("present",false);//是否使用电池
                int level=intent.getIntExtra("level",0);//电池剩余电量
                int scale=intent.getIntExtra("scale",0);//电池最大值，通常未100
                int plugged=intent.getIntExtra("plugged",0);//充电方式
                int voltage=intent.getIntExtra("voltage",0);//电池电压（伏特）
                int temperature=intent.getIntExtra("temperature",0);//电池温度，单位为0.1度，197=19.7度
                String technology=intent.getStringExtra("technology");//电池类型，例如，Li-ion等

                String statusString="未知状态";
                //电池状态的判断
                switch(status){
                    case BatteryManager.BATTERY_STATUS_UNKNOWN:
                        statusString="未知状态";
                        break;
                    case BatteryManager.BATTERY_STATUS_CHARGING:
                        statusString="充电状态";
                        break;
                    case BatteryManager.BATTERY_STATUS_DISCHARGING:
                        statusString="放电状态";
                        break;
                    case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                        statusString="未充电";
                        break;
                    case BatteryManager.BATTERY_STATUS_FULL:
                        statusString="充满电";
                        break;
                }

                String healthString="未知状态";
                switch(health){
                    case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                        healthString="未知状态";
                        break;
                    case BatteryManager.BATTERY_HEALTH_GOOD:
                        healthString="状态好";
                        break;
                    case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                        healthString="电池过热";
                        break;
                    case BatteryManager.BATTERY_HEALTH_DEAD:
                        healthString="电池没有的电";
                        break;
                    case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                        healthString="电池电压过高";
                        break;
                    case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                        healthString="未知错误";
                        break;
                }

                String acString="未知状态";
                switch(plugged){
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        acString="直流充电";
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        acString="USB充电";
                        break;
                    case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                        acString="无线充电";
                        break;
                }
                tvBatteryInfo.setText("电池状态信息如下:\n"+
                        "\n是否使用电池:"+String.valueOf(present)+
                        "\n电池状态:"+statusString+
                        "\n电池电量:"+String.valueOf(level)+"%"+
                        "\n电池健康状态:"+healthString+
                        "\n最大值:"+String.valueOf(scale)+
                        "\n充电方式:"+acString+
                        "\n电池电压:"+String.valueOf(voltage)+
                        "\n电池温度:"+String.valueOf(temperature/10.0)+
                        "\n电池类型:"+technology);
            }
        }
    };
}
