package com.chaskify.data.realm.cache;

import com.chaskify.data.model.chaskify.RealmTask;

import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;


/**
 * Created by alberto on 14/12/17.
 */

public interface TaskCache {
    Single<List<RealmTask>> findAll(String driverId);

    Single<List<RealmTask>> findAllByDate(String driverId, Date date);

    Single<RealmTask> findById(String driverId, String taskId);

    void put(List<RealmTask> realmTasks);

    void put(RealmTask realmTask);
}
