package com.choubao.www.fragmentdemo;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RightFragment extends Fragment {

    private TextView tvRightFg;
    private String string="";

    public RightFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        if (bundle != null) {
            string=bundle.getString("data","");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_right, container, false);
        tvRightFg= (TextView) view.findViewById(R.id.tv_right_fg);
        if (!string.equals("")) {
            tvRightFg.setText(string);
        }
        return view;
    }

    public void updataTextView(String string) {
        tvRightFg.setText(string);
    }

}
