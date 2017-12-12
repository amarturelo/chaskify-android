package com.chaskify.android.ui.fragments.launch;

import com.chaskify.android.shared.BaseContract;

class LaunchContract {
    interface View extends BaseContract.View {

        void showLogin();

        void launch();

        void renderProfiles();
    }

    interface Presenter extends BaseContract.Presenter<LaunchContract.View> {
        void findHomeConfigurations();
    }
}