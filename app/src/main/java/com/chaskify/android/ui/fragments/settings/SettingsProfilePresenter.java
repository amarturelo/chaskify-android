package com.chaskify.android.ui.fragments.settings;

import com.annimon.stream.Optional;
import com.chaskify.android.Chaskify;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.android.ui.model.mapper.ProfileModelDataMapper;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallbackSuccess;
import com.chaskify.domain.interactors.ProfileInteractor;
import com.chaskify.domain.model.Profile;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by alberto on 3/01/18.
 */

public class SettingsProfilePresenter extends BasePresenter<SettingsProfileContract.View>
        implements SettingsProfileContract.Presenter {

    private ProfileInteractor profileInteractor;

    private ChaskifySession mChaskifySession;

    public SettingsProfilePresenter(ProfileInteractor profileInteractor, ChaskifySession chaskifySession) {
        this.profileInteractor = profileInteractor;
        this.mChaskifySession = chaskifySession;
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
        addSubscription(doUpdateSettingsPush(enable)
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> view.logoutComplete())
                .subscribe(() -> view.complete(), throwable -> view.showError(throwable)));
    }

    private Completable doUpdateSettingsPush(boolean enable) {
        return Completable.create(emitter -> mChaskifySession.updateSettingsPush(enable, new ApiCallbackSuccess() {
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
    public void logout() {
        addSubscription(doLogout()
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .unsubscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> view.complete(), throwable -> view.showError(throwable)));
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
        addSubscription(doUpdateImageProfile(base64)
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .unsubscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> view.complete(), throwable -> view.showError(throwable)));
    }

    private Completable doUpdateImageProfile(String base64) {
        return Completable.create(emitter -> mChaskifySession.updateImageProfile(base64, new ApiCallbackSuccess() {
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
    public void updateProfileVehicle(String transportTypeTd, String transportDescription, String licencePlate, String color) {
        addSubscription(doUpdateProfileVehicle(transportTypeTd, transportDescription, licencePlate, color)
                .doOnSubscribe(disposable -> view.showProgress())
                .doFinally(() -> view.hideProgress())
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .unsubscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> view.complete(), throwable -> view.showError(throwable)));
    }

    private Completable doUpdateProfileVehicle(String transportTypeTd, String transportDescription, String licencePlate, String color) {
        return Completable.create(emitter -> mChaskifySession.updateVehicle(transportTypeTd, transportDescription, licencePlate, color, new ApiCallbackSuccess() {
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
    public void updateProfile(String newPhone) {
        addSubscription(doUpdateProfile(newPhone)
                .doOnSubscribe(disposable -> view.showProgress())
                .doFinally(() -> view.hideProgress())
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .unsubscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> view.complete(), throwable -> view.showError(throwable)));
    }

    private Completable doUpdateProfile(String newPhone) {
        return Completable.create(emitter -> mChaskifySession.updateProfile(newPhone, new ApiCallbackSuccess() {
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
    public void profile(String driverId) {
        addSubscription(profileInteractor
                .profileByDriverId(driverId)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .unsubscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success, error));
    }

    private Consumer<Throwable> error = throwable -> view
            .showError(throwable);
    private Consumer<Profile> success = profile -> view
            .renderProfile(ProfileModelDataMapper.transform(profile));
}
