package com.chaskify.android.ui.fragments.launch;

import android.support.annotation.NonNull;

import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.domain.interactors.CredentialsInteractor;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class LaunchPresenter extends BasePresenter<LaunchContract.View>
        implements LaunchContract.Presenter {

    private CredentialsInteractor credentialsInteractor;

    LaunchPresenter(CredentialsInteractor credentialsInteractor) {
        this.credentialsInteractor = credentialsInteractor;
    }

    @Override
    public void bindView(@NonNull LaunchContract.View view) {
        super.bindView(view);
        findHomeConfigurations();
    }

    @Override
    public void findHomeConfigurations() {
        addSubscription(
                credentialsInteractor
                        .getCredentials()
                        .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(homeServerConnectionConfigs -> {
                            /*if (homeServerConnectionConfigs.isEmpty())
                                view.showLogin();
                            else {
                                Stream.of(homeServerConnectionConfigs)
                                        .filter(value -> value.isDefault())
                                        .findFirst()
                                        .executeIfPresent(new Consumer<HomeServerConnectionConfig>() {
                                            @Override
                                            public void accept(HomeServerConnectionConfig homeServerConnectionConfig) {
                                                view.launch();
                                            }
                                        })
                                        .executeIfAbsent(new Runnable() {
                                            @Override
                                            public void run() {
                                                view.renderProfiles();
                                            }
                                        });
                            }*/
                        })
        );
    }
}
