package com.choubao.www.weixin6_0;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private List<Fragment> mTabs=new ArrayList<>();
    private String[] mTitles=new String[]{
            "First Fragment","Second Fragment","Third Fragment","Forth Fragment",
    };

    private FragmentPagerAdapter mAdapter;

    private List<ChangeColorIconWithText> mTabIndicators=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setOverflowButtonAlways();

        initView();
        initDatas();
        mViewPager.setAdapter(mAdapter);
        initEvent();
    }

    //初始化所有时间
    private void initEvent() {
        mViewPager.setOnPageChangeListener(this);

    }

    private void initDatas() {
        for (String title : mTitles) {
            TabFragment tabFragment=new TabFragment();
            Bundle bundle=new Bundle();
            bundle.putString(TabFragment.TITLE,title);
            tabFragment.setArguments(bundle);
            mTabs.add(tabFragment);
        }

        mAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mTabs.get(position);
            }

            @Override
            public int getCount() {
                return mTabs.size();
            }
        };
    }

    private void initView() {
        mViewPager= (ViewPager) findViewById(R.id.id_viewpager);
        ChangeColorIconWithText one= (ChangeColorIconWithText) findViewById(R.id.id_indicator_one);
        mTabIndicators.add(one);
        ChangeColorIconWithText two= (ChangeColorIconWithText) findViewById(R.id.id_indicator_two);
        mTabIndicators.add(two);
        ChangeColorIconWithText three= (ChangeColorIconWithText) findViewById(R.id.id_indicator_three);
        mTabIndicators.add(three);
        ChangeColorIconWithText four= (ChangeColorIconWithText) findViewById(R.id.id_indicator_four);
        mTabIndicators.add(four);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);

        one.setIconAlpha((float) 1.0);//第一个是绿色
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        restOtherTabs();
        switch(v.getId()){
            case R.id.id_indicator_one:
                mTabIndicators.get(0).setIconAlpha((float) 1.0);
                mViewPager.setCurrentItem(0,false);//false表示把切换的动画效果去掉
                break;
            case R.id.id_indicator_two:
                mTabIndicators.get(1).setIconAlpha((float) 1.0);
                mViewPager.setCurrentItem(1,false);//false表示把切换的动画效果去掉
                break;
            case R.id.id_indicator_three:
                mTabIndicators.get(2).setIconAlpha((float) 1.0);
                mViewPager.setCurrentItem(2,false);//false表示把切换的动画效果去掉
                break;
            case R.id.id_indicator_four:
                mTabIndicators.get(3).setIconAlpha((float) 1.0);
                mViewPager.setCurrentItem(3,false);//false表示把切换的动画效果去掉
                break;
        }
    }

    //重置其它的TabIndicator的颜色
    private void restOtherTabs() {
        for(int i=0;i<mTabIndicators.size();i++) {
            mTabIndicators.get(i).setIconAlpha(0);
        }
    }

    //positionOffset滚动的进度 0~1
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset > 0) {
            ChangeColorIconWithText left=mTabIndicators.get(position);
            ChangeColorIconWithText right=mTabIndicators.get(position+1);
            left.setIconAlpha(1-positionOffset);
            right.setIconAlpha(positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


 /*   private void setOverflowButtonAlways() {
        ViewConfiguration configuration=ViewConfiguration.get(this);
        try {
            Field menuKey=ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            menuKey.setAccessible(true);
            menuKey.setBoolean(configuration,false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }*/

    //设置menu显示icon
 /*   @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if(menu.getClass().getSimpleName().equals("MenuBuilder")){  //反射
                try {
                    Method m=menu.getClass().getDeclaredMethod("setOptionalIconsVisible",Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu,true);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }*/
}
