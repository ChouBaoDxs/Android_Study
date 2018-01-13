package com.choubao.www.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by choubao on 17/4/28.
 */

public class DatabaseAdapter {
    private DatabaseHelper dbHelper;

    public DatabaseAdapter(Context context) {
        dbHelper=new DatabaseHelper(context);
    }

    //事务处理
    public void transaction() {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.beginTransaction();//开始事务
        try {
            db.execSQL("insert into dog(name,age)values('duang',4)");
            db.execSQL("insert into dog(name,age)values('guang',3)");
            db.setTransactionSuccessful();//设置事务的成功标记
        }finally {
            db.endTransaction();//提交or回滚结束事务,会判断事务的标记  true：提交 false：回滚
        }
        db.close();
    }

    public void rawAdd(Dog dog) {
        String sql="insert into dog(name,age) values (?,?)";
        Object[] args={dog.getName(),dog.getAge()};
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.execSQL(sql,args);
        db.close();
    }

    public void rawDelete(int _id){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        String sql="delete from dog where _id=?";
        Object[] args={_id};
        db.execSQL(sql,args);
        db.close();
    }

    public void rawDelete(Dog dog) {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        String sql="update dog set name=?,age=?,where _id=?";
        Object[] args={dog.getName(),dog.getName(),dog.get_id()};
        db.execSQL(sql,args);
        db.close();
    }

    public Dog rawFindById(int _id) {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        String sql="select _id,name,age from dog where _id=?";
        Cursor cursor=db.rawQuery(sql,new String[]{String.valueOf(_id)});
        Dog dog=null;
        if(cursor.moveToNext()) {
            dog=new Dog();
            dog.set_id(cursor.getInt(cursor.getColumnIndex(PetMetaData.DogTable._ID)));
            dog.setName(cursor.getString(cursor.getColumnIndex(PetMetaData.DogTable.NAME)));
            dog.setAge(cursor.getInt(cursor.getColumnIndex(PetMetaData.DogTable.AGE)));
        }
        cursor.close();
        db.close();
        return dog;
    }

    public ArrayList<Dog> rawFindAll() {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        String sql="select _id,name,age from dog";
        Cursor cursor=db.rawQuery(sql,null);

        ArrayList<Dog> dogs=new ArrayList<>();
        Dog dog=null;
        while (cursor.moveToNext()) {
            dog=new Dog();
            dog.set_id(cursor.getInt(cursor.getColumnIndex(PetMetaData.DogTable._ID)));
            dog.setName(cursor.getString(cursor.getColumnIndex(PetMetaData.DogTable.NAME)));
            dog.setAge(cursor.getInt(cursor.getColumnIndex(PetMetaData.DogTable.AGE)));
            dogs.add(dog);
        }
        cursor.close();
        db.close();
        return dogs;
    }

    //添加操作
    public void add(Dog dog) {
        //获取操作数据库的工具类
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(PetMetaData.DogTable.NAME,dog.getName());
        values.put(PetMetaData.DogTable.AGE,dog.getAge());
        //参数（表名，可以为null的列名(填写的话，可以保证插入语句合法) ContentValues）
        //合法insert into dog (name,age) values ('name',2)
        //不合法insert into dog() values()
        db.insert(PetMetaData.DogTable.TABLE_NAME,null,values);
        db.close();
    }

    //实际中是不会删除数据的，最多标记一下
    public void delete(int _id) {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        String whereClause=PetMetaData.DogTable._ID+"=?";
        String[] whereArgs={String.valueOf(_id)};
        //表名 删除的条件 条件的值
        db.delete(PetMetaData.DogTable.TABLE_NAME,whereClause,whereArgs);
        db.close();
    }

    //更新操作
    public void update(Dog dog) {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(PetMetaData.DogTable.NAME,dog.getName());
        values.put(PetMetaData.DogTable.AGE,dog.getAge());
        String whereClause=PetMetaData.DogTable._ID+"=?";
        String[] whereArgs={String.valueOf(dog.get_id())};
        //表名，更新字段的集合，条件，条件的值
        db.update(PetMetaData.DogTable.TABLE_NAME,values,whereClause,whereArgs);
        db.close();
    }

    //根据_ID查询单个记录
    public Dog findById(int _id) {
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String[] columns={PetMetaData.DogTable._ID,PetMetaData.DogTable.NAME,PetMetaData.DogTable.AGE};
        Cursor cursor=db.query(true,PetMetaData.DogTable.TABLE_NAME,columns,PetMetaData.DogTable._ID+"=?",new String[]{String.valueOf(_id)},null,null,null,null);

        Dog dog=null;
        if(cursor.moveToNext()) {
            dog=new Dog();
            dog.set_id(cursor.getInt(cursor.getColumnIndex(PetMetaData.DogTable._ID)));
            dog.setName(cursor.getString(cursor.getColumnIndex(PetMetaData.DogTable.NAME)));
            dog.setAge(cursor.getInt(cursor.getColumnIndex(PetMetaData.DogTable.AGE)));
        }
        cursor.close();
        db.close();
        return dog;
    }

    //查询所有
    public ArrayList<Dog> findAll() {
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String[] columns={PetMetaData.DogTable._ID,PetMetaData.DogTable.NAME,PetMetaData.DogTable.AGE};
        /*String selection=null;
        String[] selectionArgs=null;
        db.query(true,PetMetaData.DogTable.TABLE_NAME,columns,selection,selectionArgs,null,null,null,null);*/
        //是否去重 表名 要查询的列 查询条件 查询条件的值 分组的条件 分组条件的值 排序 分页
        Cursor cursor=db.query(true,PetMetaData.DogTable.TABLE_NAME,null,null,null,null,null,null,null);

        ArrayList<Dog> dogs=new ArrayList<>();
        Dog dog=null;
        while (cursor.moveToNext()) {
            dog=new Dog();
            dog.set_id(cursor.getInt(cursor.getColumnIndex(PetMetaData.DogTable._ID)));
            dog.setName(cursor.getString(cursor.getColumnIndex(PetMetaData.DogTable.NAME)));
            dog.setAge(cursor.getInt(cursor.getColumnIndex(PetMetaData.DogTable.AGE)));
            dogs.add(dog);
        }
        cursor.close();
        db.close();
        return dogs;
    }
}
