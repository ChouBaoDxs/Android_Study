package com.choubao.www.recyclerview2;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by choubao on 17/5/5.
 */

public class TypeThreeViewHolder extends TypeAbstractViewHolder<DataModelThree>{

    public ImageView avatar;
    public TextView name;
    public TextView content;
    private ImageView contentImage;

    public TypeThreeViewHolder(View itemView) {
        super(itemView);
        avatar= (ImageView) itemView.findViewById(R.id.avatar);
        contentImage= (ImageView) itemView.findViewById(R.id.contentImage);
        name= (TextView) itemView.findViewById(R.id.name);
        content= (TextView) itemView.findViewById(R.id.content);
        itemView.setBackgroundColor(Color.GRAY);
    }

    @Override
    public void bindHolder(DataModelThree model) {
        avatar.setBackgroundColor(model.avatarColor);
        name.setText(model.name);
        content.setText(model.content);
        contentImage.setBackgroundResource(model.contentColor);
    }
}
