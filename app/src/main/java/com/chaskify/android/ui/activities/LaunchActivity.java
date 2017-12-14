package com.chaskify.android.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.chaskify.android.R;
import com.chaskify.android.ui.base.BaseActivity;
import com.chaskify.android.ui.fragments.launch.LaunchFragment;
import com.chaskify.android.ui.fragments.launch.LoginFragment;

public class LaunchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initActivity(savedInstanceState);
    }

    private void initActivity(Bundle savedInstanceState) {
        if (findFragment(LaunchFragment.class) == null) {
            loadRootFragment(R.id.fragment, LaunchFragment.newInstance());
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_launch;
    }


}
