package com.choubao.www.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/*
* 绑定服务：
* 通过绑定服务来实现功能的步骤：
* 1.客户端通过bindService方法来绑定一个服务对象，如果绑定成功会回调ServiceConnection接口方法onServiceConnected
* 2.通过Service组件来暴露业务接口
* 3.服务端通过创建一个*.aidl文件来定义一个可以被客户端调用的业务接口
* 一个aidl文件：
*   (1)不能有修饰符，类似接口的写法
*   (2)支持类型有：8种基本数据类型，String,CharSequence,List<String>,Map,自定义类型
*       自定义类型：
*       实现Parcelable接口
*       定义一个aidl文件声明类型：parcelable Person;
*       在其它aidl文件中使用，必须import
* 4.服务端需要提供业务接口的实现类，通常我们会extends Stub类
* 5.通过Service的onBind方法返回被绑定的业务对象
* 6.客户端如果绑定成功，就可以像调用自己的方法一样调用远程的业务对象方法
*
* 使用技巧：
* started启动的服务会长期存在，只要不停止
* bind启动的服务通常会在解绑时停止
* 所以一般先利用started，后bind
*
* */

public class MyBoundService extends Service {
    public MyBoundService() {
    }

    //返回的IBinder是onServiceConnected的一个参数
    @Override
    public IBinder onBind(Intent intent) {
        return new CatImpl();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
