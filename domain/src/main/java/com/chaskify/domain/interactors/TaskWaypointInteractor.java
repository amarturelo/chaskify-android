package com.chaskify.domain.interactors;

import com.chaskify.domain.model.TaskWaypoint;
import com.chaskify.domain.repositories.TaskWaypointRepository;

import io.reactivex.Single;

/**
 * Created by alberto on 28/12/17.
 */

public class TaskWaypointInteractor {
    private TaskWaypointRepository taskTaskWaypointRepository;

    public TaskWaypointInteractor(TaskWaypointRepository taskTaskWaypointRepository) {
        this.taskTaskWaypointRepository = taskTaskWaypointRepository;
    }

    public Single<TaskWaypoint> wayPointById(String driverId, String waypointId) {
        return taskTaskWaypointRepository.wayPointById(driverId, waypointId);
    }
}
