package com.chaskify.android.ui.fragments.launch;

import com.chaskify.android.ui.model.CredentialsCacheItemModel;
import com.chaskify.android.shared.BaseContract;

import java.util.List;

class LaunchContract {
    interface View extends BaseContract.View {

        void launchLogin();

        void renderCredentials(List<CredentialsCacheItemModel> driversId);

        void launchSplash();

        void showProgress();

        void hideProgress();
    }

    interface Presenter extends BaseContract.Presenter<LaunchContract.View> {
        void hasCredentials();
    }
}