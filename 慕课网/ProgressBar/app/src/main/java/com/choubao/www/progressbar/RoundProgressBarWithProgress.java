package com.choubao.www.progressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * Created by choubao on 17/5/26.
 */

public class RoundProgressBarWithProgress extends HorizontalProgressbarWithProgress {

    private int mRadius=dp2px(30);
    private int mMaxPaintWidth;

    public RoundProgressBarWithProgress(Context context) {
        this(context,null);
    }

    public RoundProgressBarWithProgress(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundProgressBarWithProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mReachHeight= (int) (mUnReachHeight*2.0f);//两倍宽度
        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.RoundProgressBarWithProgress);
        mRadius= (int) ta.getDimension(R.styleable.RoundProgressBarWithProgress_radius,mRadius);
        ta.recycle();

        mPaint.setStyle(Paint.Style.STROKE);//空心
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setDither(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mMaxPaintWidth=Math.max(mReachHeight,mUnReachHeight);
        //默认四个padding一致
        int expect=mRadius*2+mMaxPaintWidth+getPaddingLeft()+getPaddingRight();

        int width=resolveSize(expect,widthMeasureSpec);
        int height=resolveSize(expect,heightMeasureSpec);

        int realWidth=Math.min(width,height);
        mRadius=(realWidth-getPaddingRight()-getPaddingLeft()-mMaxPaintWidth)/2;
        setMeasuredDimension(realWidth,realWidth);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        String text=getProgress()+"%";
        float textWidth=mPaint.measureText(text);
        float textHeight=(mPaint.descent()+mPaint.ascent())/2;
        canvas.save();

        canvas.translate(getPaddingLeft()+mMaxPaintWidth/2,getPaddingTop()+mMaxPaintWidth/2);
        mPaint.setStyle(Paint.Style.STROKE);
        //draw unreach bar
        mPaint.setColor(mUnReachColor);
        mPaint.setStrokeWidth(mUnReachHeight);
        canvas.drawCircle(mRadius,mRadius,mRadius,mPaint);
        //draw reach bar
        mPaint.setColor(mReachColor);
        mPaint.setStrokeWidth(mReachHeight);
        float sweepAngle=getProgress()*1.0f/getMax()*360;//弧度
        canvas.drawArc(new RectF(0,0,mRadius*2,mRadius*2),0,sweepAngle,false,mPaint);
        //draw text
        mPaint.setColor(mTextColor);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText(text,mRadius-textWidth/2,mRadius-textHeight,mPaint);
        canvas.restore();;
    }
}
