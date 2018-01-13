package com.choubao.www.drawabledemo.CustomDrawable;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Created by choubao on 17/5/20.
 */

public class CircleDrawable extends Drawable {

    private Paint mPaint;
    private int mWidth;
    private Bitmap mBitmap;

    public CircleDrawable(Bitmap bitmap) {
        this.mBitmap = bitmap;
        BitmapShader bitmapShader=new BitmapShader(mBitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);//着色器 完整平铺
        mPaint=new Paint();
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setShader(bitmapShader);//设置着色器
        mWidth=Math.min(mBitmap.getWidth(),mBitmap.getHeight());
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(mWidth/2,mWidth/2,mWidth/2,mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;//让drawable透明度和窗口一样
    }

    //返回drawable实际的高
    @Override
    public int getIntrinsicHeight() {
        return mWidth;
    }
    //返回drawable实际的宽
    @Override
    public int getIntrinsicWidth() {
        return mWidth;
    }
}
