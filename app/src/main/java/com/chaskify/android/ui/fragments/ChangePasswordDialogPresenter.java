package com.chaskify.android.ui.fragments;

import com.chaskify.android.LoginHandler;
import com.chaskify.android.shared.BasePresenter;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import timber.log.Timber;

/**
 * Created by alberto on 3/01/18.
 */

public class ChangePasswordDialogPresenter extends BasePresenter<ChangePasswordDialogContract.View>
        implements ChangePasswordDialogContract.Presenter {

    private LoginHandler mLoginHandler;

    public ChangePasswordDialogPresenter(LoginHandler loginHandler) {
        this.mLoginHandler = loginHandler;
    }

    @Override
    public void changePassword(String newPassword, String confirmNewPassword) {
        Timber.d("::Authenticating credentials " + "NewPassword:" + newPassword + " ConfirmNewPassword:" + confirmNewPassword + "::");

    }

    private Completable doChangePassword(String newPassword, String confirmNewPassword) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter e) throws Exception {
                //mLoginHandler.changePassword(newPassword, confirmNewPassword);
            }
        });
    }
}
