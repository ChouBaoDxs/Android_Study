package com.choubao.www.recyclerview1;

import android.content.Context;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义 RecyclerView 适配器
 * <p>
 * Created by Mouzhai on 2016/12/20.
 */


public class StaggeredAdapter extends MyAdapter {

    private List<Integer> mHeights;
    private List<String> data;

    public StaggeredAdapter(Context context, List<String> data) {
        super(context, data);
        this.data = data;

        mHeights = new ArrayList<>();
        //添加随机高度
        for (int i = 0; i < data.size(); i++) {
            mHeights.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = mHeights.get(position);
        holder.itemView.setLayoutParams(layoutParams);

        holder.textView.setText(data.get(position));

        setUpClickEvent(holder);
    }
}
