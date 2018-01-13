package com.choubao.www.slidingmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created by choubao on 17/5/1.
 */

public class SlidingMenu_ChouTi extends HorizontalScrollView {

    private LinearLayout mWapper;
    private ViewGroup mMenu;
    private ViewGroup mContent;
    private int mScreenWidth;

    private int mMenuWidth;

    private int mMenuRightPadding=100;//单位dp

    private boolean once=false;

    //未使用自定义属性时会调用这个
    public SlidingMenu_ChouTi(Context context, AttributeSet attrs) {
        //super(context, attrs);
        this(context,attrs,0);
    }

    //当使用了自定义属性时，会调用此构造方法
    public SlidingMenu_ChouTi(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        WindowManager wm= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetris=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetris);
        mScreenWidth=outMetris.widthPixels;

        //把dp转换为px
        mMenuRightPadding= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100,context.getResources().getDisplayMetrics());
    }

    public SlidingMenu_ChouTi(Context context) {
        //super(context);
        this(context,null);
    }

    //设置子View的宽和高
    //设置自己的宽和高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!once) {
            mWapper= (LinearLayout) getChildAt(0);
            mMenu= (ViewGroup) mWapper.getChildAt(0);
            mContent= (ViewGroup) mWapper.getChildAt(1);

            mMenuWidth=mMenu.getLayoutParams().width=mScreenWidth-mMenuRightPadding;
            mContent.getLayoutParams().width=mScreenWidth;
            once=true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //通过设置偏移量，将menu隐藏
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo(mMenuWidth,0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action=ev.getAction();

        switch(action){
            case MotionEvent.ACTION_UP:
                //隐藏在左边的宽度
                int scrollX=getScrollX();
                if (scrollX >= mMenuWidth / 2) {
                    this.smoothScrollTo(mMenuWidth, 0);
                    isOpen=false;
                } else {
                    this.smoothScrollTo(0,0);
                    isOpen=true;
                }
                return true;
//            case MotionEvent.ACTION_MOVE: //这里两句可以屏蔽滑动      //子控件请求父不要拦截事件：getParent().requestDisallowInterceptTouchEvent(true);
//                return true;
        }

        return super.onTouchEvent(ev);
    }

    private boolean isOpen;//是否开启了菜单

    //打开菜单
    public void openMenu() {
        if(isOpen)return;
        this.smoothScrollTo(0,0);
        isOpen=true;
    }

    public void closeMenu() {
        if(!isOpen)return;
        this.smoothScrollTo(mMenuWidth,0);
        isOpen=false;
    }

    public void toggle() {
        if (isOpen){
            closeMenu();
        } else {
            openMenu();
        }
    }

    //滚动发生时，都会调用
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //抽屉式菜单就多了下面这行行代码
        //调用属性动画，调用TranslationX
        //mMenu.setTranslationX(l);


        //下面的代码是为了在滑动的同时，改变透明度以及原本显示内容的大小
        /*
            内容区域1.0~0.7的缩放效果
            scale:1.0~0.7
            0.7+0.3*scale

            菜单的偏移量需要修改

            菜单的显示时有缩放以及透明度变化
            缩放:0.7~1.0
                1.0-scale*0.3
            透明度0.6~1.0
                0.6+0.4*(1-scale)*/
        float scale=l*1.0f/mMenuWidth;
        float rightScale=0.7f+0.3f*scale;
        float leftScale=1.0f-0.3f*scale;
        float leftAlpha=0.6f+0.4f*(1-scale);
        //设置menu的动画
        mMenu.setTranslationX(mMenuWidth*scale*0.8f);

        mMenu.setScaleX(leftScale);
        mMenu.setScaleY(leftScale);
        mMenu.setAlpha(leftAlpha);

        //设置content的缩放动画
        mContent.setPivotX(0);
        mContent.setPivotY(mContent.getHeight()/2);
        mContent.setScaleX(rightScale);
        mContent.setScaleY(rightScale);
    }
}
