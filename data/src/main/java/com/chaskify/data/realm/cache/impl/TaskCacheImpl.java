package com.chaskify.data.realm.cache.impl;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.chaskify.data.realm.cache.TaskCache;
import com.chaskify.data.model.chaskify.RealmTask;
import com.chaskify.data.realm.module.InMemoryModule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by alberto on 14/12/17.
 */

public class TaskCacheImpl implements TaskCache {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", /*Locale.getDefault()*/Locale.getDefault());


    public TaskCacheImpl() {

    }

    @Override
    public List<RealmTask> findAll() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(RealmTask.class).findAll();
    }

    @Override
    public List<RealmTask> findAllByDate(Date date) {
        return Stream.of(findAll())
                .filter(value -> dateFormat.format(value.getDelivery_date()).equals(dateFormat.format(date)))
                .toList();
    }

    @Override
    public Optional<RealmTask> findById(String driverId, String taskId) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<RealmTask> result = realm.where(RealmTask.class)
                .equalTo(RealmTask.TASK_ID, taskId)
                .equalTo(RealmTask.DRIVER_ID, driverId)
                .findAll();
        return result.isEmpty() ? Optional.empty() : Optional.of(result.first());
    }

    @Override
    public void put(List<RealmTask> realmTasks) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(realmTasks);
        realm.commitTransaction();
    }

    @Override
    public void put(RealmTask realmTask) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(realmTask);
        realm.commitTransaction();
    }
}
