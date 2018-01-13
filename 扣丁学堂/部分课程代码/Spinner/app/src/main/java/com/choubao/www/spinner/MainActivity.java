package com.choubao.www.spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner_shijuan;

    private Spinner spinner_falv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner_shijuan= (Spinner) findViewById(R.id.spinner_shijuan);

        String[] shijuan={"卷一","卷二","卷三","卷四"};

        //方式一：
        //用来创建一个数组适配器（上下文,下拉列表布局文件,显示下拉选项的组件ID,数据）
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,android.R.id.text1,shijuan);

        //通过适配器进行数据的绑定
        spinner_shijuan.setAdapter(adapter);


        spinner_falv2= (Spinner) findViewById(R.id.spinner_falv2);

        //方式二：
        //(上下文,数据,下拉列表布局文件)
        ArrayAdapter adapter2=ArrayAdapter.createFromResource(this,R.array.falv,android.R.layout.simple_spinner_dropdown_item);
//        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //通过适配器进行数据的绑定
        spinner_falv2.setAdapter(adapter2);

    }
}
