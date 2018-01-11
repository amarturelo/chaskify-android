package com.chaskify.android.ui.fragments.launch;

import com.annimon.stream.Optional;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.domain.interactors.ProfileInteractor;
import com.chaskify.domain.interactors.SettingsInteractor;
import com.chaskify.domain.model.Profile;
import com.chaskify.domain.model.Settings;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
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
        view.showProgress();
        addSubscription(Single.concat(
                profileInteractor.profileByDriverId(driverId)
                        .firstOrError()
                        .map(Optional::isPresent)
                , settingsInteractor.settingsByDriverId(driverId)
                        .firstOrError()
                        .map(Optional::isPresent))
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> {
                    view.hideProgress();
                    view.complete();
                })
                .subscribe(aBoolean -> Timber.d("::onNext " + aBoolean + " ::")
                        ));
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
