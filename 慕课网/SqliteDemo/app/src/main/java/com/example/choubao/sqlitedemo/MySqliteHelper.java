package com.example.choubao.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by choubao on 16/11/21.
 */

public class MySqliteHelper extends SQLiteOpenHelper{

    /*
    * context 上下文对象
    * name 创建数据库的名称
    * factory 游标工厂
    * version 表示数据库的版本
    * */
    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySqliteHelper(Context context){
        super(context,Constant.DARABASE_NAME,null,Constant.DATABASE_VERSION);
    }
    /*
    * 数据库创建时回调的函数
    * */
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("DaraBase onCreate");
        String sql="create table "+Constant.TABLE_NAME+"("+Constant._ID+
                " Integer primary key,"+Constant.NAME+" varchar(10),"+
                Constant.AGE+" Integer)";
        db.execSQL(sql);//执行sql语句
    }
    /*
    * 数据库版本更新时回调的函数
    * */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*
    * 数据库打开时回调的函数
    * */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
