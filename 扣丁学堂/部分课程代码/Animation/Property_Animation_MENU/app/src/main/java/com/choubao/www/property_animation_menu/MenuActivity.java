package com.choubao.www.property_animation_menu;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{

    private int[] res={
        R.id.imageView0,
        R.id.imageView1,
        R.id.imageView2,
        R.id.imageView3,
        R.id.imageView4,
        R.id.imageView5,
        R.id.imageView6,
        R.id.imageView7,
        R.id.imageView8
    };
    private ArrayList<ImageView> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        for(int i=0;i<res.length;i++) {
            ImageView imageView= (ImageView) findViewById(res[i]);
            imageView.setOnClickListener(this);
            list.add(imageView);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.imageView0:
                if (isOpen) {
                    closeMenu();
                    isOpen=false;
                } else {
                    openMenu();
                    isOpen=true;
                }
                break;
            default:
                Toast.makeText(this,v.getTag().toString(),Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private boolean isOpen=false;
    //打开菜单
    private void openMenu() {
        for(int i=1;i<res.length;i++) {
            ObjectAnimator a1=ObjectAnimator.ofFloat(list.get(i),"translationX",0,100*i).setDuration(50*i);
            ObjectAnimator a2=ObjectAnimator.ofFloat(list.get(i),"translationY",0,100*i).setDuration(50*i);
            a1.setInterpolator(new BounceInterpolator());//震荡效果
            a2.setInterpolator(new BounceInterpolator());//震荡效果
            a1.start();
            a2.start();
        }
    }

    //关闭菜单
    private void closeMenu() {
        for(int i=1;i<res.length;i++) {
            /*ObjectAnimator a1=ObjectAnimator.ofFloat(list.get(i),"translationX",100*i,0).setDuration(i*500);
            ObjectAnimator a2=ObjectAnimator.ofFloat(list.get(i),"translationY",100*i,0).setDuration(i*500);
            a1.setInterpolator(new BounceInterpolator());//震荡效果
            a2.setInterpolator(new BounceInterpolator());//震荡效果
            a1.start();
            a2.start();*/

            ObjectAnimator a1=ObjectAnimator.ofFloat(list.get(i),"translationX",100*i,0);
            a1.setInterpolator(new BounceInterpolator());//震荡效果
            ObjectAnimator a2=ObjectAnimator.ofFloat(list.get(i),"translationY",100*i,0);
            a2.setInterpolator(new BounceInterpolator());//震荡效果
            AnimatorSet set=new AnimatorSet();
            set.setDuration(50*i);
            //set.setInterpolator(new BounceInterpolator());//组合之后插值器无效
            set.playTogether(a1,a2);
            set.start();
        }
    }

}
