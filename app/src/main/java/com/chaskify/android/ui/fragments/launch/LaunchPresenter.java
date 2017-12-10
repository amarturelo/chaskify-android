package com.chaskify.android.ui.fragments.launch;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Consumer;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.domain.interactors.HomeServerConfigurationInteractor;
import com.chaskify.domain.model.HomeServerConnectionConfig;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by alberto on 10/12/17.
 */

public class LaunchPresenter extends BasePresenter<LaunchContract.View>
        implements LaunchContract.Presenter {

    private HomeServerConfigurationInteractor homeServerConfigurationInteractor;

    LaunchPresenter(HomeServerConfigurationInteractor homeServerConfigurationInteractor) {
        this.homeServerConfigurationInteractor = homeServerConfigurationInteractor;
    }

    @Override
    public void bindView(@NonNull LaunchContract.View view) {
        super.bindView(view);
        findHomeConfigurations();
    }

    @Override
    public void findHomeConfigurations() {
        addSubscription(
                homeServerConfigurationInteractor
                        .homeServerConnectionConfigs()
                        .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(homeServerConnectionConfigs -> {
                            if (homeServerConnectionConfigs.isEmpty())
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
                            }
                        })
        );
    }
}
