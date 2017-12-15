package com.chaskify.android.ui.fragments.launch;

import com.chaskify.android.ui.model.ServerConfigurationListCacheModel;
import com.chaskify.android.shared.BaseContract;

import java.util.List;

class LaunchContract {
    interface View extends BaseContract.View {

        void launchLogin();

        void renderCredentials(List<ServerConfigurationListCacheModel> crendetials);

        void launchSplash();

        void showProgress();

        void hideProgress();
    }

    interface Presenter extends BaseContract.Presenter<LaunchContract.View> {
        void hasCredentials();
    }
}