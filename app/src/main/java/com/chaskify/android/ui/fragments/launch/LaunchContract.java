package com.chaskify.android.ui.fragments.launch;

import com.chaskify.android.shared.BaseContract;
import com.chaskify.android.ui.model.ProfileItemModel;

import java.util.List;

class LaunchContract {
    interface View extends BaseContract.View {

        void launchLogin();

        void renderCredentials(List<ProfileItemModel> profileItemModels);

        void launchSplash();

        void showProgress();

        void hideProgress();
    }

    interface Presenter extends BaseContract.Presenter<LaunchContract.View> {
        void hasCredentials();

        void remove(String driverId);
    }
}