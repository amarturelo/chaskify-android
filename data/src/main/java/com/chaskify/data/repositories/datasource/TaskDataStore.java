package com.chaskify.data.repositories.datasource;

import com.chaskify.domain.filter.Filter;
import com.chaskify.domain.model.Task;

import java.util.Date;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by alberto on 5/01/18.
 */

public interface TaskDataStore {
    Flowable<List<Task>> tasks(List<Filter> filters);

    Single<Task> taskById(String driverId, String taskId);
}
