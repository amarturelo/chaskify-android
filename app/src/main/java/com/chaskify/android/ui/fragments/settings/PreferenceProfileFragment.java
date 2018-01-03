package com.chaskify.android.ui.fragments.settings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.chaskify.android.R;
import com.chaskify.android.navigation.Navigator;
import com.chaskify.android.ui.base.BaseFragment;

/**
 * Created by alberto on 3/01/18.
 */

public class PreferenceProfileFragment extends PreferenceFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.profile_preferences);
    }

    public static PreferenceProfileFragment newInstance() {

        Bundle args = new Bundle();

        PreferenceProfileFragment fragment = new PreferenceProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
