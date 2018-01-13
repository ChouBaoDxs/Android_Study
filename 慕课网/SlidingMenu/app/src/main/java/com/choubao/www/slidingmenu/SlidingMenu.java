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

public class SlidingMenu extends HorizontalScrollView {

    private LinearLayout mWapper;
    private ViewGroup mMenu;
    private ViewGroup mContent;
    private int mScreenWidth;

    private int mMenuWidth;

    private int mMenuRightPadding=100;//单位dp

    private boolean once=false;

    //未使用自定义属性时会调用这个
    public SlidingMenu(Context context, AttributeSet attrs) {
        //super(context, attrs);
        this(context,attrs,0);
    }

    //当使用了自定义属性时，会调用此构造方法
    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //获取我们自定义的属性    //没效果啊
       /* TypedArray a=context.getTheme().obtainStyledAttributes(attrs,R.styleable.SlidingMenu,defStyleAttr,0);
        int indexCount=a.getIndexCount();
        for (int i=0;i<indexCount;i++) {
            int attr=a.getIndex(i);
            switch(attr){
                case R.styleable.SlidingMenu_rightRadding:
                    //第一个参数是传进来的，第二个是默认
                    mMenuRightPadding=a.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,context.getResources().getDisplayMetrics()));
                    break;
            }
        }
        a.recycle();*/

        WindowManager wm= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetris=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetris);
        mScreenWidth=outMetris.widthPixels;

        //把dp转换为px
        mMenuRightPadding= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100,context.getResources().getDisplayMetrics());
    }

    public SlidingMenu(Context context) {
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
}
