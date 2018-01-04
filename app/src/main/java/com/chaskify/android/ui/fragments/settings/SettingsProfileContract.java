package com.chaskify.android.ui.fragments.settings;

import com.chaskify.android.shared.BaseContract;
import com.chaskify.android.ui.model.ProfileModel;

/**
 * Created by alberto on 3/01/18.
 */

public interface SettingsProfileContract {
    interface View extends BaseContract.View {

        void showProgress();

        void hideProgress();

        void showError(Throwable throwable);

        void renderProfile(ProfileModel profileModel);

        void complete();
    }

    interface Presenter extends BaseContract.Presenter<SettingsProfileContract.View> {
        void profile(String driver_id);

        void updateProfile(String newPhone);
    }
}
