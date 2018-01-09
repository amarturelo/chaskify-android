package com.chaskify.android.adapters.viewholder;

import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.android.ui.model.ProfileItemModel;
import com.chaskify.domain.interactors.ProfileInteractor;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by alberto on 8/01/18.
 */

public class CredentialsCacheItemPresenter extends BasePresenter<CredentialsCacheItemContract.View>
        implements CredentialsCacheItemContract.Presenter {

    private ProfileInteractor profileInteractor;

    public CredentialsCacheItemPresenter(ProfileInteractor profileInteractor) {
        this.profileInteractor = profileInteractor;
    }

    @Override
    public void profile(String driverId) {
        addSubscription(profileInteractor.profileByDriverId(driverId)
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .unsubscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(profile -> view.renderProfile(new ProfileItemModel()
                                .setmTeamName(profile.getTeamName())
                                .setmProfileImage(profile.getDriverPicture()))
                        , throwable -> view.showError(throwable))
        );
    }


}
