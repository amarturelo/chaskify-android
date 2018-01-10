package com.chaskify.android.ui.fragments.launch;

import com.chaskify.android.shared.BaseContract;

/**
 * Created by alberto on 12/12/17.
 */

public class SplashContract {
    interface View extends BaseContract.View {
        void showProgressStatus(String status);

        void showProgress();

        void hideProgress();

        void showError(Throwable throwable);
    }

    interface Presenter extends BaseContract.Presenter<SplashContract.View> {
        void profile(String driverId);

        void settings(String driverId);

        void init(String driverId);
    }
}
