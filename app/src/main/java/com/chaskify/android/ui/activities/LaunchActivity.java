package com.chaskify.android.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.annimon.stream.Optional;
import com.chaskify.android.R;
import com.chaskify.android.ui.base.BaseActivity;
import com.chaskify.android.ui.fragments.launch.LaunchFragment;
import com.chaskify.android.ui.fragments.launch.LoginFragment;
import com.chaskify.domain.model.Credentials;

import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class LaunchActivity extends BaseActivity {

    public final static String ARG_ACCOUNT_TYPE = "ACCOUNT_TYPE";
    public final static String ARG_AUTH_TYPE = "AUTH_TYPE";
    public final static String ARG_ACCOUNT_NAME = "ACCOUNT_NAME";
    public final static String ARG_IS_ADDING_NEW_ACCOUNT = "IS_ADDING_ACCOUNT";

    public static final String KEY_ERROR_MESSAGE = "ERR_MSG";

    public final static String PARAM_USER_PASS = "USER_PASS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initActivity(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void initActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null)
            if (getArgAddingNewAccount())
                loadRootFragment(R.id.fragment, LoginFragment.newInstance());
            else if (getArgAccountName().isPresent())
                loadRootFragment(R.id.fragment, LoginFragment.newInstance(getArgAccountName().get()));
            else
                loadRootFragment(R.id.fragment, LaunchFragment.newInstance());
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_launch;
    }


    private boolean getArgAddingNewAccount() {
        return getIntent().getBooleanExtra(ARG_IS_ADDING_NEW_ACCOUNT, false);
    }

    private Optional<String> getArgAccountName() {
        return getIntent().getStringExtra(ARG_ACCOUNT_NAME) == null ? Optional.empty() : Optional.of(getIntent().getStringExtra(ARG_ACCOUNT_NAME));

    }

    @Override
    public FragmentAnimator getFragmentAnimator() {
        return new DefaultNoAnimator();
    }

}
