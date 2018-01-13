package com.choubao.www.canvas_drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by choubao on 17/4/29.
 */

public class MyImageView extends View {
    public MyImageView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p=new Paint();
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.she1);
        canvas.drawBitmap(bitmap,0,0,p);
    }
}
