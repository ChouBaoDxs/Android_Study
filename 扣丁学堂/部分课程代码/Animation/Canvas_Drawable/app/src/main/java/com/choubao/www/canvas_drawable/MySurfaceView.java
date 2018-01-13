package com.choubao.www.canvas_drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by choubao on 17/4/29.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private MyThread thread;

    public MySurfaceView(Context context) {
        super(context);
        holder = this.getHolder();
        holder.addCallback(this);
    }

    class MyThread implements Runnable {
        private SurfaceHolder holder;
        public boolean isRun;

        public MyThread(SurfaceHolder holder) {
            this.holder = holder;
            isRun = true;
        }

        @Override
        public void run() {
            int count = 0;
            Canvas canvas=null;
            while (isRun) {
                try {
                    //同步块
                    synchronized (holder) {
                        canvas = holder.lockCanvas();//锁定画布，这是我要画的
                        canvas.drawColor(Color.BLACK);
                        Paint p = new Paint();
                        p.setColor(Color.RED);
                        p.setStyle(Paint.Style.FILL);
                        p.setAntiAlias(true);
                        canvas.drawRect(10+count*10, 10, 100+count*10, 100, p);
                        p.setTextSize(30);
                        canvas.drawText("当前是第" + (count++) +" 秒", 10, 150, p);
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    holder.unlockCanvasAndPost(canvas);//释放画布并提交
                }
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread=new MyThread(holder);
        thread.isRun=true;
        new Thread(thread).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.isRun=false;
    }
}
