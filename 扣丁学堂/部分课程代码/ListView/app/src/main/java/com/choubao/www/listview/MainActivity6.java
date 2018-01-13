package com.choubao.www.listview;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Vector;

public class MainActivity6 extends AppCompatActivity implements AbsListView.OnScrollListener{

    private ListView listView;
    private Vector<News> news=new Vector<>();
    private MyAdapter myAdapter;
    private static final int DATE_UPDATE=0x1;//数据更新完成后的标记

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        listView= (ListView) findViewById(R.id.listView5);
        //设置滚动监听
        listView.setOnScrollListener(this);
        View footerView=getLayoutInflater().inflate(R.layout.loading,null);
        listView.addFooterView(footerView);
        initData();
        myAdapter=new MyAdapter();
        listView.setAdapter(myAdapter);
    }

    private int index=1;
    //初始化数据
    private void initData() {
        for (int i=0;i<12;i++) {
            News n=new News();
            n.title="title--"+index;
            n.content="content--"+index;
            index++;
            news.add(n);
        }
    }


    private int visibleLastIndex;//用来可显示的最后一条数据的索引
    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        //如果已经显示到最后一行且滑动停止
        if (myAdapter.getCount()==visibleLastIndex && i == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
            new LoadDataThread().start();
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        visibleLastIndex=i+i1-1;//这个1是那个"玩命加载中..."
    }

    //线程之间通讯的机制
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case DATE_UPDATE:
                    myAdapter.notifyDataSetChanged();
                    break;
                default :

                    break;
            }
        }

    };

    //线程类:用于模拟加载数据
    class LoadDataThread extends Thread {
        @Override
        public void run() {
            initData();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            myAdapter.notifyDataSetChanged();
            //通过handler给主线程发送一个消息标记
            handler.sendEmptyMessage(DATE_UPDATE);
        }
    }

    //自定义适配器填充数据
    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return news.size();
        }

        @Override
        public Object getItem(int i) {
            return news.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ViewHolder vh;
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.custom_list_item_activity6, null);
                vh = new ViewHolder();
                vh.tv_title = (TextView) view.findViewById(R.id.textView_title1);
                vh.tv_content = (TextView) view.findViewById(R.id.textView_content);
                view.setTag(vh);
            } else {
                vh= (ViewHolder) view.getTag();
            }
            News n=news.get(i);
            vh.tv_title.setText(n.title);
            vh.tv_content.setText(n.content);

            return view;
        }

        class ViewHolder {
            TextView tv_title;
            TextView tv_content;
        }

    }

}
