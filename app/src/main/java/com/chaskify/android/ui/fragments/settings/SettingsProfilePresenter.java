package com.chaskify.android.ui.fragments.settings;

import com.annimon.stream.Optional;
import com.chaskify.android.Chaskify;
import com.chaskify.android.helper.MethodCallHelper;
import com.chaskify.android.helper.LogIfError;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.android.ui.model.SettingsModel;
import com.chaskify.android.ui.model.mapper.ProfileModelDataMapper;
import com.chaskify.android.ui.model.mapper.SettingsModelDataMapper;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallbackSuccess;
import com.chaskify.chaskify_sdk.rest.model.ChaskifySettings;
import com.chaskify.data.realm.cache.impl.NotificationsCacheImpl;
import com.chaskify.data.realm.cache.impl.ProfileCacheImpl;
import com.chaskify.data.realm.cache.impl.SettingsCacheImpl;
import com.chaskify.data.realm.cache.impl.TaskCacheImpl;
import com.chaskify.data.realm.cache.impl.TaskWayPointCacheImpl;
import com.chaskify.domain.interactors.ProfileInteractor;
import com.chaskify.domain.interactors.SettingsInteractor;

import bolts.Continuation;
import bolts.Task;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by alberto on 3/01/18.
 */

public class SettingsProfilePresenter extends BasePresenter<SettingsProfileContract.View>
        implements SettingsProfileContract.Presenter {

    private ProfileInteractor profileInteractor;

    private SettingsInteractor settingsInteractor;

    private ChaskifySession mChaskifySession;

    private MethodCallHelper mMethodCallHelper;

    public SettingsProfilePresenter(ProfileInteractor profileInteractor, SettingsInteractor settingsInteractor, ChaskifySession mChaskifySession) {
        this.profileInteractor = profileInteractor;
        this.settingsInteractor = settingsInteractor;
        this.mChaskifySession = mChaskifySession;

        mMethodCallHelper = new MethodCallHelper(mChaskifySession
                , new TaskCacheImpl()
                , new NotificationsCacheImpl()
                , new ProfileCacheImpl()
                , new SettingsCacheImpl()
                , new TaskWayPointCacheImpl()
        );
    }

    @Override
    public void updateSettingsSound(String sound) {
        addSubscription(doUpdateSettingsSound(sound)
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> view.complete(), throwable -> view.showError(throwable)));
    }

    private Completable doUpdateSettingsSound(String sound) {
        return Completable.create(emitter -> mChaskifySession.updateSettingsSound(sound, new ApiCallbackSuccess() {
            @Override
            public void onSuccess() {
                emitter.onComplete();
            }

            @Override
            public void onNetworkError(Exception e) {
                emitter.onError(e);
            }

            @Override
            public void onChaskifyError(Exception e) {
                emitter.onError(e);
            }

            @Override
            public void onUnexpectedError(Exception e) {
                emitter.onError(e);
            }
        }));
    }

    @Override
    public void updateSettingsPush(boolean enable) {
        mMethodCallHelper
                .updateSettings(enable)
                .continueWith(task -> {
                    settings();
                    return null;
                })
                .continueWith(new LogIfError());
    }

    @Override
    public void logout() {
        addSubscription(doLogout()
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .unsubscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> view.logoutComplete())
                .subscribe(() -> {
                }, throwable -> view.showError(throwable)));
    }

    private Completable doLogout() {
        return Completable.create(emitter -> Chaskify.getInstance().logout(mChaskifySession, new ApiCallbackSuccess() {
            @Override
            public void onSuccess() {
                emitter.onComplete();
            }

            @Override
            public void onNetworkError(Exception e) {
                emitter.onError(e);
            }

            @Override
            public void onChaskifyError(Exception e) {
                emitter.onError(e);
            }

            @Override
            public void onUnexpectedError(Exception e) {
                emitter.onError(e);
            }
        }));
    }

    @Override
    public void updateImageProfile(String base64) {
        mMethodCallHelper.updateProfileImage(base64)
                .continueWith(new LogIfError());
    }

    @Override
    public void updateProfileVehicle(String transportTypeTd, String transportDescription, String licencePlate, String color) {
        mMethodCallHelper
                .updateProfileVehicle(transportTypeTd, transportDescription, licencePlate, color)
                .continueWith(new LogIfError());
    }

    @Override
    public void updateProfile(String newPhone) {
        mMethodCallHelper.updateProfile(newPhone)
                .continueWith(new LogIfError());
    }

    @Override
    public void profile() {
        mMethodCallHelper.getProfile()
                .continueWith(new LogIfError());
        addSubscription(profileInteractor
                .profileByDriverId(mChaskifySession.getCredentials().getDriverId())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(profile -> view.renderProfile(ProfileModelDataMapper.transform(profile)), error));
    }

    @Override
    public void settings() {
        mMethodCallHelper.getSettings()
                .onSuccessTask((Continuation<ChaskifySettings, Task<Void>>) task -> {
                    view.renderSettings(new SettingsModel()
                            .setEnabledPush(task.getResult().getEnabledPush().equals("1")));
                    return null;
                });
    }

    private Consumer<Throwable> error = throwable -> view
            .showError(throwable);

}
