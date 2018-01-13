package com.choubao.www.imageswitcher_textswitcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class Main2Activity extends AppCompatActivity implements ViewSwitcher.ViewFactory,View.OnTouchListener{

    private TextSwitcher textSwitcher;
    private String[] texts={"刑法","民法","行政法"};
    private int index=0;
    private int TEXTS_LENGTH=texts.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textSwitcher= (TextSwitcher) findViewById(R.id.textSwitcher1);
        textSwitcher.setOnTouchListener(this);
        textSwitcher.setFactory(this);
    }

    public void toImageSwitcher(View v) {
        startActivity(new Intent(Main2Activity.this,MainActivity.class));
    }

    @Override
    public View makeView() {
        TextView tv=new TextView(this);
        tv.setText(texts[index]);

        //设置居中
        FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity=Gravity.CENTER;

        tv.setLayoutParams(lp);
        return tv;
    }

    float startX = 0.0f;
    float endX = 0.0f;
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();//获取当前的事件动作
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                startX = motionEvent.getX();
                return true;
            case MotionEvent.ACTION_UP:
                endX = motionEvent.getX();
                if (startX - endX > 20) {   //左滑：下一张

                    index = index + 1 < TEXTS_LENGTH ? ++index :0;
                    textSwitcher.setInAnimation(this,android.R.anim.fade_in);
                    textSwitcher.setInAnimation(this,android.R.anim.fade_out);
                    textSwitcher.setText(texts[index]);

                } else if (endX - startX > 20) {    //右滑：上一张

                    index = index - 1 >= 0 ? --index : TEXTS_LENGTH-1;
                    textSwitcher.setInAnimation(this,android.R.anim.fade_in);
                    textSwitcher.setInAnimation(this,android.R.anim.fade_out);
                    textSwitcher.setText(texts[index]);

                }
                System.out.println(index);
                return true;
        }
        return false;
    }
}
