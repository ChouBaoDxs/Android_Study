package com.choubao.www.apache_httpclient_52;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;


//两种请求方式的区别：
//get：大小不能超过4KB，速度快，会在URL上显示，不安全
//post：大小不限制，速度比get稍慢，不会子啊URL上显示，安全性高
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getClick(View view) {
        getRequest();
    }

    //使用apache HttpClient的GET请求
    public void getRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String path="http://10.0.2.2:8080/Android_NetServer/LoginServlet?username=name&password=123456";
                //创建请求对象
                HttpGet get=new HttpGet(path);
                //创建HTTP客户端对象，用于发送请求
                HttpClient httpClient=new DefaultHttpClient();
                //向服务器发送请求，并返回响应对象
                HttpResponse response=httpClient.execute(get);
                //获取响应的状态码
                int status=response.getStatusLine().getStatuCode();
                switch(status){
                    case HttpStatus.SC_OK://200
                        HttpEntity entity=response.getEntity();
                        String result=EntityUtils.toString(entity,"utf-8");
                        System.out.println(result);
                        break;
                    case HttpStatus.SC_NOT_FOUND://404
                        break;
                    case HttpStatus.SC_INTERNAL_SERVER_ERROR://500
                        break;
                }
            }
        }).start();
    }

    public void postClick(View view) {
        postRequest();
    }

    //使用apache HttpClient的POST请求
    private void postRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String path="http://10.0.2.2:8080/Android_NetServer/LoginServlet";
                //创建请求对象
                HttpPost post=new HttpPost(path);
                ArrayList<NameValuePair> params=new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username","vince"));
                params.add(new BasicNameValuePair("password","123456"));
                HttpEntity entity=new UrlEncodeFromEntity(params);
                post.setEntity(entity);
                HttpClient httpClient=new DefaultHttpClient();
                HttpResponse response=httpClient.execute(post);

                int status=response.getStatusLine().getStatuCode();
                switch(status){
                    case HttpStatus.SC_OK://200
                        HttpEntity entity=response.getEntity();
                        String result=EntityUtils.toString(entity,"utf-8");
                        System.out.println(result);
                        break;
                    case HttpStatus.SC_NOT_FOUND://404
                        break;
                    case HttpStatus.SC_INTERNAL_SERVER_ERROR://500
                        break;
                }
            }
        }).start();
    }
}
