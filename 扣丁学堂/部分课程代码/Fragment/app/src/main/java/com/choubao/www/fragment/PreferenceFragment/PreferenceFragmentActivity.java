package com.choubao.www.fragment.PreferenceFragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.choubao.www.fragment.R;

public class PreferenceFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_fragment);

        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        String name=sp.getString("edittext_preference","");
        Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
    }
}
