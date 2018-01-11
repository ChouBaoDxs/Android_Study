package com.choubao.www.fragmentdemo;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class LeftFragment extends Fragment {

    //要弄一个回调，这里写一个接口
    public interface onSendDataButtonClickListener{
        public void onSendDataButtonClicked(String string);
    }

    private onSendDataButtonClickListener listener;

    //当fragment和activity被关联时调用
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener=(onSendDataButtonClickListener)context;
    }

    public LeftFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i("LeftFragment","onAttach()");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("LeftFragment","onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("LeftFragment","onCreateView()");
        View view= inflater.inflate(R.layout.fragment_left, container, false);
        Button btnSenData= (Button) view.findViewById(R.id.btnSendDataToFragment);
        btnSenData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSendDataButtonClicked("来自星星的数据");
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("LeftFragment","onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("LeftFragment","onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("LeftFragment","onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("LeftFragment","onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("LeftFragment","onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("LeftFragment","onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("LeftFragment","onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("LeftFragment","onDetach()");
    }

}
