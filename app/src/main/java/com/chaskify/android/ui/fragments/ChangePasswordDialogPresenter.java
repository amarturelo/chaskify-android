package com.chaskify.android.ui.fragments;

import com.chaskify.android.Chaskify;
import com.chaskify.android.LoginHandler;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.service.ChaskifyService;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallbackSuccess;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by alberto on 3/01/18.
 */

public class ChangePasswordDialogPresenter extends BasePresenter<ChangePasswordDialogContract.View>
        implements ChangePasswordDialogContract.Presenter {

    private ChaskifySession mChaskifySession;

    public ChangePasswordDialogPresenter(ChaskifySession chaskifySession) {
        mChaskifySession = chaskifySession;
    }

    private Completable doChangePassword(String currentPassword, String newPassword, String confirmNewPassword) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                mChaskifySession.updatePassword(currentPassword, newPassword, confirmNewPassword, new ApiCallbackSuccess() {
                    @Override
                    public void onSuccess() {
                        emitter.onComplete();
                    }

                    @Override
                    public void onNetworkError(Exception e) {
                        emitter.onError(e);
                    }

                    @Override
                    public void onChaskifyError(Exception e) {
                        emitter.onError(e);
                    }

                    @Override
                    public void onUnexpectedError(Exception e) {
                        emitter.onError(e);
                    }
                });
            }
        });
    }

    @Override
    public void changePassword(String currentPassword, String newPassword, String confirmNewPassword) {
        addSubscription(doChangePassword(currentPassword, newPassword, confirmNewPassword)
                .doOnSubscribe(disposable -> view.showProgress())
                .doFinally(() -> view.hideProgress())
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .unsubscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> view.complete(), throwable -> view.showError(throwable)));
    }
}
