package com.chaskify.android.ui.fragments.settings;

import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.android.ui.model.mapper.ProfileModelDataMapper;
import com.chaskify.domain.interactors.ProfileInteractor;
import com.chaskify.domain.model.Profile;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by alberto on 3/01/18.
 */

public class SettingsProfilePresenter extends BasePresenter<SettingsProfileContract.View>
        implements SettingsProfileContract.Presenter {

    private ProfileInteractor profileInteractor;

    public SettingsProfilePresenter(ProfileInteractor profileInteractor) {
        this.profileInteractor = profileInteractor;
    }

    @Override
    public void profile(String driver_id) {
        addSubscription(profileInteractor
                .profileByDriverId(driver_id)
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
