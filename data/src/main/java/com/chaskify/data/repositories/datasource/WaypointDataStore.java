package com.chaskify.data.repositories.datasource;

import com.chaskify.domain.filter.Filter;
import com.chaskify.domain.model.TaskWaypoint;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by alberto on 14/01/18.
 */

public interface WaypointDataStore {
    Flowable<List<TaskWaypoint>> taskWayPoint(List<Filter> filters);
}
