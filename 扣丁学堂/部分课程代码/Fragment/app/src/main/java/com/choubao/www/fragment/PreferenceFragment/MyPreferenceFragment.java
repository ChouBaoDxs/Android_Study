package com.choubao.www.fragment.PreferenceFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.choubao.www.fragment.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPreferenceFragment extends android.preference.PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);
    }
}
