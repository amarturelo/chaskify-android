package com.chaskify.android.ui.fragments.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chaskify.android.R;
import com.chaskify.android.ui.base.BaseFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class PreferenceVehicleFragment extends BaseFragment {

    public PreferenceVehicleFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.preference_vehicle_fragment;
    }
}
