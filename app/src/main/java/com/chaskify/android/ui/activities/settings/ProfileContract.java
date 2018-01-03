package com.chaskify.android.ui.activities.settings;

import com.chaskify.android.shared.BaseContract;
import com.chaskify.android.ui.model.ProfileModel;

/**
 * Created by alberto on 29/12/17.
 */

public class ProfileContract {
    interface View extends BaseContract.View {

        void showProgress();

        void hideProgress();

        void showError(Throwable throwable);

        void renderProfile(ProfileModel taskModel);
    }

    interface Presenter extends BaseContract.Presenter<ProfileContract.View> {
        void findProfile();
    }
}
