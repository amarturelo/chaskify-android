package com.chaskify.data.repositories;

import com.chaskify.data.realm.cache.TaskWayPointCache;
import com.chaskify.data.repositories.datasource.disk.DiskTaskWayPointDataStore;
import com.chaskify.domain.filter.Filter;
import com.chaskify.domain.model.TaskWaypoint;
import com.chaskify.domain.repositories.TaskWaypointRepository;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by alberto on 28/12/17.
 */

public class TaskWaypointRepositoryImpl implements TaskWaypointRepository {

    private DiskTaskWayPointDataStore mDiskTaskWayPointDataStore;

    public TaskWaypointRepositoryImpl(TaskWayPointCache taskWayPointCache) {
        this.mDiskTaskWayPointDataStore = new DiskTaskWayPointDataStore(taskWayPointCache);
    }

    @Override
    public Flowable<List<TaskWaypoint>> wayPointById(List<Filter> filters) {
        return mDiskTaskWayPointDataStore.taskWayPoint(filters);
    }
}
