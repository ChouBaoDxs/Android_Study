package com.choubao.www.toast;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//绑定布局文件
    }

    /**
     * 按钮的单击事件的方法
     * 写法：public void 方法名(View v)
     * @param view
     */
    public void showToastText(View view) {
        //getApplicationContext() 应用程序上下文, 作用域为整个程序
        //this：当前对象
        Toast.makeText(getApplicationContext(),"骄奢淫逸",Toast.LENGTH_LONG).show();
    }

    public void showToastImage(View view) {
        Toast t=new Toast(this);
        //用于显示图片的组件
        ImageView imageView=new ImageView(this);
        imageView.setImageResource(R.drawable.she1);
        t.setView(imageView);
        t.setDuration(Toast.LENGTH_LONG);
        //设置显示的位置
        t.setGravity(Gravity.CENTER,0,0);
        t.show();
    }

    public void showToastImageText(View view) {
        Toast t=new Toast(this);
        TextView textView=new TextView(this);
        textView.setText("臭包");
        ImageView imageView=new ImageView(this);
        imageView.setImageResource(R.drawable.she1);

        LinearLayout linearLayout=new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);

        linearLayout.addView(imageView);
        linearLayout.addView(textView);

        t.setView(linearLayout);
        t.setGravity(Gravity.CENTER,0,0);
        t.setDuration(Toast.LENGTH_LONG);
        t.show();
    }
}
