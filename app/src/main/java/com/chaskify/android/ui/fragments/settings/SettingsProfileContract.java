package com.chaskify.android.ui.fragments.settings;

import android.graphics.Bitmap;

import com.chaskify.android.shared.BaseContract;
import com.chaskify.android.ui.model.ProfileModel;
import com.chaskify.android.ui.model.SettingsModel;

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

        void renderSettings(SettingsModel settingsModel);
    }

    interface Presenter extends BaseContract.Presenter<SettingsProfileContract.View> {
        void profile();

        void updateProfile(String newPhone);

        void updateProfileVehicle(String transportTypeTd
                , String transportDescription
                , String licencePlate
                , String color);

        void updateImageProfile(Bitmap bitmap);

        void logout();

        void updateSettingsPush(boolean enable);

        void updateSettingsSound(String sound);

        void settings();
    }
}
