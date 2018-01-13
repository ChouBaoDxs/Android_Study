package com.choubao.www.imageswitcher_textswitcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory, View.OnTouchListener {

    private ImageSwitcher imageSwitcher;
    private int[] images = {R.mipmap.she1, R.mipmap.she2, R.mipmap.she3, R.mipmap.she4};
    private int index=0;
    private int IMAGES_LENGTH = images.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        imageSwitcher.setOnTouchListener(this);
        imageSwitcher.setFactory(this);
    }

    @Override
    public View makeView() {
        ImageView iv = new ImageView(this);
        iv.setImageResource(images[0]);

        //设置居中
        FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity= Gravity.CENTER;

        iv.setLayoutParams(lp);
        return iv;
    }


    float startX = 0.0f;
    float endX = 0.0f;

    //触屏事件
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        int action = motionEvent.getAction();//获取当前的事件动作
/*        if (action==MotionEvent.ACTION_DOWN) {
            startX=motionEvent.getX();
            return true;
        }*/
//        System.out.println(action);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                startX = motionEvent.getX();
                return true;
            case MotionEvent.ACTION_UP:
                endX = motionEvent.getX();
                if (startX - endX > 20) {   //左滑：下一张

                    index = index + 1 < IMAGES_LENGTH ? ++index :0;
                    imageSwitcher.setInAnimation(this,android.R.anim.fade_in);
                    imageSwitcher.setInAnimation(this,android.R.anim.fade_out);
                    imageSwitcher.setImageResource(images[index]);

                } else if (endX - startX > 20) {    //右滑：上一张

                    index = index - 1 >= 0 ? --index : IMAGES_LENGTH-1;
                    imageSwitcher.setInAnimation(this,android.R.anim.fade_in);
                    imageSwitcher.setInAnimation(this,android.R.anim.fade_out);
                    imageSwitcher.setImageResource(images[index]);

                }
                System.out.println(index);
                return true;
        }
        return false;
    }

    public void toTextSwitcher(View v) {
        startActivity(new Intent(MainActivity.this,Main2Activity.class));
    }
}
