package com.chaskify.android.ui.fragments.launch;

import com.annimon.stream.Optional;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.domain.interactors.ProfileInteractor;
import com.chaskify.domain.interactors.SettingsInteractor;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by alberto on 12/12/17.
 */

public class SplashPresenter extends BasePresenter<SplashContract.View>
        implements SplashContract.Presenter {

    private ProfileInteractor profileInteractor;

    private SettingsInteractor settingsInteractor;

    public SplashPresenter(ProfileInteractor profileInteractor, SettingsInteractor settingsInteractor) {
        this.profileInteractor = profileInteractor;
        this.settingsInteractor = settingsInteractor;
    }

    @Override
    public void init(String driverId) {
        addSubscription(Single.concatArray(profileInteractor
                        .profileByDriverId(driverId)
                        .firstOrError()
                        .doOnSubscribe(subscription -> view.showProgressStatus("Loading profile"))
                , settingsInteractor
                        .settingsByDriverId(driverId)
                        .firstOrError()
                        .doOnSubscribe(subscription -> view.showProgressStatus("Loading settings"))
        )
                .map(Optional::get)
                .subscribe(o -> view.complete()
                        , throwable -> view.showError(throwable)));
    }

    @Override
    public void settings(String driverId) {
        addSubscription(settingsInteractor
                .settingsByDriverId(driverId)
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    @Override
    public void profile(String driverId) {
        addSubscription(profileInteractor.profileByDriverId(driverId)
                .filter(Optional::isPresent)
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }
}
