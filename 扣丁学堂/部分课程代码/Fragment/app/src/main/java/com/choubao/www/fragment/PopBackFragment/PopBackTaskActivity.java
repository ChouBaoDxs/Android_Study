package com.choubao.www.fragment.PopBackFragment;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.choubao.www.fragment.R;

public class PopBackTaskActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button_one,button_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_back_task);
        button_one= (Button) findViewById(R.id.button_one);
        button_two= (Button) findViewById(R.id.button_two);
        button_one.setOnClickListener(this);
        button_two.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_one:
                System.out.println("one");
                PopBackFragment p1=PopBackFragment.getInstance("one");
                FragmentTransaction ft=getFragmentManager().beginTransaction();
                ft.replace(R.id.content,p1);
                //把当前Fragement添加到Activity栈
                ft.addToBackStack(null);
                ft.commit();
                break;
            case R.id.button_two:
                System.out.println("two");
                PopBackFragment p2=PopBackFragment.getInstance("two");
                FragmentTransaction ft2=getFragmentManager().beginTransaction();
                ft2.replace(R.id.content,p2);
                //把当前Fragement添加到Activity栈
                ft2.addToBackStack(null);
                ft2.commit();
                break;
            default :

                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getFragmentManager().getBackStackEntryCount() == 0) {
                finish();
            } else {
                getFragmentManager().popBackStack();//出栈
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
