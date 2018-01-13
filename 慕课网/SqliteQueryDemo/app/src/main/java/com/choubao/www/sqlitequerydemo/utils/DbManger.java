package com.choubao.www.sqlitequerydemo.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.choubao.www.sqlitequerydemo.bean.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by choubao on 17/4/26.
 */

public class DbManger {
    //单例形式
    private static MySqliteHelper helper;
    public static MySqliteHelper getInstance(Context context) {
        if (helper==null) {
            helper=new MySqliteHelper(context);
        }
        return helper;
    }

    //数据库 sql语句 查询条件的占位符
    public static Cursor selectDataBySql(SQLiteDatabase db,String sql,String[] selectionArgs) {
        Cursor cursor=null;
        if (db!=null) {
            cursor=db.rawQuery(sql,selectionArgs);
        }
        return cursor;
    }

    //Cursord对象转换成List
    public static List<Person> cursorToList(Cursor cursor) {
        List<Person> list=new ArrayList<>();

        while (cursor.moveToNext()) {
            //根据参数中指定的字段名称获取字段下标
            int columnIndex=cursor.getColumnIndex(Constant._ID);
            //根据参数指定的字段下标，获取对应int类型的value
            int _id=cursor.getInt(columnIndex);

            String name=cursor.getString(cursor.getColumnIndex(Constant.NAME));
            int age=cursor.getInt(cursor.getColumnIndex(Constant.AGE));

            Person person=new Person(_id,name,age);
            list.add(person);
        }
        return list;
    }

    //根据数据库以及表名获取表中数据总条目
    public static int getDataCount(SQLiteDatabase db,String tableName) {
        int count=0;
        if (db!=null) {
            Cursor cursor=db.rawQuery("select * from "+tableName,null);
            count=cursor.getCount();//获取cursor中的数据总数
        }
        return count;
    }

    //根据当前的页码查询获取该页码对应的集合数据        select * from person ?,?
    //0,15
    //15,15
    //30,15
    //
    public static List<Person> getListByCurrentPage(SQLiteDatabase db,String tableName,int currentPage,int pageSize) {

        int index=(currentPage-1)*pageSize;//获取当前页码第一条数据的下标
        Cursor cursor=null;
        if (db != null) {
            String sql="select * from "+tableName+" limit ?,?";
            cursor=db.rawQuery(sql,new String[]{index+"",pageSize+""});
        }
        return cursorToList(cursor);
    }
}
