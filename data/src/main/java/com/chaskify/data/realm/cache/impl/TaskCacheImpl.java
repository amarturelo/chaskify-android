package com.chaskify.data.realm.cache.impl;

import com.annimon.stream.Optional;
import com.chaskify.data.realm.cache.TaskCache;
import com.chaskify.data.model.chaskify.RealmTask;
import com.chaskify.data.realm.module.InMemoryModule;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by alberto on 14/12/17.
 */

public class TaskCacheImpl implements TaskCache {

    private RealmConfiguration configuration;

    public TaskCacheImpl() {
        configuration = new RealmConfiguration.Builder()
                .name("inMemory.realm")
                .inMemory()
                .modules(new InMemoryModule())
                .deleteRealmIfMigrationNeeded()
                .build();
    }

    @Override
    public List<RealmTask> findAll() {
        Realm realm = Realm.getInstance(configuration);
        return realm.where(RealmTask.class).findAll();
    }

    @Override
    public List<RealmTask> findAllByDate(String date) {
        Realm realm = Realm.getInstance(configuration);
        return null;
    }

    @Override
    public Optional<RealmTask> find(String taskId) {
        Realm realm = Realm.getInstance(configuration);
        RealmResults<RealmTask> result = realm.where(RealmTask.class).equalTo(RealmTask.TASK_ID, taskId).findAll();
        return result.isEmpty() ? Optional.empty() : Optional.of(result.first());
    }

    @Override
    public void put(List<RealmTask> realmTasks) {
        Realm realm = Realm.getInstance(configuration);
        realm.beginTransaction();
        realm.insertOrUpdate(realmTasks);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void put(RealmTask realmTask) {
        Realm realm = Realm.getInstance(configuration);
        realm.beginTransaction();
        realm.insertOrUpdate(realmTask);
        realm.commitTransaction();
        realm.close();
    }
}
