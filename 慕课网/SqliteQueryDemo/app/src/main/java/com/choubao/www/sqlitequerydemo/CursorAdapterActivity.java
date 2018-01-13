package com.choubao.www.sqlitequerydemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.choubao.www.sqlitequerydemo.utils.Constant;

public class CursorAdapterActivity extends AppCompatActivity {

    private ListView lv;
    private SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv= (ListView) findViewById(R.id.lv);
        String path= getApplicationContext().getDatabasePath("info.db").getPath();
        sqLiteDatabase=SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READONLY);
        Cursor cursor=sqLiteDatabase.rawQuery("select * from "+ Constant.TABLE_NAME,null);
        //System.out.println("select * from "+ Constant.TABLE_NAME);
        MyCursorAdapter adapter=new MyCursorAdapter(this,cursor,CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lv.setAdapter(adapter);
        sqLiteDatabase.close();
    }

    /*
    *以内部类的形式定义适配器
    *
    */
    public class MyCursorAdapter extends CursorAdapter {
        public MyCursorAdapter(Context context,Cursor c,int flags) {
            super(context,c,flags);
        }

        //创建适配器控件中每个item对应的view对象
        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View view = LayoutInflater.from(CursorAdapterActivity.this).inflate(R.layout.item_layout, null);
            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView tv_id= (TextView) view.findViewById(R.id.tv_id);
            TextView tv_name= (TextView) view.findViewById(R.id.tv_name);
            TextView tv_age= (TextView) view.findViewById(R.id.tv_age);

            tv_id.setText(cursor.getInt(cursor.getColumnIndex(Constant._ID))+"");
            tv_name.setText(cursor.getString(cursor.getColumnIndex(Constant.NAME)));
            tv_age.setText(cursor.getInt(cursor.getColumnIndex(Constant.AGE))+"");
           //System.out.println(cursor.getColumnIndex(Constant._ID)+cursor.getColumnIndex(Constant.NAME)+cursor.getColumnIndex(Constant.AGE));
        }
    }
}
