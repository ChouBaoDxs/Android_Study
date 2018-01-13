package com.choubao.www.fragment.Activity3;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.choubao.www.fragment.R;


public class MainFragment extends Fragment {
    public MainFragment() {
        // Required empty public constructor
    }

    private TextView textView_value;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_main, container, false);
        textView_value= (TextView) view.findViewById(R.id.value);
        return view;
    }

    public void changeTextViewValue(String value) {
        textView_value.setText(value);
    }

}
