package com.choubao.www.view_drawable_animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button_alpha_xml,button_alpha_code,button_scale_xml,button_translate_xml,button_rotate_xml;
    private ImageView imageView,imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_alpha_xml= (Button) findViewById(R.id.button_alpha_xml);
        button_alpha_code= (Button) findViewById(R.id.button_alpha_code);
        button_scale_xml= (Button) findViewById(R.id.button_scale_xml);
        button_translate_xml= (Button) findViewById(R.id.button_translate_xml);
        button_rotate_xml= (Button) findViewById(R.id.button_rotate_xml);
        imageView= (ImageView) findViewById(R.id.imageView);
        imageView2= (ImageView) findViewById(R.id.imageView2);

        button_alpha_xml.setOnClickListener(this);
        button_alpha_code.setOnClickListener(this);
        button_scale_xml.setOnClickListener(this);
        button_translate_xml.setOnClickListener(this);
        button_rotate_xml.setOnClickListener(this);
        imageView2.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            //补间动画****************************************************************************
            case R.id.button_alpha_xml:     //透明度渐变
                //加载动画资源文件
                Animation alpha1=AnimationUtils.loadAnimation(this,R.anim.alpha_anim);
                //ImageView iv= (ImageView) v;
                //启动动画
                //iv.startAnimation(alpha1);
                imageView.startAnimation(alpha1);
                break;
            case R.id.button_alpha_code:    //透明度渐变
                Animation alpha2=new AlphaAnimation(0.1f,1.0f);
                alpha2.setDuration(3000);
                //ImageView iv2= (ImageView) v;
                //启动动画
                //iv2.startAnimation(alpha2);
                imageView.startAnimation(alpha2);
                break;
            case R.id.button_scale_xml:     //伸缩
                Animation scale=AnimationUtils.loadAnimation(this,R.anim.scale_anim);
                imageView.startAnimation(scale);
                break;
            case R.id.button_translate_xml: //平移
                Animation translate=AnimationUtils.loadAnimation(this,R.anim.translate_anim);
                imageView.startAnimation(translate);
                break;
            case R.id.button_rotate_xml:    //旋转
                Animation rotate=AnimationUtils.loadAnimation(this,R.anim.rotate_anim);
                imageView.startAnimation(rotate);
                break;
            //帧动画****************************************************************************
            case R.id.imageView2:
                AnimationDrawable ad= (AnimationDrawable) imageView2.getDrawable();
                ad.start();
                //ad.stop();
                //imageView2.setImageResource(R.drawable.anim_list);
                break;
        }
    }
}
