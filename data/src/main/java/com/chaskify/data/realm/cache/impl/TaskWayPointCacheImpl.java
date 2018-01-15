package com.chaskify.data.realm.cache.impl;

import android.os.Looper;
import android.util.Pair;

import com.annimon.stream.Stream;
import com.chaskify.data.realm.cache.TaskWayPointCache;
import com.chaskify.data.realm.model.RealmTask;
import com.chaskify.data.realm.model.RealmTaskWaypoint;
import com.chaskify.domain.filter.DriverFilter;
import com.chaskify.domain.filter.Filter;
import com.chaskify.domain.filter.TaskIdFilter;
import com.chaskify.domain.filter.WaypointIdFilter;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import timber.log.Timber;

/**
 * Created by alberto on 14/01/18.
 */

public class TaskWayPointCacheImpl extends RealmCache implements TaskWayPointCache {

    public TaskWayPointCacheImpl() {
        Timber.tag(this.getClass().getSimpleName());
    }

    @Override
    public void put(List<RealmTaskWaypoint> realmTaskWaypoints) {
        Timber.d("::put array " + realmTaskWaypoints.toString() + " ::");
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(realmTaskWaypoints);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void put(RealmTaskWaypoint realmTaskWaypoint) {
        Timber.d("::put array " + realmTaskWaypoint.toString() + " ::");
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(realmTaskWaypoint);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public Flowable<List<RealmTaskWaypoint>> getTaskWayPoint(List<Filter> filters) {
        return Flowable.defer(() -> Flowable.using(() -> new Pair<>(Realm.getDefaultInstance(), Looper.myLooper()), pair -> {
                    RealmQuery<RealmTaskWaypoint> query = pair.first.where(RealmTaskWaypoint.class);
                    Stream.of(filters)
                            .forEach(filter -> {
                                /*if (filter instanceof DriverFilter)
                                    query.equalTo(RealmTaskWaypoint.ID, ((DriverFilter) filter).getDriver());*/
                                if (filter instanceof WaypointIdFilter)
                                    query.equalTo(RealmTaskWaypoint.ID, ((WaypointIdFilter) filter).getWayPointId());
                            });

                    RealmResults<RealmTaskWaypoint> result = query
                            .findAll();

                    return result.<RealmTaskWaypoint>asFlowable()
                            .map(pair.first::copyFromRealm)
                            ;
                }
                , pair -> close(pair.first, pair.second))
                .unsubscribeOn(AndroidSchedulers.from(Looper.myLooper())))
                ;
    }
}
