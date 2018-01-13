package com.choubao.www.menus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PopupMenu extends AppCompatActivity implements android.widget.PopupMenu.OnMenuItemClickListener{

    private Button buttonSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_menu);
//        buttonSelect= (Button) findViewById(R.id.button_select);
    }

    //按钮的单击事件
    public void typeSizeClick(View v) {
        //创建弹出式菜单
        android.widget.PopupMenu popupMenu=new android.widget.PopupMenu(this,v);
        MenuInflater inflater=popupMenu.getMenuInflater();
        inflater.inflate(R.menu.popupmenu,popupMenu.getMenu());

        //弹出式菜单的单击事件
        popupMenu.setOnMenuItemClickListener(this);

        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.s_Size:
                Toast.makeText(this,"你选择了S号",Toast.LENGTH_SHORT).show();
                break;
            case R.id.m_Size:
                Toast.makeText(this,"你选择了M号",Toast.LENGTH_SHORT).show();
                break;
            case R.id.l_Size:
                Toast.makeText(this,"你选择了L号",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
