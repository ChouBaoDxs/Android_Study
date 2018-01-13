package com.choubao.www.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class ScreenOrientationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //landscape:风景画(横屏) portrait:肖像画(竖屏)
        //设置横屏的方式一：AndroidMainfest清单文件里设置android:screenOrientation="landscape"

        //设置横屏的方式二：
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //去除标题栏   //注意前面要继承Activity
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //可以在AndroidMainfest清单文件里设置主题达到前面的效果
        /**
         * android:theme="@android:style/Theme.DeviceDefault.NoActionBar.Fullscreen"
         *
         *
         * 设置窗体模式：
         * android:theme="@android:style/Theme.DeviceDefault.Dialog"
         */

        //以上代码必须要在设置布局之前
        setContentView(R.layout.activity_screen_orientation);
    }
}
