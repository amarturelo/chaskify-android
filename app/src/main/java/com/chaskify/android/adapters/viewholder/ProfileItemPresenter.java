package com.chaskify.android.adapters.viewholder;

import com.annimon.stream.Optional;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.android.ui.model.ProfileItemModel;
import com.chaskify.domain.interactors.ProfileInteractor;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by alberto on 8/01/18.
 */

public class ProfileItemPresenter extends BasePresenter<ProfileItemContract.View>
        implements ProfileItemContract.Presenter {

    private ProfileInteractor profileInteractor;

    public ProfileItemPresenter(ProfileInteractor profileInteractor) {
        this.profileInteractor = profileInteractor;
    }

    @Override
    public void profile(String driverId) {
        addSubscription(profileInteractor.profileByDriverId(driverId)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(profile -> view.renderProfile(new ProfileItemModel()
                                .setTeamName(profile.getTeamName())
                                .setProfileImage(profile.getDriverPicture()))
                        , throwable -> view.showError(throwable))
        );
    }


}
