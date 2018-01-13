package com.choubao.www.httpurlconnection_51;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView= (ImageView) findViewById(R.id.imageView);

    }

    private final MyHandler handlder=new MyHandler(this);
    private static final int LOAD_SUCCESS = 0x1;

    private static class MyHandler extends Handler {
        private final WeakReference<MainActivity> mWeakReference;
        public MyHandler(MainActivity mainActivity) {
            mWeakReference=new WeakReference<MainActivity>(mainActivity);
        }
        @Override
        public void handleMessage(Message msg) {
            MainActivity mainActivity=mWeakReference.get();
            if (mainActivity != null) {
                switch(msg.what){
                    case LOAD_SUCCESS:
                        mainActivity.imageView.setImageBitmap((Bitmap) msg.obj);
                        break;
                }
            }
        }
    }

    //访问网络操作必须在线程中进行，否则可能会阻塞
    public void showNetImageClick(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL("http://bmob-cdn-10805.b0.upaiyun.com/2017/04/30/ef8c1a36404385e9809f0aaca3619942.png");
                    InputStream in=url.openStream();
                    Bitmap bitmap= BitmapFactory.decodeStream(in);
                    Message msg=handlder.obtainMessage(LOAD_SUCCESS,bitmap);
                    handlder.sendMessage(msg);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
