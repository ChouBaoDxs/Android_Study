package com.example.choubao.sqlitedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MySqliteHelper helper;
    //private Button btn_createdb,btn_insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper=DataBaseManger.getIntance(this);
        findViewById(R.id.btn_createdb).setOnClickListener(this);
        findViewById(R.id.btn_insert).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);

        findViewById(R.id.btn_insertApi).setOnClickListener(this);
        findViewById(R.id.btn_updateApi).setOnClickListener(this);
        findViewById(R.id.btn_deleteApi).setOnClickListener(this);
        findViewById(R.id.btn_queryApi).setOnClickListener(this);
    }
    public void createDataBase(){
        SQLiteDatabase db=helper.getReadableDatabase();
        //SQLiteDatabase db=helper.getWritableDatabase();
    }
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_createdb:
                createDataBase();
                break;
            case R.id.btn_insert:
                SQLiteDatabase db=helper.getWritableDatabase();
                String sql="insert into "+Constant.TABLE_NAME+" values(1,'张三',20)";
                DataBaseManger.execSQL(db,sql);
                String sql2="insert into "+Constant.TABLE_NAME+" values(2,'李四',25)";
                DataBaseManger.execSQL(db,sql2);
                db.close();
                break;
            case R.id.btn_update:
                db=helper.getWritableDatabase();
                String updateSql="update "+Constant.TABLE_NAME+" set "+Constant.NAME+"='小明' where _id=1";
                DataBaseManger.execSQL(db,updateSql);
                db.close();
                break;
            case R.id.btn_delete:
                db=helper.getWritableDatabase();
                String deleteSql="delete from "+Constant.TABLE_NAME+" where "+Constant._ID+"=2";
                DataBaseManger.execSQL(db,deleteSql);
                db.close();
                break;
            case R.id.btn_insertApi:
                db=helper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put(Constant._ID,3);
                values.put(Constant.NAME,"王五");
                values.put(Constant.AGE,30);
                //long 表示插入数据的列数,-1代表出错
                long result=db.insert(Constant.TABLE_NAME,null,values);
                if(result>0){
                    Toast.makeText(MainActivity.this,"插入数据成功",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"插入数据失败",Toast.LENGTH_LONG).show();
                }
                db.close();
                break;
            case R.id.btn_updateApi:
                db=helper.getWritableDatabase();
                ContentValues cv=new ContentValues();
                cv.put(Constant.NAME,"王五五");
                //int 表示成功修改的记录数
                int count=db.update(Constant.TABLE_NAME,cv,Constant._ID+"=3",null);
                //int count=db.update(Constant.TABLE_NAME,cv,Constant._ID+"=?",String[]{"3"});//两种是一样的
                if(count>0){
                    Toast.makeText(MainActivity.this,"修改数据成功",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"修改数据失败",Toast.LENGTH_LONG).show();
                }
                db.close();
                break;
            case R.id.btn_deleteApi:
                db=helper.getWritableDatabase();
                //int 表示删除掉的记录数
                int count2=db.delete(Constant.TABLE_NAME,Constant._ID+"=?",new String[]{"1"});
                if(count2>0){
                    Toast.makeText(MainActivity.this,"删除数据成功",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"删除数据失败",Toast.LENGTH_LONG).show();
                }
                db.close();
                break;
            case R.id.btn_queryApi:
                db=helper.getWritableDatabase();
                StringBuilder sb=new StringBuilder();
                //Cursor类似于JDBC的RresultSet
                Cursor cursor=db.query(Constant.TABLE_NAME,null,null,null,null,null,null);//表名，列名，where，where中占位符的值，group by，having，order by
                if(cursor.moveToFirst()){
                    do{
                        int id=cursor.getInt(cursor.getColumnIndex(Constant._ID));
                        String name=cursor.getString(cursor.getColumnIndex(Constant.NAME));
                        int age=cursor.getInt(cursor.getColumnIndex(Constant.AGE));
                        sb.append("id:"+id+" name:"+name+" age:"+age+"\n");
                    }while(cursor.moveToNext());
                }
                db.close();
                Toast.makeText(MainActivity.this,sb.toString(),Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
