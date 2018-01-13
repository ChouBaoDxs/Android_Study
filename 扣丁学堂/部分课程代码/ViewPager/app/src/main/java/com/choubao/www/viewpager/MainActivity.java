package com.choubao.www.viewpager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private ViewPager viewPager;
    private PagerTabStrip pagerTabStrip;

    private String[] titles={"she1","she2","she3","she4"};
    private ArrayList<View> views=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager= (ViewPager) findViewById(R.id.viewPaper);
        pagerTabStrip= (PagerTabStrip) findViewById(R.id.papertab);
        initViews();
        viewPager.setOnPageChangeListener(this);
        viewPager.setAdapter(new MyPagerAdapter());

        //设置点击监听事件
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            int flage=0;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        flage=0;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        flage=1;
                        break;
                    case MotionEvent.ACTION_UP:

                        if (flage==0) { //意思是按下了，但是没有移动，直接提起来＝＝单击
                            switch(viewPager.getCurrentItem()){
                                case 0:
                                    startActivity(new Intent(MainActivity.this,OtherActivity.class));
                                    break;
                                case 1:

                                    break;
                                case 2:

                                    break;
                                case 3:

                                    break;
                            }
                        }
                        break;
                }
                return false;
            }
        });
    }

    private void initViews() {
        views.add(getLayoutInflater().inflate(R.layout.layout1,null));
        views.add(getLayoutInflater().inflate(R.layout.layout2,null));
        views.add(getLayoutInflater().inflate(R.layout.layout3,null));
        views.add(getLayoutInflater().inflate(R.layout.layout4,null));

        //标签栏背景颜色
        pagerTabStrip.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        //标签栏标志颜色
        pagerTabStrip.setTabIndicatorColor(getResources().getColor(android.R.color.holo_blue_bright));
        //标题栏文字颜色
        pagerTabStrip.setTextColor(Color.WHITE);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Toast.makeText(this,""+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //适配器
    class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return views.size();
        }

        //获取选项卡
        @Override
        public Object instantiateItem(ViewGroup container,int position) {
            View v=views.get(position);
                container.addView(v);
            return v;
        }
        //删除选项卡
        @Override
        public void destroyItem(ViewGroup container,int position,Object object) {
            container.removeView(views.get(position));
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        //获取标题
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
