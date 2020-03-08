package com.chaskify.domain.repositories;

import com.chaskify.domain.filter.Filter;
import com.chaskify.domain.model.TaskWaypoint;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by alberto on 28/12/17.
 */

public interface TaskWaypointRepository {
    Flowable<List<TaskWaypoint>> wayPointById(List<Filter> filters);
}
