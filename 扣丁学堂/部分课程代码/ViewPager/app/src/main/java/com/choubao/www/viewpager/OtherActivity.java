package com.choubao.www.viewpager;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class OtherActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private ArrayList<View> views = new ArrayList<>();
    private ImageView[] imageViews;
    private int currentIndex = 0;//当前正在显示的选项卡页

//******************设置为首次启动*********************
//    public class MainActivity extends AppCompatActivity {
//        private SharedPreferences sharedPreferences;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_main);
//
//            sharedPreferences = getSharedPreferences("count", MODE_PRIVATE);
//            int count = sharedPreferences.getInt("count", 0);
//            Log.d("print", String.valueOf(count));
//            //判断程序是第几次运行，如果是第一次运行则跳转到引导页面
//            if (count == 0) {
//                Intent intent = new Intent();
//                intent.setClass(getApplicationContext(), FirstStartActivity.class);
//                startActivity(intent);
//                this.finish();
//            }
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            //存入数据
//            editor.putInt("count", ++count);
//            //提交修改
//            editor.commit();
//        }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        initViews();
        initPoint();
    }

    private void initViews() {
        viewPager = (ViewPager) findViewById(R.id.viewPaper);
        views.add(getLayoutInflater().inflate(R.layout.layout1, null));
        views.add(getLayoutInflater().inflate(R.layout.layout2, null));
        views.add(getLayoutInflater().inflate(R.layout.layout3, null));
        views.add(getLayoutInflater().inflate(R.layout.layout4, null));

        viewPager.setOnPageChangeListener(this);
        viewPager.setAdapter(new MyPagerAdapter());
    }

    //初始化LinearLayout里的四个ImageView，并设置第1个圆点为实心
    private void initPoint() {
        LinearLayout point_layout = (LinearLayout) findViewById(R.id.point_layout);
        imageViews = new ImageView[views.size()];
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i] = (ImageView) point_layout.getChildAt(i);
        }

        currentIndex = 0;
        imageViews[currentIndex].setImageResource(R.drawable.touched200x200);
    }

    //设置小圆点的图片，根据选择的Page设置实心
    private void setCurrentPoint(int position) {
        if (currentIndex < 0 || currentIndex == position || currentIndex > imageViews.length - 1) {
            return;
        }
        imageViews[currentIndex].setImageResource(R.drawable.default200x200);
        imageViews[position].setImageResource(R.drawable.touched200x200);
        currentIndex = position;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setCurrentPoint(position);
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
        public Object instantiateItem(ViewGroup container, int position) {
            View v = views.get(position);
            container.addView(v);
            return v;
        }

        //删除选项卡
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
