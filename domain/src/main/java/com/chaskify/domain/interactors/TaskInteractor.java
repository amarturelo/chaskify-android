package com.chaskify.domain.interactors;

import com.chaskify.domain.model.Task;
import com.chaskify.domain.repositories.TaskRepository;

import java.util.Date;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by alberto on 14/12/17.
 */

public class TaskInteractor {
    private TaskRepository taskRepository;

    public TaskInteractor(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Single<List<Task>> tasks(Date date) {
        return taskRepository.tasks(date);
    }
}
