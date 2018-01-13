package com.choubao.www.fragment.PopBackFragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.choubao.www.fragment.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopBackFragment extends Fragment {


    //private String title;
    public PopBackFragment() {

    }
    //这个样子传参是有问题的
//    public PopBackFragment(String title) {
//        this.title=title;
//    }

    //传参的正确方式           记住这个套路
    public static PopBackFragment getInstance(String title) {
        PopBackFragment p=new PopBackFragment();
        Bundle b=new Bundle();
        b.putString("title",title);
        p.setArguments(b);
        return p;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_pop_back, container, false);
        TextView tv= (TextView) view.findViewById(R.id.textView_text);
        //tv.setText(title);
        tv.setText(getArguments().getString("title"));
        return view;
    }
}
