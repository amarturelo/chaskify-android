package com.chaskify.android.ui.fragments.settings;

import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chaskify.android.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class SettingsVehicleFragment extends PreferenceFragment {

    public SettingsVehicleFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.vehicle_preferences);
    }
}
