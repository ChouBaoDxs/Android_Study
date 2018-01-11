package com.choubao.www.fragmentdemo.FragmentDemo;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.choubao.www.fragmentdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TitleFragment extends Fragment implements AdapterView.OnItemClickListener {

    private String[] newsTitles={"news1","news2","news3"};
    private String[] newsDetials={"News Detail 1","News Detail 2","News Detail 3"};

    private ListView lvNewsTitle;

    private ArrayAdapter<String> adapter;

    public TitleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_title, container, false);

        lvNewsTitle=(ListView)view.findViewById(R.id.lv_news_title);

        adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,newsTitles);

        lvNewsTitle.setOnItemClickListener(this);

        lvNewsTitle.setAdapter(adapter);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView tvDetial=(TextView)getActivity().findViewById(R.id.tv_news_detail);
        if(tvDetial==null){
            //为手机
            Intent intent=new Intent(getActivity(),DetialActivity.class);
            intent.putExtra("detail",newsDetials[position]);
            startActivity(intent);
        }else{
            //为平板
            tvDetial.setText(newsDetials[position]);
        }
    }
}
