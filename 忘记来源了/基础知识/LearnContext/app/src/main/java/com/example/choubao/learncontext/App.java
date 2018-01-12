package com.example.choubao.learncontext;

import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by choubao on 16/11/15.
 */

public class App extends Application {
    private String textData="default";

    public void setTextData(String textData) {
        this.textData = textData;
    }

    public String getTextData() {
        return textData;
    }

    @Override
    public void onCreate() {//先于Activity的onCreate执行
        super.onCreate();

        System.out.println("App onCreate");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        System.out.println("App Terminate");
    }

    @Override
    public void onLowMemory() {//低内存时执行
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {//程序在进行内存清理时执行
        super.onTrimMemory(level);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {//配置发生改变时执行
        super.onConfigurationChanged(newConfig);
    }
}
