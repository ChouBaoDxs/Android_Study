package com.choubao.www.fragment.Activity3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.choubao.www.fragment.R;

public class Main3Activity extends AppCompatActivity implements MenuFragment.MyMenuListener{

    private MenuFragment menuFragment;
    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        menuFragment=(MenuFragment) getFragmentManager().findFragmentById(R.id.menuFragement);
        mainFragment=(MainFragment) getFragmentManager().findFragmentById(R.id.mainFragement);
    }


    @Override
    public void changeValue(String value) {
        mainFragment.changeTextViewValue(value);
    }
}
