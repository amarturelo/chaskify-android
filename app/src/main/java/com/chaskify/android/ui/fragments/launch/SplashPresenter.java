package com.chaskify.android.ui.fragments.launch;

import com.annimon.stream.Optional;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.domain.interactors.ProfileInteractor;
import com.chaskify.domain.interactors.SettingsInteractor;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import timber.log.Timber;

/**
 * Created by alberto on 12/12/17.
 */

public class SplashPresenter extends BasePresenter<SplashContract.View>
        implements SplashContract.Presenter {

    private ProfileInteractor profileInteractor;

    private SettingsInteractor settingsInteractor;

    public SplashPresenter(ProfileInteractor profileInteractor, SettingsInteractor settingsInteractor) {
        Timber.tag(this.getClass().getSimpleName());
        this.profileInteractor = profileInteractor;
        this.settingsInteractor = settingsInteractor;
    }

    @Override
    public void init(String driverId) {
        addSubscription(Flowable.concat(profileInteractor.profileByDriverId(driverId)
                        .map(Optional::isPresent)
                , settingsInteractor.settingsByDriverId(driverId)
                        .map(Optional::isPresent))
                .doOnSubscribe(subscription -> view.showProgress())
                .doOnComplete(() -> {
                    view.hideProgress();
                    view.complete();
                })
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> Timber.d("::onNext " + aBoolean + " ::")
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
