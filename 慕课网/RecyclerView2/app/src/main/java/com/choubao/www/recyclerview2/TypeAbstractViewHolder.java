package com.choubao.www.recyclerview2;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by choubao on 17/5/5.
 */

public abstract class TypeAbstractViewHolder<T> extends RecyclerView.ViewHolder {
    public TypeAbstractViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindHolder(T dataModel);
}
