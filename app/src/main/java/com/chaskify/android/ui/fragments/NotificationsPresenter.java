package com.chaskify.android.ui.fragments;

import android.support.annotation.NonNull;

import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.domain.interactors.NotificationsInteractor;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by alberto on 17/12/17.
 */

public class NotificationsPresenter extends BasePresenter<NotificationsContract.View>
        implements NotificationsContract.Presenter {

    private NotificationsInteractor notificationsInteractor;

    public NotificationsPresenter(NotificationsInteractor notificationsInteractor) {
        this.notificationsInteractor = notificationsInteractor;
    }

    @Override
    public void notificationsByDriverId(String driver_id) {
        addSubscription(notificationsInteractor
                .notifications(driver_id)
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notifications -> view.renderNotifications(notifications)
                        , throwable -> view.showError(throwable)));
    }

    @Override
    public void bindView(@NonNull NotificationsContract.View view) {
        super.bindView(view);
    }
}
