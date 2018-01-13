package com.choubao.www.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity4 extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        listView= (ListView) findViewById(R.id.listView3);

        //准备数据
        HashMap<String,Object> title1=new HashMap<>();
        title1.put("title","title-1");
        title1.put("icon",android.R.drawable.arrow_down_float);
        HashMap<String,Object> title2=new HashMap<>();
        title2.put("title","title-2");
        title2.put("icon",android.R.drawable.arrow_up_float);
        HashMap<String,Object> title3=new HashMap<>();
        title3.put("title","title-3");
        title3.put("icon",android.R.drawable.btn_plus);

        List<Map<String,Object>> list=new ArrayList<>();
        list.add(title1);
        list.add(title2);
        list.add(title3);

        //把数据填充到
        SimpleAdapter sa=new SimpleAdapter(this,list,R.layout.custom_list_item_activity4,new String[]{"title","icon"},new int[]{R.id.textView_title,R.id.imageView_icon});
        listView.setAdapter(sa);
    }
}
