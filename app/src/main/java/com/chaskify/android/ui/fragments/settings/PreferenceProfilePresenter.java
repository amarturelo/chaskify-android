package com.chaskify.android.ui.fragments.settings;

import com.chaskify.android.shared.BasePresenter;
import com.chaskify.domain.interactors.ProfileInteractor;

/**
 * Created by alberto on 3/01/18.
 */

public class PreferenceProfilePresenter extends BasePresenter<PreferenceProfileContract.View>
        implements PreferenceProfileContract.Presenter {

    private ProfileInteractor profileInteractor;

    public PreferenceProfilePresenter(ProfileInteractor profileInteractor) {
        this.profileInteractor = profileInteractor;
    }

    @Override
    public void profileInformation(String driver_id) {

    }
}
