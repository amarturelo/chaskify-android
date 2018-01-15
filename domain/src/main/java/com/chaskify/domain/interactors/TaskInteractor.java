package com.chaskify.domain.interactors;

import com.chaskify.domain.filter.Filter;
import com.chaskify.domain.model.Task;
import com.chaskify.domain.repositories.TaskRepository;

import java.util.Date;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by alberto on 14/12/17.
 */

public class TaskInteractor {
    private TaskRepository taskRepository;

    public TaskInteractor(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Flowable<List<Task>> tasks(List<Filter> filters) {
        return taskRepository.tasks(filters);
    }

    public Observable<Task> taskById(String driver_id, String task_id) {
        return taskRepository.taskById(driver_id, task_id);
    }
}
