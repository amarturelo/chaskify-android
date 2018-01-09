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

        void logoutComplete();
    }

    interface Presenter extends BaseContract.Presenter<SettingsProfileContract.View> {
        void profile(String driverId);

        void updateProfile(String newPhone);

        void updateProfileVehicle(String transportTypeTd
                , String transportDescription
                , String licencePlate
                , String color);

        void updateImageProfile(String base64);

        void logout();
    }
}