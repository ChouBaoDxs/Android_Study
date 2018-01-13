package com.choubao.www.sqlitequerydemo.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by choubao on 17/4/26.
 */

public class MySqliteHelper extends SQLiteOpenHelper {

    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //这个是自己写的
    public MySqliteHelper(Context context) {
        super(context,Constant.DATABASE_NAME,null,Constant.DATABASE_VERSON);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql="create table "+Constant.TABLE_NAME+
                "("+Constant._ID+" Integer primary key,"+Constant.NAME+" varchar(10),"+Constant.AGE+" Integer)";

        System.out.println(sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
