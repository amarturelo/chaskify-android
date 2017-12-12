package com.chaskify.android.ui.fragments.launch;

import android.support.annotation.NonNull;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.chaskify.android.Chaskify;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.domain.interactors.CredentialsInteractor;
import com.chaskify.domain.model.Credentials;

import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

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
                            if (credentials.isEmpty()) {
                                Timber.d("::Credentials is empty::");
                                view.launchLogin();
                            } else {
                                Optional<Credentials> optional = Stream.of(credentials)
                                        .filter(Credentials::isDefault)
                                        .findFirst();
                                if (optional.isPresent()) {
                                    Timber.d("::Credentials " + optional.get().getUsername() + " as default::");
                                    if (Chaskify.getInstance() != null && Chaskify.getInstance().getChaskifySession() != null)
                                        view.launchSplash();
                                    else
                                        view.launchLogin(optional.get());
                                } else {
                                    Timber.d("::Credentials is present but not things is default::");
                                    view.renderCredentials(Stream.of(credentials)
                                            .map(Credentials::getUsername)
                                            .toList());
                                }
                            }
                        })
        );
    }
}
