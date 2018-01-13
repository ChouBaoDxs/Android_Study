package com.choubao.www.recyclerview2;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private DemoAdapter demoAdapter;

    int colors[] = {
            android.R.color.holo_red_dark,
            android.R.color.holo_blue_dark,
            android.R.color.holo_orange_dark,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);//2列

        //根据子View类型设置其所占的列数（空间））
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = recyclerView.getAdapter().getItemViewType(position);

                if (type == DataModel.TYPE_THREE) {
                    return gridLayoutManager.getSpanCount();
                } else {
                    return 1;
                }
            }
        });
        //recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setLayoutManager(gridLayoutManager);
        demoAdapter = new DemoAdapter(this);
        recyclerView.setAdapter(demoAdapter);

        //设置子View间隔
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
                int spanSize = layoutParams.getSpanSize();
                int spanIndex = layoutParams.getSpanIndex();
                outRect.top = 20;
                if (spanSize != gridLayoutManager.getSpanCount()) {
                    if (spanIndex == 1) {
                        outRect.left = 10;
                    } else {
                        outRect.right = 10;
                    }
                }
            }
        });

        initData();
    }

    private void initData() {
        List<DataModel> list = new ArrayList<>();
        for (int i = 0; i <= 30; i++) {
//            int type= (int) (Math.random()*3+1); //1~3
            int type = 0;
            if (i < 5 || (i > 15 && i < 20)) {
                type = 1;
            } else if (i < 10 || i > 26) {
                type = 2;
            } else {
                type = 3;
            }
            DataModel data = new DataModel();
            data.avatarColor = colors[type - 1];
            data.type = type;
            data.name = "name:" + type;
            data.content = "content" + i;
            data.contentColor = colors[(type + 1) % 3];
            list.add(data);
        }

        List<DataModelOne> list1=new ArrayList<>();
        List<DataModelTwo> list2=new ArrayList<>();
        List<DataModelThree> list3=new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            DataModelOne data = new DataModelOne();
            data.avatarColor = colors[0];
            data.name = "name:" + 1;
            list1.add(data);
        }
        for (int i = 0; i < 10; i++) {
            DataModelTwo data = new DataModelTwo();
            data.avatarColor = colors[1];
            data.name = "name:" + 2;
            data.content="content:"+2;
            list2.add(data);
        }
        for (int i = 0; i < 10; i++) {
            DataModelThree data = new DataModelThree();
            data.avatarColor = colors[2];
            data.name = "name:" + 3;
            data.content="content:"+3;
            data.contentColor=colors[2];
            list3.add(data);
        }


        demoAdapter.addList(list,list1,list2,list3);
        demoAdapter.notifyDataSetChanged();
    }
}
