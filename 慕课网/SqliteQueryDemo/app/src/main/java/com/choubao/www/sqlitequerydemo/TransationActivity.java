package com.choubao.www.sqlitequerydemo;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.choubao.www.sqlitequerydemo.adapter.MyBaseAdapter;
import com.choubao.www.sqlitequerydemo.bean.Person;
import com.choubao.www.sqlitequerydemo.utils.Constant;
import com.choubao.www.sqlitequerydemo.utils.DbManger;
import com.choubao.www.sqlitequerydemo.utils.MySqliteHelper;

import java.util.List;

public class TransationActivity extends AppCompatActivity {

    private MySqliteHelper helper;
    private ListView lv_fenye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transation);
        helper=new MySqliteHelper(this);

        lv_fenye= (ListView) findViewById(R.id.lv_fenye);
        fenye();
    }

    //点击按钮时批量插入
    public void insertData(View v) {
        SQLiteDatabase db=helper.getWritableDatabase();

        //1.数据库显式开启事务
        db.beginTransaction();

        if (db == null) {
            System.out.println("*******************************");
        }
        String sql1="insert into person values(1,'小慕',18)";
//        System.out.println(sql1);
//        db.execSQL(sql1);
        try {
            db.execSQL(sql1);
        }
        catch (SQLException e){
        }


        for(int i=1;i<=100;i++) {
            String sql="insert into "+Constant.TABLE_NAME+" values("+i+",'小慕"+i+"',18)";
            //sql="insert into person values(1,'小慕',18)";
            System.out.println(sql);
            try {
                db.execSQL(sql);
            }
            catch (SQLException e){
            }
        }
        //2.提交当前事务
        db.setTransactionSuccessful();
        //3.关闭事务
        db.endTransaction();
        db.close();
    }

    //select * from person limit 0,15;当前页码第一条数据的下标，每页显式的数据条目
    /*
    总条目：
    每页条数：
    总页数：
    页码：

    1.页码为1时在listview中展示对应数据
    2.当listview加载完本页数据分页加载下一页数据
    */
    private int totalNum;
    private SQLiteDatabase db;
    private int pageSize=20;//每页展示数据的条目
    private int pageNum;
    private int currentPage=1;//当前页码
    private List<Person> totalList;//表示数据源
    private MyBaseAdapter myBaseAdapter;
    private boolean isDivPage;
    public void fenye() {
        String path= getApplicationContext().getDatabasePath("info.db").getPath();
        db=SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READONLY);
        //获取数据表数据总条目
        totalNum= DbManger.getDataCount(db,Constant.TABLE_NAME);
        //根据总条目与每页展示数据条目 获得总页数
        pageNum= (int) Math.ceil(totalNum/(double)pageSize);
        if (currentPage == 1) {
            totalList=DbManger.getListByCurrentPage(db,Constant.TABLE_NAME,currentPage,pageSize);
        }
        myBaseAdapter=new MyBaseAdapter(this,totalList);
        lv_fenye.setAdapter(myBaseAdapter);

        lv_fenye.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (isDivPage&&AbsListView.OnScrollListener.SCROLL_STATE_IDLE==scrollState) {
                    if (currentPage<pageNum) {
                        currentPage++;
                        //根据最新的页码加载获取集合存储到数据源中
                        totalList.addAll(DbManger.getListByCurrentPage(db,Constant.TABLE_NAME,currentPage,pageSize));
                        myBaseAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                isDivPage=((firstVisibleItem+visibleItemCount)==totalItemCount);

            }
        });
    }

}
