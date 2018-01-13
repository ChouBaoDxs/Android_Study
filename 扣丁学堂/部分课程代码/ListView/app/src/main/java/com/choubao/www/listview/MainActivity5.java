package com.choubao.www.listview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 *
 * 使用自定义适配器填充ListView
 */

public class MainActivity5 extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        listView= (ListView) findViewById(R.id.listView4);
        listView.setAdapter(new MyAdapter(this));
    }

    static class MyAdapter extends BaseAdapter {
        private String[] titles={"title-1","title-2","title-3","title-4","title-5",
                "title-6","title-7","title-8","title-9","title-10",
                "title-11","title-12","title-13","title-14","title-15"
        };
        private int[] icons={
                android.R.drawable.ic_input_add,
                android.R.drawable.ic_delete,
                android.R.drawable.ic_dialog_email,
                android.R.drawable.ic_dialog_info,
                android.R.drawable.ic_dialog_map,
                android.R.drawable.ic_input_add,
                android.R.drawable.ic_delete,
                android.R.drawable.ic_dialog_email,
                android.R.drawable.ic_dialog_info,
                android.R.drawable.ic_dialog_map,
                android.R.drawable.ic_input_add,
                android.R.drawable.ic_delete,
                android.R.drawable.ic_dialog_email,
                android.R.drawable.ic_dialog_info,
                android.R.drawable.ic_dialog_map
        };

        private Context context;
        public MyAdapter(Context context) {
            this.context=context;
        }
        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Object getItem(int i) {
            return titles[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ViewHolder vh;
            //防止创建过多的对象
            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                view = inflater.inflate(R.layout.custom_list_item_activity5, null);

                vh = new ViewHolder();
                vh.tv = (TextView) view.findViewById(R.id.textView_title_s);
                vh.iv = (ImageView) view.findViewById(R.id.imageView_icon_s);
                view.setTag(vh);
            } else {
                vh= (ViewHolder) view.getTag();
            }

            vh.tv.setText(titles[i]);
            vh.iv.setImageResource(icons[i]);


            //实例化一个布局文件
//            System.out.println("converView:"+view);
//            System.out.println(i+"---"+view);

//            TextView tv= (TextView) view.findViewById(R.id.textView_title_s);
//            ImageView iv= (ImageView) view.findViewById(R.id.imageView_icon_s);

//            tv.setText(titles[i]);
//            iv.setImageResource(icons[i]);
            return view;
        }

        //用于保存第一次查找的组件，避免下次重复查找
        static class ViewHolder {
            ImageView iv;
            TextView tv;
        }
    }

}
