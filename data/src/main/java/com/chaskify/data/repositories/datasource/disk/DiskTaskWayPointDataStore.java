package com.chaskify.data.repositories.datasource.disk;

import com.chaskify.data.realm.cache.TaskWayPointCache;
import com.chaskify.data.realm.mapper.TaskWaypointDataMapper;
import com.chaskify.data.repositories.datasource.WaypointDataStore;
import com.chaskify.domain.filter.Filter;
import com.chaskify.domain.model.TaskWaypoint;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by alberto on 14/01/18.
 */

public class DiskTaskWayPointDataStore implements WaypointDataStore {

    private TaskWayPointCache mTaskWayPointCache;

    public DiskTaskWayPointDataStore(TaskWayPointCache mTaskWayPointCache) {
        this.mTaskWayPointCache = mTaskWayPointCache;
    }

    @Override
    public Flowable<List<TaskWaypoint>> taskWayPoint(List<Filter> filters) {
        return mTaskWayPointCache.getTaskWayPoint(filters)
                .map(TaskWaypointDataMapper::transform);
    }
}
