package com.chaskify.data.realm.cache.impl;

import com.chaskify.data.model.chaskify.RealmNotification;
import com.chaskify.data.model.chaskify.RealmTask;
import com.chaskify.data.realm.cache.NotificationsCache;
import com.chaskify.data.realm.module.InMemoryModule;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by alberto on 17/12/17.
 */

public class NotificationsCacheImpl implements NotificationsCache {


    public NotificationsCacheImpl() {
    }

    @Override
    public List<RealmNotification> getAllByDriverId(String driverId) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(RealmNotification.class).equalTo(RealmNotification.DRIVER_ID, driverId).findAll();
    }

    @Override
    public void put(List<RealmNotification> notifications) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(notifications);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void put(RealmNotification notification) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(notification);
        realm.commitTransaction();
        realm.close();
    }
}
