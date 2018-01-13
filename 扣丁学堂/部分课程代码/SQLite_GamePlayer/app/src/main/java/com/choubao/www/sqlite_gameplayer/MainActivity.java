package com.choubao.www.sqlite_gameplayer;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.choubao.www.sqlite_gameplayer.db.DatabaseAdapter;
import com.choubao.www.sqlite_gameplayer.entity.GamePlayer;
import com.choubao.www.sqlite_gameplayer.fragments.AddFragment;
import com.choubao.www.sqlite_gameplayer.fragments.GamePlayerFragment;
import com.choubao.www.sqlite_gameplayer.fragments.UpdateFragment;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements AddFragment.AddFragmentListener,GamePlayerFragment.GamePlayerFragmentListener,UpdateFragment.UpdateFragmentListener {

    private GamePlayerFragment gamePlayerFragment;
    private UpdateFragment updateFragment;
    private DatabaseAdapter dbAdapter;


    //程序有错误啊*********************************


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbAdapter=new DatabaseAdapter(this);
        showGamePlayerFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch(id){
            case R.id.add:
                AddFragment createGamePlayerFragment=AddFragment.newInstance();
                createGamePlayerFragment.show(getSupportFragmentManager(),null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            } else {
                getFragmentManager().popBackStack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void add(GamePlayer gamePlayer) {
        dbAdapter.add(gamePlayer);
        gamePlayerFragment.changedData();
    }

    @Override
    public void showGamePlayerFragment() {
        gamePlayerFragment=GamePlayerFragment.newInstance();
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.main_layout,gamePlayerFragment);//替换
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void showUpdateFragment(int id) {
        updateFragment=UpdateFragment.newInstance(id);
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.main_layout,updateFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void delete(int id) {
        dbAdapter.delete(id);
        gamePlayerFragment.changedData();
    }

    @Override
    public ArrayList<GamePlayer> findAll() {
        return dbAdapter.findAll();
    }

    @Override
    public void update(GamePlayer gamePlayer) {
        dbAdapter.update(gamePlayer);
        gamePlayerFragment.changedData();
    }

    @Override
    public GamePlayer findById(int id) {
        return dbAdapter.findById(id);
    }
}
