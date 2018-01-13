package com.choubao.www.sqlitequerydemo;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.choubao.www.sqlitequerydemo.bean.Person;
import com.choubao.www.sqlitequerydemo.utils.Constant;
import com.choubao.www.sqlitequerydemo.utils.DbManger;
import com.choubao.www.sqlitequerydemo.utils.MySqliteHelper;

import java.util.List;

//数据库查询操作
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MySqliteHelper helper;
    private Button btn_query;
    private Button btn_queryApi;
    private Button btn_startOtherActivity;
    private Button btn_startThreeActivity;

    private Button btn_Adapter;
    private ListView listView;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper= DbManger.getInstance(this);
        btn_query= (Button) findViewById(R.id.btn_query);
        btn_queryApi= (Button) findViewById(R.id.btn_queryApi);
        btn_Adapter= (Button) findViewById(R.id.btn_Adapter);
        btn_startOtherActivity= (Button) findViewById(R.id.btn_startOtherActivity);
        btn_startThreeActivity= (Button) findViewById(R.id.btn_startThreeActivity);

        listView= (ListView) findViewById(R.id.lv);

        btn_query.setOnClickListener(this);
        btn_queryApi.setOnClickListener(this);
        btn_Adapter.setOnClickListener(this);
        btn_startOtherActivity.setOnClickListener(this);
        btn_startThreeActivity.setOnClickListener(this);
    }

    //点击按钮创建数据库并插入数据
    public void createDb(View view) {
        SQLiteDatabase db=helper.getWritableDatabase();
        for(int i=0;i<=30;i++) {
            String sql="insert into "+Constant.TABLE_NAME+" values("+i+",'张三"+i+"',20)";
            try {
                db.execSQL(sql);
            }
            catch (SQLException e){
            }
        }
        db.close();
    }

    //点击按钮查询表中的数据
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_query:
                SQLiteDatabase db=helper.getWritableDatabase();

                String selectSql="select * from "+Constant.TABLE_NAME;
                Cursor cursor=DbManger.selectDataBySql(db,selectSql,null);

                List<Person> list=DbManger.cursorToList(cursor);

                for (Person p : list) {
                    System.out.println(p.toString());
                }

                db.close();
                break;
            case R.id.btn_queryApi:
                db=helper.getWritableDatabase();
                //表名称
                //字段名称(null表示所有)  null 查询所有
                //查询条件  where子句
                //表示查询条件占位符的取值
                //表示分组条件 group by子句
                //表示筛选条件 having子句
                //表示排序条件 order by子句
                cursor=db.query(Constant.TABLE_NAME,null,Constant._ID+">?"
                        ,new String[]{"10"},null,null,Constant._ID+" desc"); //默认是aesc:升序   desc:降序
                list=DbManger.cursorToList(cursor);
                for (Person p : list) {
                    System.out.println(p.toString());
                }
                db.close();
                break;
            case R.id.btn_Adapter:
                //String path=getApplicationContext()+"/databases/"+"info.db";
                String path= getApplicationContext().getDatabasePath("info.db").getPath();
                System.out.println(path);
                //String path:数据库的存放路径
                //CursorFactory factory:游标工厂
                //int flags:表示打开数据库的操作模式
                sqLiteDatabase=SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READONLY);
                //1.获取数据源
                Cursor cursor_btn_Adapter=sqLiteDatabase.rawQuery("select * from "+Constant.TABLE_NAME,null);
                //2.将数据源数据加载到适配器中
                /*SimpleCursorAdapter(Context context, int layout, Cursor c, String[] from,int[] to, int flags)
                Context context 上下文对象
                int layout 表示适配器控件中每项item的布局id
                Cursor c 表示Cursor数据源
                String[] from 表示cursor中数据表字段的数组
                int[] to 表示展示字段对应值得控件资源id
                int flags 设置适配器的标记*/
                SimpleCursorAdapter adapter=new SimpleCursorAdapter(this,R.layout.item_layout,cursor_btn_Adapter,
                        new String[]{Constant._ID,Constant.NAME,Constant.AGE},
                        new int[]{R.id.tv_id,R.id.tv_name,R.id.tv_age},
                        SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                //3.将适配器的数据加载到控件
                listView.setAdapter(adapter);
                sqLiteDatabase.close();
                break;
            case R.id.btn_startOtherActivity:
                startActivity(new Intent(MainActivity.this,CursorAdapterActivity.class));
                break;
            case R.id.btn_startThreeActivity:
                startActivity(new Intent(MainActivity.this,TransationActivity.class));
                break;
        }
    }

}
