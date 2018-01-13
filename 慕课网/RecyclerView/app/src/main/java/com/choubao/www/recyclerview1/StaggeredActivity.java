package com.choubao.www.recyclerview1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StaggeredActivity extends AppCompatActivity {
    private RecyclerView mRecycleView;
    private List<String> mDatas;
    private StaggeredAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();
        initViews();

        adapter = new StaggeredAdapter(this,mDatas);
        mRecycleView.setAdapter(adapter);
        setAsStaggeredView();

        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(StaggeredActivity.this, "CLICK: " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {
                adapter.deleteItem(position);
            }
        });
    }

    private void setAsStaggeredView() {
        //设置为瀑布流
        mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
    }

    private void initDatas() {
        mDatas = new ArrayList<>();

        for (int i = 'A'; i <= 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    private void initViews() {
        mRecycleView = (RecyclerView) findViewById(R.id.recycleView);
    }
}
