package com.chaskify.android.ui.fragments.launch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.annimon.stream.Optional;
import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.navigation.Navigator;
import com.chaskify.android.service.ChaskifyService;
import com.chaskify.android.ui.base.BaseFragment;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.data.realm.cache.impl.ProfileCacheImpl;
import com.chaskify.data.realm.cache.impl.SettingsCacheImpl;
import com.chaskify.data.repositories.ProfileRepositoryImpl;
import com.chaskify.data.repositories.SettingsRepositoryImpl;
import com.chaskify.domain.interactors.ProfileInteractor;
import com.chaskify.domain.interactors.SettingsInteractor;

import timber.log.Timber;

public class SplashFragment extends BaseFragment implements SplashContract.View, View.OnClickListener {

    private SplashPresenter presenter;
    private View mActionTryAgain;

    public SplashFragment() {
        Timber.d(this.getClass().getSimpleName());
    }

    private Optional<ChaskifySession> mChaskifySession;

    private ProgressBar progressBar;

    public static SplashFragment newInstance() {
        SplashFragment fragment = new SplashFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mChaskifySession = Chaskify.getInstance().getDefaultSession();
        mChaskifySession
                .executeIfAbsent(this::goToLaunch)
                .ifPresent(chaskifySession -> {
                    presenter = new SplashPresenter(mChaskifySession.get(), new ProfileInteractor(
                            new ProfileRepositoryImpl(
                                    new ProfileCacheImpl()
                            )
                    )
                            , new SettingsInteractor(
                            new SettingsRepositoryImpl(
                                    new SettingsCacheImpl()
                            )
                    ));
                });
    }

    private void goToLaunch() {
        startWithPop(LaunchFragment.newInstance());
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_splash;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (presenter != null)
            presenter.release();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progress);
        mActionTryAgain = view.findViewById(R.id.action_retry);
        mActionTryAgain.setOnClickListener(this);
        mChaskifySession.ifPresent(chaskifySession -> {
                    presenter.bindView(this);
                    actionRetry();
                }
        );
    }

    @Override
    public void showProgressStatus(String status) {
        Timber.d("::Status Splash " + status + "::");
    }

    @Override
    public void showProgress() {
        mActionTryAgain.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable throwable) {
        hideProgress();
        Toast.makeText(getActivity(), throwable.toString(), Toast.LENGTH_LONG).show();
        mActionTryAgain.setVisibility(View.VISIBLE);
    }

    @Override
    public void complete() {
        if (ChaskifyService.getInstance() == null)
            ChaskifyService.start(getContext(), Chaskify.getInstance().getDefaultSession().get().getCredentials().getDriverId());
        Navigator.goToMainActivity(getActivity());
        //getActivity().finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_retry:
                actionRetry();
        }
    }

    private void actionRetry() {
        mChaskifySession.ifPresent(chaskifySession -> presenter.init(chaskifySession.getCredentials().getDriverId()));
    }
}
