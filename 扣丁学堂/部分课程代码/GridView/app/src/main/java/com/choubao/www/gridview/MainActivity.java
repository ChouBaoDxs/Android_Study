package com.choubao.www.gridview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * GridView组件：
 * 1、自定义适配器
 * （1）创建一个类，继承BaseAdapter类
 * （2）实现四个方法：
 *          getCount:获取要显示的选项对象总数
 *          getItem:获取选项对象
 *          getItemID:获取选项对象的position
 *          getView:在填充gridView时不断调用该方法,该方法用来为每一个选项生成视图
 */
public class MainActivity extends AppCompatActivity {

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView= (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new MyAdapter(this));

        //设置点击事件
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("parent:"+adapterView);  //GridView
                System.out.println("View:"+view);   //每一个选项的布局
                System.out.println("position:"+i);  //位置
                System.out.println("id:"+l);    //编号id
                TextView tv= (TextView) view.findViewById(R.id.textView);
                Toast.makeText(MainActivity.this,tv.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    //自定义适配器
    static class MyAdapter extends BaseAdapter {

        private int[] images={
                R.mipmap.she1,
                R.mipmap.she2,
                R.mipmap.she3,
                R.mipmap.she4,
                R.mipmap.weixin,
                R.mipmap.she1,
                R.mipmap.she2,
                R.mipmap.she3,
                R.mipmap.she4,
                R.mipmap.weixin
        };

        private String[] names={"she1","she2","she3","she4","weixin","she1","she2","she3","she4","weixin"};

        private Context context;

        public MyAdapter(Context context) {
            this.context=context;
        }
        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int i) {
            return images[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
//            ImageView iv=new ImageView(context);
//            iv.setImageResource(images[i]);
//
//            iv.setMaxHeight(300);
//            iv.setMaxWidth(90);
//            iv.setAdjustViewBounds(true);
//            iv.setScaleType(ImageView.ScaleType.FIT_XY);
//
//            return iv;
            LayoutInflater inflater=LayoutInflater.from(context);
            View v=inflater.inflate(R.layout.item,null);
            ImageView iv= (ImageView) v.findViewById(R.id.imageView);
            TextView tv= (TextView) v.findViewById(R.id.textView);

            iv.setImageResource(images[i]);
//            iv.setMaxHeight(300);
//            iv.setMaxWidth(90);
//            iv.setAdjustViewBounds(true);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);

            tv.setText(names[i]);

            return v;
        }
    }
}
