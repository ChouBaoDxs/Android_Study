package com.choubao.www.expandablelistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * 可扩展的ListView
 */
public class MainActivity extends AppCompatActivity {

    private ExpandableListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView= (ExpandableListView) findViewById(R.id.expandableListView);
        listView.setAdapter(new MyExpandableAdapter());

        //事件监听
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                TextView tv= (TextView) view.findViewById(R.id.title1);
                Toast.makeText(MainActivity.this,tv.getText().toString(),Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    //适配器
    class MyExpandableAdapter extends BaseExpandableListAdapter {

        private String[] groups={"自己人","同学"};
        private String[][] childs={{"小叔","二叔","姨婆","四叔"},{"SHE1","SHE2","SHE3","SHE4"}};
        private int[][] icons={{R.drawable.weixin,R.drawable.weixin,R.drawable.weixin,R.drawable.weixin},
                {R.drawable.she1,R.drawable.she2,R.drawable.she3,R.drawable.she4}};

        @Override
        public int getGroupCount() {
            return groups.length;
        }

        @Override
        public int getChildrenCount(int i) {
            return childs[i].length;
        }

        @Override
        public Object getGroup(int i) {
            return groups[i];
        }

        @Override
        public Object getChild(int i, int i1) {
            return childs[i][i1];
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int i, boolean b, View convertView, ViewGroup viewGroup) {
            if (convertView == null) {
                convertView=getLayoutInflater().inflate(R.layout.group_layout,null);
            }
            ImageView icon= (ImageView) convertView.findViewById(R.id.icon);
            TextView title= (TextView) convertView.findViewById(R.id.title);
//            icon.setImageResource(R.drawable.she1);
            title.setText(groups[i]);
            return convertView;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View convertView, ViewGroup viewGroup) {
            if (convertView == null) {
                convertView=getLayoutInflater().inflate(R.layout.childs_layout,null);//这里的group_layout可以改成其它的
            }
            ImageView icon= (ImageView) convertView.findViewById(R.id.icon);
            TextView title= (TextView) convertView.findViewById(R.id.title1);
            icon.setImageResource(icons[i][i1]);
            title.setText(childs[i][i1]);
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }
}
