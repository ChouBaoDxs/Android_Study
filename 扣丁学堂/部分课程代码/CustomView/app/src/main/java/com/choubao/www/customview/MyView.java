package com.choubao.www.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by choubao on 17/3/19.
 */

public class MyView extends View {

    private int textColor;
    private float textSize;
    private String text;
    private Paint paint;//画笔

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        //获取配置文件中的属性值
        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.MyView);
        textColor=array.getColor(R.styleable.MyView_textColor,0xFFFFFF);
        textSize=array.getDimension(R.styleable.MyView_textSize,24);
        text=array.getString(R.styleable.MyView_text);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //视图的绘制事件
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(textColor);
        paint.setTextSize(textSize);

        canvas.drawText(text,50,50,paint);
    }
}
