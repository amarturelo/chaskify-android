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
        /*addSubscription(notificationsInteractor
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
                                        .setPushType(notification.getPushType())
                                        .setPushTitle(notification.getPushTitle())
                                        .setPushMessage(notification.getPushMessage()))
                                .toList())
                        , throwable -> view.showError(throwable)));*/
        List<NotificationItemModel> itemModels = new ArrayList<>();
        itemModels.add(new NotificationItemModel()
                .setDateCreated("2017-12-04 19:00:43")
                .setDateProcess("2017-12-04 19:00:43")
                .setDriverId("175")
                .setTaskId("0")
                .setActions("private")
                .setPushType("campaign")
                .setPushTitle("Lorem ipsum dolor sit amet")
                .setPushMessage("Lorem ipsum dolor sit amet, lorem ipsum dolor sit amet"));

        itemModels.add(new NotificationItemModel()
                .setDateCreated("2017-12-04 19:00:43")
                .setDateProcess("2017-12-04 19:00:43")
                .setDriverId("175")
                .setTaskId("3643")
                .setPushType("task")
                .setActions("ASSIGN_TASK")
                .setPushTitle("ASSIGN TASK")
                .setPushMessage("You have new assign task 3643"));

        view.hideProgress();
        view.renderNotifications(itemModels);
    }


    @Override
    public void bindView(@NonNull NotificationsContract.View view) {
        super.bindView(view);
    }
}
