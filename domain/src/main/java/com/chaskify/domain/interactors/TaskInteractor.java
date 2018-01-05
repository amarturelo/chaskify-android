package com.chaskify.domain.interactors;

import com.chaskify.domain.model.Task;
import com.chaskify.domain.repositories.TaskRepository;

import java.util.Date;
import java.util.List;

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

    public Observable<List<Task>> tasks(String driverId, Date date) {
        return taskRepository.tasks(driverId, date);
    }

    public Observable<Task> taskById(String driver_id, String task_id) {
        return taskRepository.taskById(driver_id, task_id);
    }
}
