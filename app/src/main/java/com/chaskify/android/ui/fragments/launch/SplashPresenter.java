package com.chaskify.android.ui.fragments.launch;

import com.chaskify.android.helper.MethodCallHelper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.data.realm.cache.impl.NotificationsCacheImpl;
import com.chaskify.data.realm.cache.impl.ProfileCacheImpl;
import com.chaskify.data.realm.cache.impl.SettingsCacheImpl;
import com.chaskify.data.realm.cache.impl.TaskCacheImpl;
import com.chaskify.data.realm.cache.impl.TaskWayPointCacheImpl;
import com.chaskify.domain.interactors.ProfileInteractor;
import com.chaskify.domain.interactors.SettingsInteractor;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
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
                , new TaskWayPointCacheImpl()
        );
    }

    @Override
    public void init(String driverId) {
        view.showProgress();

        Single.zip(
                settings(driverId)
                , profile(driverId)

                , (asProfile, asSettings) -> asProfile && asSettings
        )
                .subscribe(aBoolean -> {
                    if (aBoolean)
                        view.complete();
                    else
                        view.showError(new Exception("paso algo con la red"));
                }, throwable -> view.showError(throwable))
        ;
    }

    private Single<Boolean> settings(String driverId) {
        return settingsInteractor.settingsByDriverId(driverId)
                .firstOrError()
                .toFlowable()
                .switchMap(settingsOptional -> {
                    if (settingsOptional.isPresent())
                        return Flowable.just(true);
                    return Single.create((SingleOnSubscribe<Boolean>) emitter -> mMethodCallHelper.getSettings()
                            .continueWith(task -> {
                                if (task.isCompleted())
                                    emitter.onSuccess(true);
                                else
                                    emitter.onError(task.getError());
                                return null;
                            }))
                            .toFlowable();
                })
                .firstOrError()
                ;
    }

    private Single<Boolean> profile(String driverId) {
        return profileInteractor.profileByDriverId(driverId)
                .firstOrError()
                .toFlowable()
                .switchMap(profileOptional -> {
                    if (profileOptional.isPresent())
                        return Flowable.just(true);
                    else
                        return Single.create((SingleOnSubscribe<Boolean>) emitter -> mMethodCallHelper.getProfile()
                                .continueWith(task -> {
                                    if (task.isCompleted())
                                        emitter.onSuccess(true);
                                    else
                                        emitter.onError(task.getError());
                                    return null;
                                }))
                                .toFlowable();
                })
                .firstOrError()
                ;
    }

}
