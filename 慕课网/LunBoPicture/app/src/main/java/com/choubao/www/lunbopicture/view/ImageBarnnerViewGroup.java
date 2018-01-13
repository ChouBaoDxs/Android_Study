package com.choubao.www.lunbopicture.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by choubao on 17/5/22.
 */

//该类是实现图片轮播的核心类
public class ImageBarnnerViewGroup extends ViewGroup{

    private int mChildren;//子视图个数
    private int mChildHeight;//子视图高度
    private int mChildWidth;//子视图宽度

    private int x;//第一次按下的点的横坐标，每一次移动过程中，移动之前的位置横坐标
    private int index=0;//代表我们每张图片的索引

    private Scroller mScroller;

    //要想实现图片的单击事件  就是利用一个单击变量开关进行判断，在用户离开屏幕的一瞬间我们去判断变量开关来
    //判断用户是点击还是移动
    private boolean isClick;//true:点击事件  false:非点击事件
    private ImageBarnnerListener mListener;

    public ImageBarnnerListener getListener() {
        return mListener;
    }

    public void setListener(ImageBarnnerListener listener) {
        mListener = listener;
    }

    public interface ImageBarnnerListener{
        void clickImageIndex(int pos);//pos代表当前图片的具体索引值
    }

    //要想实现图片轮播底部圆点以及底部圆点切换功能步骤思路：
    //1.我们需要自定义一个继承自FrameLayout的布局，利用FrameLayout布局的特性，我们就可以实现底部圆点的布局
    //2.我们需要准备底部原点的素材,我们可以利用Drawable的功能区实现一个圆点图片的展示
    //3.我们就需要继承FrameLayout来自定义一个类，在该类的实现过程中，我们去加载自定义的
    //  ImageBarnnerViewGroup核心类和需要实现的底部圆点的布局LinearLayout来实现


    //自动轮播
    /*
    * 采用Timer,TimerTask,Handler三者结合的方式来实现自动轮播
    * 抽出两个方法来控制是否启动自动轮播，我们称之为startAuto(),stopAuto()
    * 那么我们在2个方法的控制过程中，我们实际上希望控制的是自动开启轮播图的开关
    * 那么我们就需要一个变量参数来作为我们自动开启自动轮播图的开关，我们称之为isAuto boolean型：true开启 false关闭
    * */
    private boolean isAuto=true;//默认情况下开启自动轮播
    private Timer timer=new Timer();
    private TimerTask task;
    private Handler autoHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 0://此时需要图片的自动轮播
                    if(++index>=mChildren){//说明是最后一张图片的话，从第一张图片开始重新滑动
                        index=0;
                    }
                    scrollTo(mChildWidth*index,0);/////
                    mImageBarnnerViewGroupChangeListener.selectImage(index);
                    break;
            }
        }
    };

    private void startAuto() {
        isAuto=true;
    }

    private void stopAuto() {
        isAuto=false;
    }


    public ImageBarnnerViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initObj();
    }

    public ImageBarnnerViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initObj();
    }

    public ImageBarnnerViewGroup(Context context) {
        super(context);
        initObj();
    }

    private void initObj(){
        mScroller=new Scroller(getContext());
        task=new TimerTask() {
            @Override
            public void run() {
                if (isAuto) {//轮播图开启着
                    autoHandler.sendEmptyMessage(0);
                }
            }
        };
        timer.schedule(task,100,2000);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(),0);
            invalidate();//重绘
        }
    }

    /*
    * 测量onMeasure->布局onLayout->绘制onDraw
    * */

    //因为我们是自定义的ViewGroup容器，针对其绘制，其实就是对容器内子控件的绘制过程，那么我们只需要调用系统自带的
    //绘制即可，也就是说，对于ViewGroup绘制过程我们不需要再写了
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         *由于我们要实现的是一个ViewGroup的容器，那么，
         *我们就应该需要做的该容器中的所有子视图
         *我们想要测量我们的ViewGroup的宽度和高度，那么我们就必须先要去测量子视图
         * 的宽度和高度之和，才能知道我们的ViewGroup的宽度和高度是多少
         */
        //1.求出子视图的个数
        mChildren = getChildCount();
        if (mChildren == 0) {
            setMeasuredDimension(0, 0);
        } else {
            //2.测量子视图的宽度和高度
            measureChildren(widthMeasureSpec,heightMeasureSpec);
            //此时我们以第一个子视图为基准，也就是说我们的ViewGroup的高度就是我们第一个子视图的高度
            //宽度就是我们第一个子视图的宽度 * 子视图的个数
            View view=getChildAt(0);//因为此时第一个子视图绝对是存在的
            //3.根据子视图的宽度和高度，来求出该ViewGroup的宽度和高度
            mChildHeight = view.getMeasuredHeight();
            mChildWidth = view.getMeasuredWidth();
            int mWidth=view.getMeasuredWidth()*mChildWidth;
            setMeasuredDimension(mWidth, mChildHeight);
        }
    }
    /*
    * 事件传递过程中的调用方法：我们需要调用容器的拦截方法
    * 针对于该方法，我们可以理解为 如果说该方法的返回值为true的时候，那么我们自定义的ViewGroup容器就会处理此次拦截事件
    * 如果说返回值为false时，那么我们自定的ViewGroup容器将不会接受此次事件的处理过程，将会继续向下传递该事件
    * 针对于我们自定义的ViewGroup 我们当然是希望我们的ViewGroup容器处理接收事件，那么我们的返回值就应该是true
    * 如果返回true的话，真正处理该事件的方法是 onTouchEvent方法
    * */

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    //用两种方式来实现轮播图的手动轮播
    //1.利用scrollTo、scrollBy完成轮播图的手动轮播
    //2.利用Scroller对象完成轮播图的手动轮播
    /*
    * 第一：我们在滑动屏幕图片的过程中，其实就是我们自定义ViewGroup的子视图的移动过程，那么我们只需要知道
    * 滑动之前的横坐标和滑动之后的横坐标，此时，我们就可以求出我们次过程中移动的距离，我们在利用scrollBy方法
    * 实现图片的滑动，所以 此时我们需求出2个值：分别是移动之前和移动之后的横坐标值
    *
    * 第二：在我们第一次按下的那一瞬间，此时的移动之前和移动之后的值是相等的，也就是我们此时按下那一瞬间的那一
    * 个点的横坐标的值
    *
    * 第三：我们在不断的滑动过程中，是会不断地调用我们ACTION_MOVE方法，那么我们就应该将移动之前的值和移动之
    * 后的值进行保存，以便我们能够算出滑动的距离
    *
    * 第四：在我们抬起的那一瞬间，我们需要计算出我们此时将要滑动到哪张图片的位置上。
    *
    * 我们此时就需要求出将要滑动到的哪张图的索引值
    * （我们当前ViewGroup的滑动位置+我们的每一张图片的宽度/2）/我们的每一张图片的宽度值
    *
    * 此时我们就可以利用scrollTo方法 滑动到该图片的位置上
    * */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //这里也可以用手势探测 GestureDetector类实现

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN://表示用户按下的一瞬间
                stopAuto();
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();//如果还没执行完，就结束图片的滑动
                }
                isClick=true;
                x= (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE://表示用户按下之后移动时
                int moveX= (int) event.getX();
                int distance=moveX-x;
                scrollBy(-distance,0);
                x=moveX;
                if(Math.abs(distance)>10){
                    isClick=false;
                }
                break;
            case MotionEvent.ACTION_UP://表示的是用户抬起时
                int scrollX=getScrollX();
                index=(scrollX+mChildWidth/2)/mChildWidth;

                if (isClick) {//代表点击事件
                    mListener.clickImageIndex(index);
                } else {
                    if (index < 0) {//说明此时已经滑动到了最左边第一页
                        index=0;
                    } else if (index>mChildren-1) {//说明此时已经滑动到最有最后一张图片
                        index=mChildren-1;
                    }
                    //1.方法一
                    //scrollTo(index*mChildWidth,0);
                    //2.方法二     //第二种方法更舒服
                    int dx=index*mChildWidth-scrollX;
                    mScroller.startScroll(scrollX,0,dx,0);
                    postInvalidate();/////
                    mImageBarnnerViewGroupChangeListener.selectImage(index);
                }
                startAuto();
                break;
        }
        return true;//返回true的目的是告诉ViewGroup容器的父View 我们已经处理好了该事件
    }

    /*
            * changed:当ViewGroup发生改变时为true，没有发生改变为false
            *
            * */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int leftMargin=0;
            for(int i=0;i<mChildren;i++) {
                View view=getChildAt(i);
                view.layout(leftMargin,0,leftMargin+mChildWidth,mChildHeight);
                leftMargin+=mChildWidth;
            }
        }
    }


    private ImageBarnnerViewGroupChangeListener mImageBarnnerViewGroupChangeListener;

    public ImageBarnnerViewGroupChangeListener getImageBarnnerViewGroupChangeListener() {
        return mImageBarnnerViewGroupChangeListener;
    }
    public void setImageBarnnerViewGroupChangeListener(ImageBarnnerViewGroupChangeListener imageBarnnerViewGroupChangeListener) {
        mImageBarnnerViewGroupChangeListener = imageBarnnerViewGroupChangeListener;
    }
    public interface ImageBarnnerViewGroupChangeListener {
        void selectImage(int index);
    }
}
