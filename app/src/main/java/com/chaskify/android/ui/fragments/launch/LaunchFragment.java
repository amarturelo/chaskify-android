package com.chaskify.android.ui.fragments.launch;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.ui.base.BaseFragment;
import com.chaskify.data.cache.impl.CredentialsCacheImpl;
import com.chaskify.data.repositories.RealmCredentialsRepositoryImpl;
import com.chaskify.domain.interactors.CredentialsInteractor;

import java.util.List;

public class LaunchFragment extends BaseFragment implements LaunchContract.View {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private LaunchPresenter launchPresenter;


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
                new CredentialsInteractor(
                        new RealmCredentialsRepositoryImpl(
                                new CredentialsCacheImpl()
                        )
                )
        );
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        startWithPop(LoginFragment.newInstance());
    }

    @Override
    public void launchSplash() {
        startWithPop(SplashFragment.newInstance());
    }

    public void renderCredentials(List<String> credentials) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

}
