package com.choubao.www.menus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //用于创建选项菜单的事件方法，在打开界面时会被自动调用
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //getMenuInflater()获取一个菜单填充器
        //最好使用这种配置文件的方式设置菜单
        getMenuInflater().inflate(R.menu.menu,menu);

        //添加菜单项(组ID,当前选项ID,排序,标题)
//        menu.add(0,100,1,"游戏设置");
//        menu.add(0,200,2,"开始游戏");
//        menu.add(0,300,3,"退出游戏");
//        menu.add(0,400,4,"关闭程序");

        return super.onCreateOptionsMenu(menu);
    }

    //菜单选项的单击事件处理方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();// 100 200 300
//        switch(id){
//            case 100:
//                Toast.makeText(this,"正在打开设置游戏界面...",Toast.LENGTH_SHORT).show();
//                break;
//            case 200:
//                Toast.makeText(this,"正在启动游戏...",Toast.LENGTH_SHORT).show();
//                break;
//            case 300:
//                Toast.makeText(this,"正在退出游戏...",Toast.LENGTH_SHORT).show();
//                break;
//            case 400:
//                Toast.makeText(this,"正在关闭程序...",Toast.LENGTH_SHORT).show();
//                onDestroy();
//                break;
//        }

        switch(id){
            case R.id.game_set:
                Toast.makeText(this,"正在打开设置游戏界面...",Toast.LENGTH_SHORT).show();
                break;
            case R.id.game_start:
                Toast.makeText(this,"正在启动游戏...",Toast.LENGTH_SHORT).show();
                break;
            case R.id.game_exit:
                Toast.makeText(this,"正在退出游戏...",Toast.LENGTH_SHORT).show();
                break;
            case R.id.start_ContentMenu:
                startActivity(new Intent(MainActivity.this,ContentMenu.class));
                break;
            case R.id.start_PopupMenu:
                startActivity(new Intent(MainActivity.this,PopupMenu.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);
    }
}
