package com.chaskify.android.ui.fragments.launch;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.annimon.stream.Optional;
import com.annimon.stream.function.Consumer;
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

public class SplashFragment extends BaseFragment implements SplashContract.View {

    private SplashPresenter presenter;

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

        Chaskify.getInstance().getDefaultSession()
                .executeIfAbsent(this::goToLaunch)
                .ifPresent(chaskifySession -> {
                    this.mChaskifySession = Chaskify.getInstance().getDefaultSession();
                    presenter = new SplashPresenter(new ProfileInteractor(
                            new ProfileRepositoryImpl(
                                    new ProfileCacheImpl()
                                    , mChaskifySession.get().getProfileRestClient()
                            )
                    )
                            , new SettingsInteractor(
                            new SettingsRepositoryImpl(
                                    new SettingsCacheImpl()
                                    , mChaskifySession.get().getSettingsRestClient()
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
        presenter.release();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progress);

        mChaskifySession.ifPresent(chaskifySession -> {
                    presenter.bindView(this);
                    presenter.init(chaskifySession.getCredentials().getDriverId());
                }
        );
    }

    @Override
    public void showProgressStatus(String status) {
        Timber.d("::Status Splash " + status + "::");
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable throwable) {
        Timber.d(throwable);
    }

    @Override
    public void complete() {
        Navigator.goToMainActivity(getActivity());
        getActivity().finish();
    }
}
