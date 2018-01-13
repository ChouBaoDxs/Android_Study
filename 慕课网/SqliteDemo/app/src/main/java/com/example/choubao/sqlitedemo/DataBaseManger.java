package com.example.choubao.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by choubao on 16/11/21.
 */

public class DataBaseManger {
    private static MySqliteHelper helper;
    public static MySqliteHelper getIntance(Context context){
        if(helper==null){
            helper=new MySqliteHelper(context);
        }
        return helper;
    }

    public static void execSQL(SQLiteDatabase db,String sql){
        if(db!=null){
            if(sql!=null && !"".equals(sql)){
                db.execSQL(sql);
            }
        }
    }
}
