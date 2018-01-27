package com.chaskify.data.repositories;

import com.annimon.stream.Stream;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.client.NotificationRestClient;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyNotification;
import com.chaskify.data.realm.model.RealmNotification;
import com.chaskify.data.realm.cache.NotificationsCache;
import com.chaskify.domain.model.Notification;
import com.chaskify.domain.repositories.NotificationsRepository;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;

/**
 * Created by alberto on 17/12/17.
 */

public class NotificationRepositoryImpl implements NotificationsRepository {

    private NotificationRestClient notificationRestClient;

    private NotificationsCache notificationsCache;


    public NotificationRepositoryImpl(NotificationRestClient notificationRestClient, NotificationsCache notificationsCache) {
        this.notificationRestClient = notificationRestClient;
        this.notificationsCache = notificationsCache;
    }

    @Override
    public Single<List<Notification>> findAllByDriverId(String driver_id) {
        return Single
                .create((SingleOnSubscribe<List<ChaskifyNotification>>) emitter -> notificationRestClient.getNotifications(new ApiCallback<List<ChaskifyNotification>>() {
                    @Override
                    public void onSuccess(List<ChaskifyNotification> chaskifyNotifications) {
                        emitter.onSuccess(chaskifyNotifications);
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
                }))
                .map(chaskifyNotifications -> Stream
                        .of(chaskifyNotifications)
                        .filter(chaskifyNotification -> chaskifyNotification.getDriverId().equals(driver_id))
                        .toList())
                .map(chaskifyNotifications -> Stream.of(chaskifyNotifications)
                        .map(chaskifyNotification -> new Notification()
                                .setDeviceId(chaskifyNotification.getDeviceId())
                                .setActions(chaskifyNotification.getActions())
                                .setDateProcess(chaskifyNotification.getDateProcess())
                                .setDateCreated(chaskifyNotification.getDateCreated())
                                .setCustomerId(chaskifyNotification.getCustomerId())
                                .setDriverId(chaskifyNotification.getDriverId())
                                .setIsRead(chaskifyNotification.getIsRead())
                                .setMessage(chaskifyNotification.getMessage())
                                .setTaskId(chaskifyNotification.getTaskId())
                                .setPushId(chaskifyNotification.getPushId())
                                .setPushMessage(chaskifyNotification.getPushMessage())
                                .setPushType(chaskifyNotification.getPushType())
                                .setPushTitle(chaskifyNotification.getPushTitle()))
                        .toList());

    }
}
