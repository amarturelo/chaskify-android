package com.chaskify.data.realm.cache;

import com.chaskify.data.model.chaskify.RealmNotification;

import java.util.List;

/**
 * Created by alberto on 17/12/17.
 */

public interface NotificationsCache {
    List<RealmNotification> getAllByDriverId(String driverId);

    void put(List<RealmNotification> notifications);

    void put(RealmNotification notification);
}
