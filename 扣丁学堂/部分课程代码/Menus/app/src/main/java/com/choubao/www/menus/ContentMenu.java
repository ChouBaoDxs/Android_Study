package com.choubao.www.menus;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

//上下文菜单
public class ContentMenu extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_menu);
        tv= (TextView) findViewById(R.id.textView_bg);
        //注册上下文菜单到tv这个组件上
        registerForContextMenu(tv);
    }

    //创建上下文菜单的事件方法
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
//        menu.add();
        getMenuInflater().inflate(R.menu.context_menu,menu);
    }

    //上下文菜单的单击事件

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.red:
                tv.setBackgroundColor(Color.RED);
                break;
            case R.id.blue:
                tv.setBackgroundColor(Color.BLUE);
                break;
            case R.id.green:
                tv.setBackgroundColor(Color.GREEN);
                break;
        }

        return super.onContextItemSelected(item);
    }
}
