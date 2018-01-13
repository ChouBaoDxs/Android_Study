package com.choubao.www.httpurlconnection_51;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class HttpURLConnectionActivity extends AppCompatActivity {

    private TextView tv_info;
    private EditText et_username,et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_urlconnection);
        tv_info= (TextView) findViewById(R.id.info);
        et_username= (EditText) findViewById(R.id.editText_username);
        et_password= (EditText) findViewById(R.id.editText_password);
    }

    //登录功能
    public void loginClick(View view) {
        final String username=et_username.getText().toString();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        final String password=et_password.getText().toString();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }

        //启动登录工作线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                //下面是服务器地址
                String path="http://10.0.2.2:8080//Android_NetServer/LoginServlet";
//                String path="http://10.0.2.2:8080//Android_NetServer/LoginServlet?xxx=xxx&xxx=xxx";//GET请求方式
                try {
                    URL url=new URL(path);
                    //打开http连接
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");//设置请求方式
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setConnectTimeout(1000*5);//连接超时时间
                    conn.setReadTimeout(1000*5);//读数据的超时时间
                    conn.setUseCaches(false);
                    conn.setRequestProperty("Content-type","application/x-www-form-urlencoded");

                    //对服务器端读取或写入数据（使用输入输出流）
                    DataOutputStream out=new DataOutputStream(conn.getOutputStream());//获取连接的输出流
                    out.writeBytes("username="+ URLEncoder.encode("vince","GBK"));  //值，编码方式
                    out.writeBytes("&password="+ URLEncoder.encode("123","GBK"));    //值，编码方式
                    out.flush();
                    out.close();

                    //从服务器获取响应
                    BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String result=br.readLine();
                    System.out.println("result="+result);
                    br.close();
                    conn.disconnect();//关闭连接
                    Message msg=handlder.obtainMessage(LOAD_SUCCESS,result);
                    handlder.sendMessage(msg);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    handlder.sendEmptyMessage(LOAD_ERROR);
                }
            }
        }).start();
    }

    private final MyHandler handlder=new MyHandler(this);
    private static final int LOAD_SUCCESS = 200;
    private static final int LOAD_ERROR = -1;

    private static class MyHandler extends Handler {
        private final WeakReference<HttpURLConnectionActivity> mWeakReference;
        public MyHandler(HttpURLConnectionActivity activity) {
            mWeakReference=new WeakReference<HttpURLConnectionActivity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            HttpURLConnectionActivity activity=mWeakReference.get();
            if (activity != null) {
                switch(msg.what){
                    case LOAD_SUCCESS:
                        String json= (String) msg.obj;
                        activity.jsonToObject(json);
                        break;
                    case LOAD_ERROR:
                        activity.tv_info.setText("登录失败，请检查用户名和密码是否正确");
                        break;
                }
            }
        }
    }

    //解析JSON为对象
    public void jsonToObject(String json) {
        Gson gson=new Gson();
        JsonObject object=gson.fromJson(json,JsonObject.class);
        tv_info.setText(object.toString());
    }

}
