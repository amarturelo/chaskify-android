package com.chaskify.android.ui.fragments.launch;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.navigation.Navigator;
import com.chaskify.android.ui.base.BaseFragment;
import com.chaskify.data.cache.impl.CredentialsCacheImpl;
import com.chaskify.data.repositories.RealmCredentialsRepositoryImpl;
import com.chaskify.domain.interactors.CredentialsInteractor;
import com.chaskify.domain.model.Credentials;

import java.util.List;

import timber.log.Timber;

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
        Timber.d("::Launch login::");
        startWithPop(LoginFragment.newInstance());
    }

    public void renderCredentials(List<String> credentials) {
        Timber.d("::Render credentials " + credentials.toString() + "::");

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void launchLogin(Credentials credentials) {
        Timber.d("::Launch login whit credential " + credentials.getUsername() + "::");
        start(LoginFragment.newInstance(credentials));
    }

    @Override
    public void launchSplash() {
        getActivity().finish();
    }

    @Override
    public void hideProgress() {

    }

}
