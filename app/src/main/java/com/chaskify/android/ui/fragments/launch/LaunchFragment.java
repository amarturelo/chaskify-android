package com.chaskify.android.ui.fragments.launch;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chaskify.android.R;
import com.chaskify.android.ui.model.ServerConfigurationListCacheModel;
import com.chaskify.android.ui.base.BaseFragment;
import com.chaskify.android.ui.widget.MultiStateView;

import java.util.List;

import timber.log.Timber;

public class LaunchFragment extends BaseFragment implements LaunchContract.View {

    private LaunchPresenter launchPresenter;

    private MultiStateView multiStateView;

    public LaunchFragment() {
        // Required empty public constructor
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
        launchPresenter = new LaunchPresenter(
        );
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        multiStateView = view.findViewById(R.id.multistate_profile_cache);
        launchPresenter.bindView(this);
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
        launchPresenter.release();
    }

    @Override
    public void launchLogin() {
        Timber.d("::Launch login::");
        startWithPop(LoginFragment.newInstance());
    }

    public void renderCredentials(List<ServerConfigurationListCacheModel> credentials) {
        Timber.d("::Render credentials " + credentials.toString() + "::");
    }

    @Override
    public void launchSplash() {
        startWithPop(SplashFragment.newInstance());
        getActivity().finish();
    }

    @Override
    public void showProgress() {
        multiStateView.setVisibility(View.VISIBLE);
        multiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
    }

    @Override
    public void hideProgress() {
        multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
    }




}
