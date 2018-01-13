package com.choubao.www.guaguaka;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by choubao on 17/5/2.
 */

public class GuaGuaKa extends View {
    private Paint mOutterPaint;
    private Path mPath;
    private Canvas mCanvas;
    private Bitmap mBitmap;

    private int mLastX;
    private int mLastY;

    private Bitmap mOutterBitmap;

    //-------------------------------------
    private Bitmap bitmap;

    private String mText;
    private Paint mBackPaint;
    //记录刮奖信息文本的宽和高
    private Rect mTextBound;
    private int mTextSize;
    private int mTextColor;

    //判断遮盖层区域消除是否达到阈值   //volatile是为了让子线程对这个布尔值进行改变时，保持其可见性
    private volatile boolean mComplete=false;

    //刮刮卡刮完的回调
    public interface OnGuaGuaKaCompleteListener {
        void complete();
    }

    private OnGuaGuaKaCompleteListener  mListener;

    public void setOnGuaGuaKaCompleteListener(OnGuaGuaKaCompleteListener mListener) {
        this.mListener = mListener;
    }

    public GuaGuaKa(Context context) {
        super(context);
        System.out.println("调用了一个参数的方法");
        init();
    }

    public GuaGuaKa(Context context, AttributeSet attrs) {
        super(context, attrs);
        System.out.println("调用了二个参数的方法");
        init();
        TypedArray a = null;
        try
        {
            a = context.getTheme().obtainStyledAttributes(attrs,
                    R.styleable.GuaGuaKa, 0, 0);
            int n = a.getIndexCount();

            for (int i = 0; i < n; i++)
            {
                int attr = a.getIndex(i);

                switch (attr)
                {
                    case R.styleable.GuaGuaKa_text:
                        mText = a.getString(attr);
                        break;

                    case R.styleable.GuaGuaKa_textSize:     //把sp设置为像素值
                        mTextSize = (int) a.getDimension(attr, TypedValue
                                .applyDimension(TypedValue.COMPLEX_UNIT_SP, 22,
                                        getResources().getDisplayMetrics()));
                        break;
                    case R.styleable.GuaGuaKa_textColor:
                        mTextColor = a.getColor(attr, 0x000000);
                        break;
                }

            }

        } finally
        {
            if (a != null)
                a.recycle();
        }
    }

    public GuaGuaKa(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        System.out.println("调用了三个参数的方法");
        TypedArray a = null;
        try
        {
            a = context.getTheme().obtainStyledAttributes(attrs,
                    R.styleable.GuaGuaKa, defStyleAttr, 0);

            int n = a.getIndexCount();

            for (int i = 0; i < n; i++)
            {
                int attr = a.getIndex(i);

                switch (attr)
                {
                    case R.styleable.GuaGuaKa_text:
                        mText = a.getString(attr);
                        break;

                    case R.styleable.GuaGuaKa_textSize:     //把sp设置为像素值
                        mTextSize = (int) a.getDimension(attr, TypedValue
                                .applyDimension(TypedValue.COMPLEX_UNIT_SP, 22,
                                        getResources().getDisplayMetrics()));
                        break;
                    case R.styleable.GuaGuaKa_textColor:
                        mTextColor = a.getColor(attr, 0x000000);
                        break;
                }

            }

        } finally
        {
            if (a != null)
                a.recycle();
        }
    }

    public void setText(String mText) {
        this.mText=mText;
        //获得当前画笔绘制文本的宽和高
        mBackPaint.getTextBounds(mText,0,mText.length(),mTextBound);
        //invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();//获得控件的宽
        int height = getMeasuredHeight();//获得控件的高

        //初始化我们的Bitmap
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);//第三个参数是设置品质
        mCanvas = new Canvas(mBitmap);

        //设置绘制path画笔的一些属性
        setupOutPaint();
        setupBackPaint();

//        mCanvas.drawColor(Color.parseColor("#c0c0c0"));//灰色蒙板
        mCanvas.drawRoundRect(new RectF(0,0,width,height),30,30,mOutterPaint);
        mCanvas.drawBitmap(mOutterBitmap,null,new Rect(0,0,width,height),null);
    }

    //设置绘制获奖信息的画笔属性
    private void setupBackPaint() {
//        mBackPaint.setColor(Color.DKGRAY);
        mBackPaint.setColor(mTextColor);
        mBackPaint.setStyle(Paint.Style.FILL);
        mBackPaint.setTextSize(mTextSize);
        //获得当前画笔绘制文本的宽和高
        mBackPaint.getTextBounds(mText,0,mText.length(),mTextBound);
    }

    //设置绘制path画笔的一些属性
    private void setupOutPaint() {
//        mOutterPaint.setColor(Color.RED);
        mOutterPaint.setColor(Color.parseColor("#c0c0c0"));
        mOutterPaint.setAntiAlias(true);
        mOutterPaint.setDither(true);
        mOutterPaint.setStrokeJoin(Paint.Join.ROUND);//设置圆角
        mOutterPaint.setStrokeCap(Paint.Cap.ROUND);
        mOutterPaint.setStyle(Paint.Style.FILL);
        mOutterPaint.setStrokeWidth(40);//设置画笔宽度
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                mPath.moveTo(mLastX, mLastY);
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = Math.abs(x - mLastX);
                int dy = Math.abs(y - mLastY);

                //小于3三像素就不操作
                if (dx > 3 || dy > 3) {
                    mPath.lineTo(x, y);
                }

                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                //这里要计算信息区域已经被刮开的像素
                new Thread(mRunnable).start();
                break;
        }

        invalidate();
        return true;
//        return super.onTouchEvent(event);
    }

    private Runnable mRunnable=new Runnable() {
        @Override
        public void run() {
            //获取组件的宽和高
            int w=getWidth();
            int h=getHeight();

            float wipeArea=0;
            float totalArea=w*h;

            Bitmap bitmap=mBitmap;

            int[] mPixels=new int[w*h];
            //获得Bitmap上所有的像素信息
            mBitmap.getPixels(mPixels,0,w,0,0,w,h);

            for(int i=0;i<w;i++) {
                for(int j=0;j<h;j++) {
                    int index=i+j*w;
                    if (mPixels[index] == 0)
                    {
                        wipeArea++;
                    }
                }
            }
            if (wipeArea > 0 && totalArea > 0)
            {
                int percent = (int) (wipeArea * 100 / totalArea);
                //Log.e("TAG", percent + "");
                if (percent > 60)//设置刮开区域的阈值
                {
                    // 清除掉图层区域
                    mComplete = true;
                    postInvalidate();
                }
            }
        }
    };

    @Override
    protected void onDraw(Canvas canvas) {

//        canvas.drawBitmap(bitmap, 0, 0, null);//绘制背景图片

        canvas.drawText(mText,getWidth()/2-mTextBound.width()/2,getHeight()/2+mTextBound.height()/2,mBackPaint);

        if (!mComplete) {
            drawPath();
            canvas.drawBitmap(mBitmap, 0, 0, null);
        }

        if (mComplete)
        {
            if (mListener != null)
            {
                mListener.complete();
            }
        }
    }

    private void drawPath() {
        mOutterPaint.setStyle(Paint.Style.STROKE);
        mOutterPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));//显示下层绘制图片
        mCanvas.drawPath(mPath, mOutterPaint);
    }

    //进行一些初始化工作
    public void init() {
        mOutterPaint = new Paint();
        mPath = new Path();

        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.t2);//这张图片用不到了
        mOutterBitmap=BitmapFactory.decodeResource(getResources(), R.mipmap.fg_guaguaka);

        mTextColor=Color.parseColor("#000000");
        mTextBound = new Rect();
        mBackPaint = new Paint();
        mText = "谢谢惠顾";
        mTextSize=30;
    }
}
