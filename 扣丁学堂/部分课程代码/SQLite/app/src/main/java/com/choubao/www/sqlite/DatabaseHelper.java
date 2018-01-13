package com.choubao.www.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by choubao on 17/4/28.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="pet.db";
    private static final int VERSION=1;
    private static final String CREATE_TABLE_DOG="create table dog(_id integer primary key autoincrement,name text,age integer)";
    private static final String DROP_TABLE_DOG="DROP TABLE IF EXISTS dog";

    public DatabaseHelper(Context context)  {
        super(context, DB_NAME, null, VERSION);
        //System.out.println(DB_NAME+VERSION);
    }

    //创建数据库时会调用的函数
    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQLiteDatabase用于操作数据库的工具类
        db.execSQL(CREATE_TABLE_DOG);
    }

    //升级  检测到版本不一致时会调用该方法    先删表再建表
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_DOG);
        db.execSQL(CREATE_TABLE_DOG);
    }
}
