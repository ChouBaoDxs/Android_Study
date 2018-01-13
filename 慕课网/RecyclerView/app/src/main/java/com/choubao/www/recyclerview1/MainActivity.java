package com.choubao.www.recyclerview1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;
    private List<String> mDatas;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();
        initViews();
        mAdapter=new MyAdapter(this,mDatas);
        mRecycleView.setAdapter(mAdapter);
        //设置RecycleView的布局管理
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecycleView.setLayoutManager(linearLayoutManager);

        mRecycleView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this,"click:"+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Toast.makeText(MainActivity.this,"long click:"+position,Toast.LENGTH_SHORT).show();
            }
        });

        //设置RecycleView的Item间分割线
       // mRecycleView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
    }

    private void initViews() {
        mRecycleView= (RecyclerView) findViewById(R.id.recycleView);
    }

    private void initDatas() {
        mDatas=new ArrayList<String>();
        for (int i='A';i<'z';i++) {
            mDatas.add(""+(char)i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch(id){
            case R.id.action_gridView:
                //3列
                mRecycleView.setLayoutManager(new GridLayoutManager(this,3));
                break;
            case R.id.action_listView:
                mRecycleView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.action_staggered_gridView:
                startActivity(new Intent(this,StaggeredActivity.class));
                break;
            case R.id.action_hor_gridView:
                mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.action_add:
                mAdapter.addItem(1);
                break;
            case R.id.action_delete:
                mAdapter.deleteItem(1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
