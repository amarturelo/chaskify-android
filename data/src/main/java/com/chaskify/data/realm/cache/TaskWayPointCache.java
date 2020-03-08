package com.chaskify.data.realm.cache;

import com.chaskify.data.realm.model.RealmTask;
import com.chaskify.data.realm.model.RealmTaskWaypoint;
import com.chaskify.domain.filter.Filter;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by alberto on 14/01/18.
 */

public interface TaskWayPointCache {
    Flowable<List<RealmTaskWaypoint>> getTaskWayPoint(List<Filter> filters);

    void put(List<RealmTaskWaypoint> realmTaskWaypoints);

    void put(RealmTaskWaypoint realmTaskWaypoint);
}
