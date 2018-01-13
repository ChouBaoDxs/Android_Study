package com.choubao.www.touchripple.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by choubao on 17/4/26.
 */

public class RippleButton extends Button {

    private RippleDrawable mRippleDrawable;
    public RippleButton(Context context) {
        this(context,null);
    }

    public RippleButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RippleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRippleDrawable=new RippleDrawable();
        //设置刷新接口，View中已经实现
        mRippleDrawable.setCallback(this);
//        setBackgroundDrawable(mRippleDrawable);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //设置位置的刷新的区域
        mRippleDrawable.setBounds(0,0,getWidth(),getHeight());
    }

    @Override
    protected boolean verifyDrawable(Drawable who) {
        //验证Drawable是否OK
        return who==mRippleDrawable||super.verifyDrawable(who);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制自己的Drawable
        mRippleDrawable.draw(canvas);
        super.onDraw(canvas);
        //invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //invalidate();
        mRippleDrawable.onTouch(event);
        super.onTouchEvent(event);
        return true;
    }

}
