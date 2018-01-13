package com.choubao.www.fragment.Activity3;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.choubao.www.fragment.R;

public class MenuFragment extends Fragment implements View.OnClickListener{

    private MyMenuListener myMenuListener;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        myMenuListener=(MyMenuListener)activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_menu, container, false);
        view.findViewById(R.id.button_xingfa).setOnClickListener(this);
        view.findViewById(R.id.button_minfa).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_xingfa:
                myMenuListener.changeValue("刑法");
                break;
            case R.id.button_minfa:
                myMenuListener.changeValue("民法");
                break;
        }
    }

    //定义一个回调接口  宿主要实现这个接口
    public static interface MyMenuListener {
        public void changeValue(String value);
    }

}
