package com.chaskify.android.ui.fragments.settings;

import com.chaskify.android.shared.BaseContract;
import com.chaskify.android.ui.model.ProfileModel;

/**
 * Created by alberto on 3/01/18.
 */

public interface PreferenceProfileContract {
    interface View extends BaseContract.View {

        void showProgress();

        void hideProgress();

        void showError(Throwable throwable);

        void renderProfile(ProfileModel profileModel);
    }

    interface Presenter extends BaseContract.Presenter<PreferenceProfileContract.View> {
        void profileInformation(String driver_id);
    }
}
