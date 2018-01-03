package com.chaskify.android.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chaskify.android.R;
import com.chaskify.android.ui.base.AbstractSwipeBackFragment;
import com.chaskify.android.ui.base.BaseFragment;
import com.chaskify.android.ui.fragments.settings.PreferenceProfileFragment;

import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProfileFragment extends AbstractSwipeBackFragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initFragments(savedInstanceState);
        initComponents(view);
    }

    private void initFragments(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
        }
    }

    private void initComponents(View view) {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_profile;
    }

    public static ProfileFragment newInstance() {

        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
