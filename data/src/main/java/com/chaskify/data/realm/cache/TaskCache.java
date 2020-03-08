package com.chaskify.data.realm.cache;

import com.chaskify.data.realm.model.RealmTask;
import com.chaskify.domain.filter.Filter;

import java.util.Date;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;


/**
 * Created by alberto on 14/12/17.
 */

public interface TaskCache {
    Single<List<RealmTask>> findAll(String driverId);

    Flowable<List<RealmTask>> getTaskAsObservable(List<Filter> filters);

    Single<RealmTask> findById(String driverId, String taskId);

    void put(List<RealmTask> realmTasks);

    void put(RealmTask realmTask);
}
