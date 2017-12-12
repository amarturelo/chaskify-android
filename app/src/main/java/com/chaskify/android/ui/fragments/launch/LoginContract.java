package com.chaskify.android.ui.fragments.launch;

import com.chaskify.android.shared.BaseContract;

public class LoginContract {
    interface View extends BaseContract.View {


        void loginComplete();

        void showError(Exception e);

        void showProgress();

        void hideProgress();
    }

    interface Presenter extends BaseContract.Presenter<LoginContract.View> {
        void login(String username, String password);
    }
}