package com.chaskify.domain.interactors;

import com.chaskify.domain.filter.Filter;
import com.chaskify.domain.model.TaskWaypoint;
import com.chaskify.domain.repositories.TaskWaypointRepository;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by alberto on 28/12/17.
 */

public class TaskWaypointInteractor {
    private TaskWaypointRepository taskTaskWaypointRepository;

    public TaskWaypointInteractor(TaskWaypointRepository taskTaskWaypointRepository) {
        this.taskTaskWaypointRepository = taskTaskWaypointRepository;
    }

    public Flowable<List<TaskWaypoint>> wayPointById(List<Filter> filters) {
        return taskTaskWaypointRepository.wayPointById(filters);
    }
}
