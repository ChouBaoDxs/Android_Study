package com.choubao.www.gridlayoutdemo;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridLayout mGridLayout;
    private int index=0;
    private View dragedView;
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
    //GrifLayout里的拖拽监听
    private View.OnDragListener odl=new View.OnDragListener() {
        //这个v就是mGridLayout，event是拖拽事件
        @Override
        public boolean onDrag(View v, DragEvent event) {
            String dragEventAction = getDragEventAction(event);
//            Rect rect=new Rect();
//            rect.contains();
            switch(event.getAction()){
                //当拖动事件开始时，创建出与子控件对应的矩形数组
                case DragEvent.ACTION_DRAG_STARTED:
                    initRects();
                    break;
                //手指移动时，实时判断触摸点是否进入了某一子控件
                case DragEvent.ACTION_DRAG_LOCATION:
                    int touchIndex=getTouchIndex(event);
                    //说明触摸点进入了某一子控件,判断被拖拽的视图与进入的子控件对象不是同一个的时候才进行添加删除操作
                    if(touchIndex>-1&&dragedView!=null&&dragedView!=mGridLayout.getChildAt(touchIndex)){
                        mGridLayout.removeView(dragedView);//先删除
                        mGridLayout.addView(dragedView,touchIndex);//再加入
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

    //创建出与子控件对应的矩形数组
    private Rect[] mRects;
    private void initRects() {
        mRects=new Rect[mGridLayout.getChildCount()];
        for (int i = 0; i < mGridLayout.getChildCount(); i++) {
            View childView=mGridLayout.getChildAt(i);
            //创建与每个子控件对应的矩形对象
            Rect rect=new Rect(childView.getLeft(),childView.getTop(),childView.getRight(),childView.getBottom());
            mRects[i]=rect;
        }
    }

    //SparseArray<String> 相当于hashmap，但是更搞笑，谷歌官方推荐
    static SparseArray<String> dragEventType=new SparseArray<>();
    //静态代码块，类一被创建就会执行里面的代码
    static {
        dragEventType.put(DragEvent.ACTION_DRAG_STARTED,"STARTED");     //拖拽开始时执行一次     可以拿到x y
        dragEventType.put(DragEvent.ACTION_DRAG_ENDED,"ENDED");         //当拖拽事件结束，手指抬起时执行一次
        dragEventType.put(DragEvent.ACTION_DRAG_ENTERED,"ENTERED");     //当手指进入设置了拖拽监听的控件范围时瞬间执行一次
        dragEventType.put(DragEvent.ACTION_DRAG_EXITED,"EXITED");       //当手指离开设置了拖拽监听的控件范围时瞬间执行一次
        dragEventType.put(DragEvent.ACTION_DRAG_LOCATION,"LOCATION");   //当手机在拖拽监听的控件方位内移动时，不断执行    可以拿到x y
        dragEventType.put(DragEvent.ACTION_DROP,"DROP");                //当手指在设置了拖拽监听的控件范围内抬起时，执行一次     可以拿到x y
    }
    public static String getDragEventAction(DragEvent de) {
        return dragEventType.get(de.getAction());
    }

    private DragedGridLayout mDragedGridLayout;
    private DragedGridLayout mDragedGridLayout2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridLayout= (GridLayout) findViewById(R.id.griflayout);
        //mGridLayout.setColumnCount(4);//设置4列
        mGridLayout.setOnDragListener(odl);

        //获取自定义控件
        mDragedGridLayout = (DragedGridLayout) findViewById(R.id.dragedgridlayout);
        //定义出我们自己的频道数据
        List<String> titles=new ArrayList<>();
        titles.add("上海站");
        titles.add("昆山南站");
        titles.add("苏州站");
        titles.add("无锡站");
        titles.add("常州站");
        titles.add("丹阳站");
        titles.add("镇江站");
        titles.add("南京站");
        mDragedGridLayout.setAllowDrag(true);//设置可以拖拽
        mDragedGridLayout.setItem(titles);

        //第二个自定义控件
        mDragedGridLayout2 = (DragedGridLayout) findViewById(R.id.hidedragedgridlayout);
        //定义出我们自己的频道数据
        List<String> titles2=new ArrayList<>();
        titles2.add("北京站");
        titles2.add("广州站");
        titles2.add("深圳站");
        titles2.add("杭州站");
        titles2.add("武汉站");
        titles2.add("郑州站");
        titles2.add("合肥站");
        titles2.add("成都站");
        mDragedGridLayout2.setAllowDrag(false);//设置可以拖拽
        mDragedGridLayout2.setItem(titles2);
    }

    public void addItem(View view) {
        //添加Item到GridLayout里
        TextView tv=new TextView(MainActivity.this);
        tv.setText(index+"");
//        tv.setBackgroundResource(R.drawable.shape_tv_normal);
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

        index++;
        tv.setOnLongClickListener(olcl);

        mGridLayout.addView(tv,0);//都放到第一个位置
    }
}
