package com.chaskify.android.ui.fragments.launch;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;
import com.chaskify.android.Chaskify;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.ui.model.CredentialsCacheItemModel;
import com.chaskify.android.shared.BasePresenter;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public class LaunchPresenter extends BasePresenter<LaunchContract.View>
        implements LaunchContract.Presenter {

    public LaunchPresenter() {
        Timber.tag(this.getClass().getSimpleName());
    }

    @Override
    public void bindView(@NonNull LaunchContract.View view) {
        super.bindView(view);
        synchronize();
    }

    private void synchronize() {
        addSubscription(Single.just(Chaskify.getInstance())
                .filter(chaskify -> chaskify.getSessions().isEmpty())
                .flatMapCompletable(Chaskify::synchronize)
                .doFinally(this::hasCredentials)
                .doOnSubscribe(disposable -> view.showProgress())
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    @Override
    public void hasCredentials() {
        addSubscription(Single.just(Chaskify.getInstance())
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(value -> {
                            if (value.getSessions().isEmpty()) {
                                Timber.d("::ChaskifyCredentials is empty::");
                                view.launchLogin();
                            } else {
                                value.getDefaultSession()
                                        .executeIfPresent(chaskifySession -> {
                                            Timber.d("::ChaskifyCredentials " + value.getDefaultSession().get().getCredentials().getUsername() + " as default::");
                                            view.launchSplash();
                                        })
                                        .executeIfAbsent(() -> {
                                            view.showProgress();
                                            Timber.d("::ChaskifyCredentials is present but not things is default::");
                                            view.renderCredentials(Stream.of(value.getSessions())
                                                    .map(chaskifySession ->
                                                            new CredentialsCacheItemModel()
                                                                    .setDriverId(chaskifySession.getCredentials().getDriverId())
                                                                    .setDriverUsername(chaskifySession.getCredentials().getUsername())
                                                    )
                                                    .toList());
                                            view.hideProgress();
                                        });
                            }
                        }
                ));
    }
}
