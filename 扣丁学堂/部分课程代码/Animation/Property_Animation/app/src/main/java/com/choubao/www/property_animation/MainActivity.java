package com.choubao.www.property_animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import static android.animation.ObjectAnimator.ofFloat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button_fanzhuan,button_zuhe,button_godie,button_godie2,button_dingzhi,button_xml;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_fanzhuan= (Button) findViewById(R.id.button_fanzhuan);
        button_zuhe= (Button) findViewById(R.id.button_zuhe);
        button_godie= (Button) findViewById(R.id.button_godie);
        button_godie2= (Button) findViewById(R.id.button_godie2);
        imageView= (ImageView) findViewById(R.id.imageView);
        button_dingzhi= (Button) findViewById(R.id.button_dingzhi);
        button_xml= (Button) findViewById(R.id.button_xml);

        button_fanzhuan.setOnClickListener(this);
        button_zuhe.setOnClickListener(this);
        button_godie.setOnClickListener(this);
        button_godie2.setOnClickListener(this);
        button_dingzhi.setOnClickListener(this);
        button_xml.setOnClickListener(this);
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_fanzhuan:  //这里是翻转效果
                ofFloat(v,"rotationX",0f,360f).setDuration(1000).start();
                break;
            case R.id.button_zuhe:    //组合多个动画
                PropertyValuesHolder p1=PropertyValuesHolder.ofFloat("alpha",1f,0f,1f);
                PropertyValuesHolder p2=PropertyValuesHolder.ofFloat("scaleX",1f,0f,1f);
                PropertyValuesHolder p3=PropertyValuesHolder.ofFloat("scaleY",1f,0f,1f);
                ObjectAnimator.ofPropertyValuesHolder(v,p1,p2,p3).setDuration(1000).start();
                break;
            case R.id.imageView:    //监听动画  往屏幕下面掉
                final View view=v;
                //获取屏幕属性
                DisplayMetrics dm=new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);

                //定义一个动画
                //ValueAnimator va=ValueAnimator.ofFloat(v.getY(),dm.heightPixels).setDuration(1000);//掉下去就消失了
                ValueAnimator va=ValueAnimator.ofFloat(v.getY(),dm.heightPixels,v.getY()).setDuration(1000);//掉下去会再升回来
                //用来监听动画的每个动作
                va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        view.setTranslationY((Float) animation.getAnimatedValue());
                    }
                });
                va.start();
                break;
            case R.id.button_godie: //监听动画事件
                final View view2=v;
                ObjectAnimator oa=ObjectAnimator.ofFloat(v,"alpha",1f,0f).setDuration(1000);
                oa.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        //动画开始时调用
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //动画结束时调用   通常使用这个
                        ViewGroup viewGroup= (ViewGroup) view2.getParent();
                        if (viewGroup!=null) {
                            viewGroup.removeView(view2);
                            System.out.println("removed");
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        //动画取消时调用
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        //重复播放时调用
                    }
                });
                oa.start();
                break;
            case R.id.button_godie2:    //这是上面的简洁版
                final View view3=v;
                ObjectAnimator oa2=ObjectAnimator.ofFloat(v,"alpha",1f,0f).setDuration(1000);
                oa2.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        ViewGroup viewGroup= (ViewGroup) view3.getParent();
                        if (viewGroup!=null) {
                            viewGroup.removeView(view3);
                            System.out.println("removed");
                        }
                    }
                });
                oa2.start();
                break;
            case R.id.button_dingzhi:   //使用AnimatorSet定制动画顺序
                ObjectAnimator a1=ObjectAnimator.ofFloat(v,"translationX",0f,-200f);
                ObjectAnimator a2=ObjectAnimator.ofFloat(v,"translationY",0f,200f);
                ObjectAnimator a3=ObjectAnimator.ofFloat(v,"rotation",0f,360f);
                AnimatorSet set=new AnimatorSet();
                set.setDuration(1000);
                //set.playTogether(a1,a2,a3);//三个动画一起执行
                //set.playSequentially(a1,a2,a3);//三个动画按顺序
                //set.setStartDelay(300);//延迟执行
                //set.play(a1).with(a2);set.play(a3).after(a2);//a1 a2同时执行，之后a3

                //插值器
                set.setInterpolator(new AccelerateDecelerateInterpolator());
                set.playTogether(a1,a2,a3);//三个动画一起执行
                set.start();
                break;
            case R.id.button_xml:   //使用xml定义属性动画
                Animator a=AnimatorInflater.loadAnimator(this,R.animator.animator);
                a.setTarget(v);
                a.start();
                break;
        }
    }
}
