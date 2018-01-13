package com.choubao.www.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Main6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
    }

    //显示网页
    public void intent1(View v) {
        Uri uri=Uri.parse("http://www.baidu.com");
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

    //拨打电话
    public void intent2(View v) {
        Uri uri=Uri.parse("tel:17706440586");
        Intent intent=new Intent(Intent.ACTION_DIAL,uri);
        startActivity(intent);

//        Uri uri=Uri.parse("tel:17706440586");
//        Intent intent=new Intent(Intent.ACTION_CALL,uri);//这个要在清单文件中加入许可CALL_PHONE
//        startActivity(intent);
    }

    //调用发送短信的程序
    public void intent3(View v) {
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.putExtra("sms_body","The SMS text");
        intent.setType("vnd.android-dir/mms-sms");
        startActivity(intent);
    }

    //发送短信
    public void intent4(View v) {
        Uri uri=Uri.parse("smsto:17706440586");
        Intent intent=new Intent(Intent.ACTION_SENDTO,uri);
        intent.putExtra("sms_body","The SMS text");
        startActivity(intent);
    }

    //播放多媒体
    public void intent5(View v) {
        Uri uri=Uri.parse("file:///sdcard/song.mp3");
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
//        intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);//非必选项
        intent.setDataAndType(uri,"audio/mp3");
        startActivity(intent);
    }

    //卸载程序
    public void intent6(View v) {
        Uri uri=Uri.parse("package:com.vince.hello");
        Intent intent=new Intent(Intent.ACTION_DELETE,uri);
        startActivity(intent);
    }

    //安装apk
    public void intent7(View v) {
//        Uri uri=Uri.fromFile(file);
//        Intent intent=new Intent(Intent.ACTION_VIEW);
//        intent.setDataAndType(uri,"application/vnd.android.package-archive");
//        startActivity(intent);
    }
}
