package com.chaskify.android.ui.fragments.launch;

import android.support.annotation.NonNull;

import com.chaskify.android.LoginHandler;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.domain.model.Credentials;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public class LoginPresenter extends BasePresenter<LoginContract.View>
        implements LoginContract.Presenter {

    private LoginHandler mLoginHandler;

    public LoginPresenter(LoginHandler loginHandler) {
        mLoginHandler = loginHandler;
    }

    @Override
    public void login(String username, String password) {
        Timber.d("::Authenticating credentials " + "Username:" + username + " Password:" + password + "::");
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

    private Single<Credentials> doLogin(String username, String password) {
        return Single
                .create(emitter -> mLoginHandler
                        .login(username, password, new ApiCallback<Credentials>() {
                            @Override
                            public void onSuccess(Credentials serverConfiguration) {
                                emitter.onSuccess(serverConfiguration);
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
                        }));
    }
}