package com.chaskify.android.ui.fragments.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chaskify.android.R;
import com.chaskify.android.ui.base.BaseFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class SettingsProfileFragment extends BaseFragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponents(view);
    }

    private void initComponents(View view) {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_settings_profile;
    }

    public static SettingsProfileFragment newInstance() {

        Bundle args = new Bundle();

        SettingsProfileFragment fragment = new SettingsProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
