package com.chaskify.data.repositories.datasource;

import com.chaskify.domain.model.Task;

import java.util.Date;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by alberto on 5/01/18.
 */

public interface TaskDataStore {
    Single<List<Task>> tasks(String driverId, Date date);

    Single<Task> taskById(String driverId, String taskId);
}
