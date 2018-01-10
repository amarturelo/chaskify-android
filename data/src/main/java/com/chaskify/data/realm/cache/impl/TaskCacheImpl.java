package com.chaskify.data.realm.cache.impl;

import com.annimon.stream.Stream;
import com.chaskify.data.realm.model.RealmTask;
import com.chaskify.data.realm.cache.TaskCache;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.realm.Realm;
import io.realm.RealmResults;
import timber.log.Timber;

/**
 * Created by alberto on 14/12/17.
 */

public class TaskCacheImpl implements TaskCache {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", /*Locale.getDefault()*/Locale.getDefault());


    public TaskCacheImpl() {
        Timber.tag(this.getClass().getSimpleName());
    }

    @Override
    public Single<List<RealmTask>> findAll(String driverId) {
        return Single.create(emitter -> {
            final Realm observableRealm = Realm.getDefaultInstance();
            final RealmResults<RealmTask> results = observableRealm.where(RealmTask.class)
                    .equalTo(RealmTask.DRIVER_ID, driverId)
                    .findAll();

            if (results.isLoaded() && results.isValid())
                emitter.onSuccess(results);

            emitter.setDisposable(new Disposable() {
                @Override
                public void dispose() {
                    Disposables.fromRunnable(observableRealm::close);
                }

                @Override
                public boolean isDisposed() {
                    return false;
                }
            });
        });
    }

    @Override
    public Single<List<RealmTask>> findAllByDate(String driverId, Date date) {
        return findAll(driverId)
                .map(realmTasks -> Stream.of(realmTasks)
                        .filter(value -> dateFormat.format(value.getDelivery_date()).equals(dateFormat.format(date)))
                        .toList())
                .doOnSuccess(realmTasks -> Timber.d(realmTasks.toString()));
    }

    @Override
    public Single<RealmTask> findById(String driverId, String taskId) {
        return Single.create(emitter -> {
            final Realm observableRealm = Realm.getDefaultInstance();
            final RealmResults<RealmTask> results = observableRealm.where(RealmTask.class)
                    .equalTo(RealmTask.DRIVER_ID, driverId)
                    .equalTo(RealmTask.TASK_ID, taskId)
                    .findAll();

            if (results.isLoaded() && results.isValid() && results.first() != null)
                emitter.onSuccess(results.first());

            emitter.setDisposable(new Disposable() {
                @Override
                public void dispose() {
                    Disposables.fromRunnable(observableRealm::close);
                }

                @Override
                public boolean isDisposed() {
                    return false;
                }
            });
        });
    }

    @Override
    public void put(List<RealmTask> realmTasks) {
        Timber.d("::put array " + realmTasks.toString() + " ::");
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(realmTasks);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void put(RealmTask realmTask) {
        Timber.d("::put " + realmTask.toString() + " ::");
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(realmTask);
        realm.commitTransaction();
        realm.close();
    }
}
