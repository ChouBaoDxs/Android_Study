package com.choubao.www.progressbar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置标题栏  必须在setContentView()方法之前
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        setContentView(R.layout.activity_main);

        //设置标题栏进度条  并显示不出来
        setProgressBarIndeterminateVisibility(true);
    }

    public void showDialogProgress(View v) {
        //创建对话框进度条
        ProgressDialog pd=new ProgressDialog(this);
        pd.setMax(100);
        pd.setIndeterminate(false);
        pd.setProgress(30);
        pd.setSecondaryProgress(50);
        pd.setTitle("下载对话框");
        pd.setMessage("正在下载中...");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//设置进度条样式
        pd.setCancelable(false);//设置对话框是否能被取消
        pd.show();

        //方式二：
//        ProgressDialog pd2=ProgressDialog.show(this,"下载对话框","正在下载中...",false,true);
    }
}
