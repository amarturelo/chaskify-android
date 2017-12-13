package com.chaskify.android.ui.fragments.launch;

import com.chaskify.android.shared.BaseContract;

/**
 * Created by alberto on 12/12/17.
 */

public class SplashContract {
    interface View extends BaseContract.View {
        void showError(Exception e);
    }

    interface Presenter extends BaseContract.Presenter<SplashContract.View> {
    }
}
