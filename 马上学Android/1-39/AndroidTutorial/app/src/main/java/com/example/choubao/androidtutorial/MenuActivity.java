package com.example.choubao.androidtutorial;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends Activity implements MenuItem.OnMenuItemClickListener{

    private TextView tvContextMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tvContextMenu=(TextView) findViewById(R.id.tvContextMenu);
        registerForContextMenu(tvContextMenu);
        //取消注册
        //unregisterForContextMenu(tvContextMenu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {////函数2
        int id=item.getItemId();
        if(id==R.id.action_settings){
            Toast.makeText(this, "You clicked Setting", Toast.LENGTH_SHORT).show();
            //可以处理单击响应
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuEdit:

                break;
            case R.id.menuDelete:

                break;
            case R.id.menuShare:

                break;
        }
        return super.onContextItemSelected(item);
    }





    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {////函数1
        //可以处理单击响应
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        //可以处理单击响应
        return false; //如果这里是false，则执行完后还会执行上面两个单击处理函数（函数1和函数2，它们两个谁先出现谁执行），如果是true，则不会再执行
    }
}
