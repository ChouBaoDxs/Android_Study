package com.choubao.www.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView info;
    private ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info= (TextView) findViewById(R.id.textView);
        pb= (ProgressBar) findViewById(R.id.progressBar);
    }

    public void asynaTaskClick(View v) {
        new MyAsyncTask(this).execute();
    }

    public void downloadClick(View v) {
        new DownloadAsyncTask(this).execute("PATH");//图片地址
    }

    private static class DownloadAsyncTask extends AsyncTask<String, Integer, Integer> {

        private MainActivity activity;

        public DownloadAsyncTask(MainActivity activity) {
            this.activity=activity;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            activity.pb.setProgress(0);
        }

        @Override
        protected Integer doInBackground(String... params) {
            String path=params[0];
            try {
                URL url=new URL(path);
                HttpURLConnection conn= (HttpURLConnection) url.openConnection();//打开一个Http连接
                int size=conn.getContentLength();//获取文件的大小
                //0标记表示需要更新最大进度值，1表示当前更新下载的进度值
                publishProgress(0,size);

                byte[] bytes=new byte[20];
                int len=-1;
                InputStream in=conn.getInputStream();
                FileOutputStream out=new FileOutputStream("/sdcard/"+System.currentTimeMillis()+".png");
                while((len=in.read(bytes))!=-1){
                    out.write(bytes,0,len);
                    publishProgress(1,len);//更新进度
                    out.flush();
                }
                out.close();
                in.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 200;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            switch (values[0]){
                case 0:
                    activity.pb.setMax(values[1]);
                    break;
                case 1:
                    activity.pb.incrementProgressBy(values[1]);
                    break;
            }
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            if (integer == 200) {
                activity.info.setText("下载成功");
            }
        }
    }

    //通过一个AsyncTask实现一个异步任务
    private static class MyAsyncTask extends AsyncTask<String, Integer, String> {

        private MainActivity activity;

        public MyAsyncTask(MainActivity activity) {
            this.activity=activity;
        }

        //执行任务之前触发的事件，可以在该方法中做一些初始化工作
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("onPreExecute");
            activity.info.setText("开始执行任务...");
        }

        //执行后台任务的方法，类似于在线程里执行
        @Override
        protected String doInBackground(String... params) {
            for(int i=0;i<10;i++) {
                System.out.println(i);
                publishProgress(i);//更新进度了
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "success";
        }

        //用来更新进度值
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            activity.info.setText("当前的值为："+values[0]);
        }

        //当doInBackground方法返回后被调用
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            activity.info.setText(s);//就是传来的success
        }
    }
}
