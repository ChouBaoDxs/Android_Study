package com.choubao.www.recyclerview1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by choubao on 17/5/5.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private List<String> mDatas;
    private Context mContext;


    private OnItemClickListener mOnItemClickListener;
    /**
     * item 的点击回调接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onLongItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }


    public MyAdapter(Context context, List<String> data) {
        this.mDatas = data;
        this.mContext=context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_single_textview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.textView.setText(mDatas.get(position));
        setUpClickEvent(holder);
    }

    protected void setUpClickEvent(final MyViewHolder holder) {
        //设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mOnItemClickListener.onLongItemClick(holder.itemView, holder.getLayoutPosition());
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 添加 Item
     */
    public void addItem(int position) {
        mDatas.add(position, "Insert One");
//        notifyDataSetChanged();//注意了，这里不是调用这个
        notifyItemInserted(position);
    }

    /**
     * 删除 Item
     */
    public void deleteItem(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.id_tv);
        }
    }
}
