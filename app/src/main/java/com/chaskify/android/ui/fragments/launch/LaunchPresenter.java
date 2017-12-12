package com.chaskify.android.ui.fragments.launch;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.domain.interactors.CredentialsInteractor;
import com.chaskify.domain.model.Credentials;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class LaunchPresenter extends BasePresenter<LaunchContract.View>
        implements LaunchContract.Presenter {

    private CredentialsInteractor credentialsInteractor;

    LaunchPresenter(CredentialsInteractor credentialsInteractor) {
        this.credentialsInteractor = credentialsInteractor;
    }

    @Override
    public void bindView(@NonNull LaunchContract.View view) {
        super.bindView(view);
        findCredentials();
    }

    @Override
    public void findCredentials() {
        addSubscription(
                credentialsInteractor
                        .getCredentials()
                        .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> view.showProgress())
                        .doFinally(() -> view.hideProgress())
                        .subscribe(credentials -> {
                            if (credentials.isEmpty())
                                view.launchLogin();
                            else if (Stream.of(credentials)
                                    .filter(Credentials::isDefault)
                                    .findFirst()
                                    .isPresent())
                                view.launchSplash();
                            else
                                view.renderCredentials(Stream.of(credentials)
                                        .map(Credentials::getUsername)
                                        .toList());
                        })
        );
    }
}
