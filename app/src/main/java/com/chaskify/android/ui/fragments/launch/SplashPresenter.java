package com.chaskify.android.ui.fragments.launch;

import com.annimon.stream.Optional;
import com.chaskify.android.MethodCallHelper;
import com.chaskify.android.helper.LogIfError;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.data.realm.cache.impl.NotificationsCacheImpl;
import com.chaskify.data.realm.cache.impl.ProfileCacheImpl;
import com.chaskify.data.realm.cache.impl.SettingsCacheImpl;
import com.chaskify.data.realm.cache.impl.TaskCacheImpl;
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

    private MethodCallHelper mMethodCallHelper;

    private ChaskifySession mChaskifySession;

    public SplashPresenter(ChaskifySession chaskifySession, ProfileInteractor profileInteractor, SettingsInteractor settingsInteractor) {
        Timber.tag(this.getClass().getSimpleName());
        this.mChaskifySession = chaskifySession;
        this.profileInteractor = profileInteractor;
        this.settingsInteractor = settingsInteractor;

        mMethodCallHelper = new MethodCallHelper(mChaskifySession
                , new TaskCacheImpl()
                , new NotificationsCacheImpl()
                , new ProfileCacheImpl()
                , new SettingsCacheImpl()
        );
    }

    @Override
    public void init(String driverId) {
        view.showProgress();
        addSubscription(
                profileInteractor.profileByDriverId(driverId)
                        .firstOrError()
                        .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(profileOptional -> {
                            if (profileOptional.isPresent())
                                view.complete();
                            else
                                mMethodCallHelper.getProfile()
                                        .continueWith(new LogIfError());
                        }));
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
