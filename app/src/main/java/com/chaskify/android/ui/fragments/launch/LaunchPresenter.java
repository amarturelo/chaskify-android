package com.chaskify.android.ui.fragments.launch;

import android.support.annotation.NonNull;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.chaskify.android.Chaskify;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.model.ServerConfigurationListCacheModel;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.domain.interactors.CredentialsInteractor;
import com.chaskify.domain.model.Credentials;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public class LaunchPresenter extends BasePresenter<LaunchContract.View>
        implements LaunchContract.Presenter {

    public LaunchPresenter() {
    }

    @Override
    public void bindView(@NonNull LaunchContract.View view) {
        super.bindView(view);
        findCredentials();
    }

    @Override
    public void findCredentials() {
        addSubscription(Single.just(Chaskify.getInstance())
                .doOnSubscribe(disposable -> view.showProgress())

                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(value -> {
                            if (value.getSessions().isEmpty()) {
                                Timber.d("::ChaskifyCredentials is empty::");
                                view.launchLogin();
                            } else {
                                if (value.getDefaultSession().isPresent()) {
                                    Timber.d("::ChaskifyCredentials " + value.getDefaultSession().get().getCredentials().getUsername() + " as default::");
                                    view.launchSplash();
                                } else {
                                    Timber.d("::ChaskifyCredentials is present but not things is default::");
                                    view.renderCredentials(Stream.of(value.getSessions())
                                            .map(chaskifySession ->
                                                    new ServerConfigurationListCacheModel()
                                                            .setUserName(chaskifySession.getCredentials().getUsername())
                                            )
                                            .toList());
                                    view.hideProgress();
                                }
                            }
                        }
                ));
    }
}
