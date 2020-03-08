package com.chaskify.android.ui.fragments;

import com.chaskify.android.shared.BaseContract;

/**
 * Created by alberto on 3/01/18.
 */

public interface ChangePasswordDialogContract {
    interface View extends BaseContract.View {

        void showProgress();

        void hideProgress();

        void showError(Throwable throwable);

        void complete();
    }

    interface Presenter extends BaseContract.Presenter<ChangePasswordDialogContract.View> {
        void changePassword(String currentPassword, String newPassword, String confirmNewPassword);
    }
}
