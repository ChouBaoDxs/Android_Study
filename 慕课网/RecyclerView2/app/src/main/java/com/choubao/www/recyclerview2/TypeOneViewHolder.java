package com.choubao.www.recyclerview2;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by choubao on 17/5/5.
 */

public class TypeOneViewHolder extends TypeAbstractViewHolder<DataModelOne>{

    public ImageView avatar;
    public TextView name;

    public TypeOneViewHolder(View itemView) {
        super(itemView);
        avatar= (ImageView) itemView.findViewById(R.id.avatar);
        name= (TextView) itemView.findViewById(R.id.name);
        itemView.setBackgroundColor(Color.BLACK);
    }

    @Override
    public void bindHolder(DataModelOne model) {
        avatar.setBackgroundResource(model.avatarColor);
        name.setText(model.name);
    }
}
