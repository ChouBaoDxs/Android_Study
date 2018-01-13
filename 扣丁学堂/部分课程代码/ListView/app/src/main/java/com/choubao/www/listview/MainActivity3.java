package com.choubao.www.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity3 extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        listView= (ListView) findViewById(R.id.listView2);

        String[] arr=getResources().getStringArray(R.array.falv);

        //单选模式
//        ArrayAdapter<String> aa=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,arr);
//        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//        listView.setAdapter(aa);

        //多选模式
        ArrayAdapter<String> aa=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,arr);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(aa);
    }
}
