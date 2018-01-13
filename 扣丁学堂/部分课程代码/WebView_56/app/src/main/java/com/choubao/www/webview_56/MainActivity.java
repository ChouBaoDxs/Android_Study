package com.choubao.www.webview_56;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView= (WebView) findViewById(R.id.webView);
        handler=new Handler();

        //想要不跳转到系统浏览器，必须写这么多 百度到的
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportMultipleWindows(true);
        //webView.setWebViewClient(new WebViewClient());
        //webView.setWebChromeClient(new WebChromeClient());
        //webView.loadUrl("http://www.baidu.com");

        //webView.loadData("<html><title><body>hello webview</body></title></html>","text/html","utf-8");

        //获取设置对象
        WebSettings settings=webView.getSettings();
        settings.setJavaScriptEnabled(true);//支持js
        settings.setSupportZoom(true);//缩放
        settings.setBuiltInZoomControls(true);//在右下角显示+-用于放大缩小

        webView.requestFocus();//防止软键盘无法弹出
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);//滚动条在内部显示

        //点击新链接之后是重新打开一个网页还是覆盖当前网页  true是不跳转
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //处理标题、图标等
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });

        webView.addJavascriptInterface(new MyObject(),"demo");
        webView.loadUrl("file:///android_asset/index.html");
//        webView.loadUrl("http://39.108.90.71:8080/SecretBoard/");
    }

    public class MyObject{
        @JavascriptInterface
        public void clickOnAndroid(){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("javascript:myfun()");//调用index.html里的myfun()
                }
            });
        }
    }

    //设置回退键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();//后退
            //webView.goForward();//前进
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
