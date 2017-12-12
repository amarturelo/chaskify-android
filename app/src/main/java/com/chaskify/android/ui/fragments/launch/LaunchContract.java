package com.chaskify.android.ui.fragments.launch;

import com.chaskify.android.shared.BaseContract;
import com.chaskify.domain.model.Credentials;

import java.util.List;

class LaunchContract {
    interface View extends BaseContract.View {

        void launchLogin();

        void launchSplash();

        void renderCredentials(List<String> crendetials);

        void showProgress();

        void hideProgress();

        void launchLogin(Credentials credentials);
    }

    interface Presenter extends BaseContract.Presenter<LaunchContract.View> {
        void findCredentials();
    }
}