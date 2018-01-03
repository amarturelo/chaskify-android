package com.chaskify.android.ui.activities.settings;

import android.support.annotation.NonNull;

import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.android.ui.model.mapper.ProfileModelDataMapper;
import com.chaskify.domain.interactors.ProfileInteractor;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by alberto on 29/12/17.
 */

public class ProfilePresenter extends BasePresenter<ProfileContract.View>
        implements ProfileContract.Presenter {

    private ProfileInteractor profileInteractor;

    public ProfilePresenter(ProfileInteractor profileInteractor) {
        this.profileInteractor = profileInteractor;
    }

    @Override
    public void bindView(@NonNull ProfileContract.View view) {
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
