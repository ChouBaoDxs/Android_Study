package com.choubao.www.fragmentdemo.SharedPreferencesDemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.choubao.www.fragmentdemo.R;

public class SharedPreferencesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        SharedPreferences sharedPreferences=getSharedPreferences("share",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("android","安卓");
        editor.putInt("version",1);
        editor.commit();

        SharedPreferences sharedPreferences2=getSharedPreferences("share",MODE_PRIVATE);
        String name=sharedPreferences2.getString("android","");
        int version=sharedPreferences2.getInt("version",0);
        Toast.makeText(this,name+" "+version,Toast.LENGTH_SHORT).show();
    }
}
