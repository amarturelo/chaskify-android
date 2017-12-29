package com.chaskify.domain.repositories;

import com.chaskify.domain.model.TaskWaypoint;

import io.reactivex.Single;

/**
 * Created by alberto on 28/12/17.
 */

public interface TaskWaypointRepository {
    Single<TaskWaypoint> wayPointById(String driverId, String waypointId);
}
