package com.chaskify.android.ui.fragments.settings;

import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chaskify.android.R;
import com.chaskify.android.navigation.Navigator;

/**
 * A placeholder fragment containing a simple view.
 */
public class RootSettingsFragment extends PreferenceFragment {

    public RootSettingsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.root_preferences);

        findPreference(getString(R.string.key_preference_profile)).setOnPreferenceClickListener(preference -> {
            Navigator.goToProfileSettings(getActivity());
            return true;
        });


    }

}