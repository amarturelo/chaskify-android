package com.chaskify.android.ui.activities.settings;

import android.support.annotation.NonNull;

import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.android.ui.model.ProfileModel;
import com.chaskify.android.ui.model.mapper.ProfileModelDataMapper;
import com.chaskify.domain.interactors.ProfileInteractor;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by alberto on 29/12/17.
 */

public class SettingsProfilePresenter extends BasePresenter<SettingsProfileContract.View>
        implements SettingsProfileContract.Presenter {

    private ProfileInteractor profileInteractor;

    public SettingsProfilePresenter(ProfileInteractor profileInteractor) {
        this.profileInteractor = profileInteractor;
    }

    @Override
    public void bindView(@NonNull SettingsProfileContract.View view) {
        super.bindView(view);
        findProfile();
    }

    @Override
    public void findProfile() {
        addSubscription(profileInteractor
                .profile()
                .doFinally(() -> view.hideProgress())
                .doOnSubscribe(disposable -> view.showProgress())
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(profile -> view.renderProfile(ProfileModelDataMapper.transform(profile))
                        , throwable -> view.showError(throwable)));
    }
}
