package com.chaskify.android.ui.fragments.launch;

import android.support.annotation.NonNull;

import com.chaskify.android.LoginHandler;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.chaskify_sdk.ProfileConnectionConfig;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyError;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class LoginPresenter extends BasePresenter<LoginContract.View>
        implements LoginContract.Presenter {

    private LoginHandler mLoginHandler;

    public LoginPresenter(LoginHandler loginHandler) {
        mLoginHandler = loginHandler;
    }

    @Override
    public void login(String username, String password) {
        addSubscription(doLogin(username, password)
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> view.showProgress())
                .doFinally(() -> view.hideProgress())
                .subscribe(
                        value -> view.loginComplete()
                        , throwable -> view.showError((Exception) throwable)));
    }

    @Override
    public void bindView(@NonNull LoginContract.View view) {
        super.bindView(view);
    }

    private Single<ProfileConnectionConfig> doLogin(String username, String password) {
        return Single
                .create(emitter -> mLoginHandler
                        .login(username, password, new ApiCallback<ProfileConnectionConfig>() {
                            @Override
                            public void onSuccess(ProfileConnectionConfig profileConnectionConfig) {
                                emitter.onSuccess(profileConnectionConfig);
                            }

                            @Override
                            public void onNetworkError(Exception e) {
                                emitter.onError(e);
                            }

                            @Override
                            public void onChaskifyError(ChaskifyError e) {

                            }

                            @Override
                            public void onUnexpectedError(Exception e) {
                                emitter.onError(e);
                            }
                        }));
    }
}