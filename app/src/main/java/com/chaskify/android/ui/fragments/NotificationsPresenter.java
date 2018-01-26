package com.chaskify.android.ui.fragments;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.android.ui.model.NotificationItemModel;
import com.chaskify.domain.interactors.NotificationsInteractor;
import com.chaskify.domain.model.Notification;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

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
        Timber.d("::findNotificationsByDriverId " + driver_id + "::");

        addSubscription(notificationsInteractor
                .notifications(driver_id)
                .doFinally(() -> view.hideProgress())
                .doOnSubscribe(disposable -> view.showProgress())
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notifications -> view.renderNotifications(
                        Stream
                                .of(notifications)
                                .map(notification -> new NotificationItemModel()
                                        .setActions(notification.getActions())
                                        .setCustomerId(notification.getCustomerId())
                                        .setDriverId(notification.getDriverId())
                                        .setPushId(notification.getPushId())
                                        .setTaskId(notification.getTaskId())
                                        .setActions(notification.getActions())
                                        .setDateProcess(notification.getDateProcess())
                                        .setPushType(notification.getPushType())
                                        .setPushTitle(notification.getPushTitle())
                                        .setPushMessage(notification.getPushMessage()))
                                .toList())
                        , throwable -> view.showError(throwable)));

    }


    @Override
    public void bindView(@NonNull NotificationsContract.View view) {
        super.bindView(view);
    }
}
