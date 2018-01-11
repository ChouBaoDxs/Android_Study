package com.choubao.www.gridlayoutdemo;

import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.List;

import static com.choubao.www.gridlayoutdemo.MainActivity.getDragEventAction;

/**
 * Created by choubao on 17/5/20.
 * 1.定义出一个setItem方法，动态地将每个频道的标题数据集合传递进来后，按照数据进行展示即可
 * 2.需要定义出一个方法setAllowDrag，控制控件是否可以进行拖拽操作
 */

public class DragedGridLayout extends GridLayout {
    private  static final int columnCount = 4;//列数
    private boolean allowDrag;//标识当前控件是否可以进行拖拽操作

    public DragedGridLayout(Context context) {
        this(context,null);
    }

    public DragedGridLayout(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public DragedGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //初始化方法：
    private void init() {
        this.setColumnCount(columnCount);                   //android:columnCount="4"
        this.setLayoutTransition(new LayoutTransition());   //android:animateLayoutChanges="true"
    }

    public void setItem(List<String> items) {
        for (String item : items) {
            addItem(item);
        }
    }

    private void addItem(String item) {
        TextView tv=newItemView();
        tv.setText(item);
        this.addView(tv);
    }

    private TextView newItemView() {
        TextView tv=new TextView(getContext());
        tv.setBackgroundResource(R.drawable.selector_tv_bg);
        int margin=5;
        GridLayout.LayoutParams layoutParams=new GridLayout.LayoutParams();//创建出当前的布局参数
        layoutParams.width=getResources().getDisplayMetrics().widthPixels/4-2*margin;//得到屏幕的像素宽 还要除以列数
        layoutParams.height=GridLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.setMargins(margin,margin,margin,margin);//设置各个tv之间的间距
        //将参数设置给tv
        tv.setLayoutParams(layoutParams);
        //设置文本内容居中
        tv.setGravity(Gravity.CENTER);

        if (this.allowDrag) {
            tv.setOnLongClickListener(olcl);
        } else {
            tv.setOnLongClickListener(null);
        }

        return tv;
    }

    public void setAllowDrag(boolean allowDrag) {
        this.allowDrag=allowDrag;
        if (this.allowDrag) {
            this.setOnDragListener(odl);
        }else{
            this.setOnDragListener(null);
        }
    }

    //GrifLayout里的拖拽监听
    private View.OnDragListener odl=new View.OnDragListener() {
        //这个v就是mGridLayout，event是拖拽事件
        @Override
        public boolean onDrag(View v, DragEvent event) {
            String dragEventAction = getDragEventAction(event);
            switch(event.getAction()){
                //当拖动事件开始时，创建出与子控件对应的矩形数组
                case DragEvent.ACTION_DRAG_STARTED:
                    initRects();
                    break;
                //手指移动时，实时判断触摸点是否进入了某一子控件
                case DragEvent.ACTION_DRAG_LOCATION:
                    int touchIndex=getTouchIndex(event);
                    //说明触摸点进入了某一子控件,判断被拖拽的视图与进入的子控件对象不是同一个的时候才进行添加删除操作
                    if(touchIndex>-1&&dragedView!=null&&dragedView!=DragedGridLayout.this.getChildAt(touchIndex)){
                        DragedGridLayout.this.removeView(dragedView);//先删除
                        DragedGridLayout.this.addView(dragedView,touchIndex);//再加入
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //拖拽事件结束后，让被拖拽的view重新可用
                    if(dragedView!=null)dragedView.setEnabled(true);
                    break;
            }
            return true;
        }
    };

    //创建出与子控件对应的矩形数组
    private Rect[] mRects;
    private void initRects() {
        mRects=new Rect[this.getChildCount()];
        for (int i = 0; i < this.getChildCount(); i++) {
            View childView=this.getChildAt(i);
            //创建与每个子控件对应的矩形对象
            Rect rect=new Rect(childView.getLeft(),childView.getTop(),childView.getRight(),childView.getBottom());
            mRects[i]=rect;
        }
    }

    //手指移动时，实时判断触摸点是否进入了某一子控件
    private int getTouchIndex(DragEvent event) {
        //遍历矩形数组，如果包含了当前的触摸点，返回索引即可
        for (int i = 0; i < mRects.length; i++) {
            Rect rect = mRects[i];
            if(rect.contains((int)event.getX(),(int)event.getY())){
                return i;
            }
        }
        return -1;
    }

    private View dragedView;//被拖拽的视图
    //TextView的长按监听
    private View.OnLongClickListener olcl=new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            //长按时，开始一个拖拽操作，要显示出阴影
            //被拖拽的视图就是这里的v
            dragedView = v;
            v.startDrag(null,new View.DragShadowBuilder(v),null,0);//v.startDragAndDrop()一样的
            v.setEnabled(false);
            return false;
        }
    };


}
