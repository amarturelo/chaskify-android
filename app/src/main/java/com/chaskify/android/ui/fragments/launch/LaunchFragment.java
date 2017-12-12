package com.chaskify.android.ui.fragments.launch;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.ui.base.BaseFragment;

import timber.log.Timber;

public class LaunchFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public LaunchFragment() {
        // Required empty public constructor
        Timber.tag(this.getClass().getSimpleName());
    }


    // TODO: Rename and change types and number of parameters
    public static LaunchFragment newInstance() {
        LaunchFragment fragment = new LaunchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        hasCredentials();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_launch;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void renderProfiles() {

    }

    /**
     * @return true if some credentials have been saved.
     */
    private void hasCredentials() {
        /*if (!Chaskify.getMXSessions().isEmpty()) {
            if (Chaskify.getInstance().getDefaultSession() != null)
                goToSplash();
            else
                renderProfiles();
        } else {
            goToLogin();
        }*/
    }

    private void goToLogin() {
        startWithPop(LoginFragment.newInstance("", ""));

    }

    private void goToSplash() {
        startWithPop(SplashFragment.newInstance("", ""));
    }
}
