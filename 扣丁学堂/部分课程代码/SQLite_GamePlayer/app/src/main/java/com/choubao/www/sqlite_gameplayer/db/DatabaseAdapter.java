package com.choubao.www.sqlite_gameplayer.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.choubao.www.sqlite_gameplayer.entity.GamePlayer;

import java.util.ArrayList;

/**
 * Created by choubao on 17/4/28.
 * 数据库操作工具类
 */

public class DatabaseAdapter {
    private DatabaseHelper dbHelper;

    public DatabaseAdapter(Context context){dbHelper=new DatabaseHelper(context);}

    public void add(GamePlayer gamePlayer){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(GameMetaData.GamePlayer.PLAYER,gamePlayer.getPlayer());
        values.put(GameMetaData.GamePlayer.SCORE,gamePlayer.getScore());
        values.put(GameMetaData.GamePlayer.LEVEL,gamePlayer.getLevel());
        db.insert(GameMetaData.GamePlayer.TABLE_NAME,null,values);
        db.close();
    }

    public void delete(int id){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        String whereClause=GameMetaData.GamePlayer._ID+"=?";
        String[] whereArgs={String.valueOf(id)};
        db.delete(GameMetaData.GamePlayer.TABLE_NAME,whereClause,whereArgs);
        db.close();
    }

    public void update(GamePlayer gamePlayer){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(GameMetaData.GamePlayer.PLAYER,gamePlayer.getPlayer());
        values.put(GameMetaData.GamePlayer.SCORE,gamePlayer.getScore());
        values.put(GameMetaData.GamePlayer.LEVEL,gamePlayer.getLevel());
        String whereClause=GameMetaData.GamePlayer._ID+"=?";
        String[] whereArgs={String.valueOf(gamePlayer.getId())};
        db.update(GameMetaData.GamePlayer.TABLE_NAME,values,whereClause,whereArgs);
        db.close();
    }

    public GamePlayer findById(int id) {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.query(true,GameMetaData.GamePlayer.TABLE_NAME,null,GameMetaData.GamePlayer._ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);
        GamePlayer gamePlayer=null;
        if (cursor.moveToNext()) {
            gamePlayer=new GamePlayer();
            gamePlayer.setId(cursor.getInt(cursor.getColumnIndexOrThrow(GameMetaData.GamePlayer._ID)));
            gamePlayer.setPlayer(cursor.getString(cursor.getColumnIndexOrThrow(GameMetaData.GamePlayer.PLAYER)));
            gamePlayer.setScore(cursor.getInt(cursor.getColumnIndexOrThrow(GameMetaData.GamePlayer.SCORE)));
            gamePlayer.setLevel(cursor.getInt(cursor.getColumnIndexOrThrow(GameMetaData.GamePlayer.LEVEL)));
        }
        cursor.close();
        db.close();
        return gamePlayer;
    }

    public ArrayList<GamePlayer> findAll(){
        //用*代替三个列名会降低系统效率
        String sql="select _id,player,score,level from player_table order by score desc";//降序排序
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.rawQuery(sql,null);

        ArrayList<GamePlayer> gamePlayers=new ArrayList<>();
        GamePlayer gamePlayer=null;
        while(cursor.moveToNext()) {
            gamePlayer=new GamePlayer();
            gamePlayer.setId(cursor.getInt(cursor.getColumnIndexOrThrow(GameMetaData.GamePlayer._ID)));
            gamePlayer.setPlayer(cursor.getString(cursor.getColumnIndexOrThrow(GameMetaData.GamePlayer.PLAYER)));
            gamePlayer.setScore(cursor.getInt(cursor.getColumnIndexOrThrow(GameMetaData.GamePlayer.SCORE)));
            gamePlayer.setLevel(cursor.getInt(cursor.getColumnIndexOrThrow(GameMetaData.GamePlayer.LEVEL)));
            gamePlayers.add(gamePlayer);
        }
        cursor.close();
        db.close();
        return gamePlayers;
    }

    public int getCount(){
        int count=0;
        String sql="select count(_id) from player_table";
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        cursor.moveToFirst();
        count=cursor.getInt(0);
        cursor.close();
        db.close();
        return count;
    }
}
