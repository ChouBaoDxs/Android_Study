package com.choubao.www.fragment.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.choubao.www.fragment.Activity2.Main2Activity;
import com.choubao.www.fragment.PopBackFragment.PopBackTaskActivity;
import com.choubao.www.fragment.Activity3.Main3Activity;
import com.choubao.www.fragment.PreferenceFragment.PreferenceFragmentActivity;
import com.choubao.www.fragment.R;

public class MainActivity extends AppCompatActivity {

    TitleFragment titleFragment;
    ContentFragment contentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //通过Fragment管理器获得fragment
        titleFragment= (TitleFragment) getFragmentManager().findFragmentById(R.id.title_fragment);
        contentFragment= (ContentFragment) getFragmentManager().findFragmentById(R.id.content_fragment);
    }

    public void start_Activity2(View v) {
        startActivity(new Intent(this,Main2Activity.class));
    }

    public void start_PopBackTaskActivity(View v) {
        startActivity(new Intent(this,PopBackTaskActivity.class));
    }

    public void start_Activity3(View v) {
        startActivity(new Intent(this,Main3Activity.class));
    }

    public void start_preActivity(View v) {
        startActivity(new Intent(this, PreferenceFragmentActivity.class));
    }
}
