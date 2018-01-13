package com.chaskify.domain.repositories;

import com.chaskify.domain.filter.Filter;
import com.chaskify.domain.model.Task;

import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by alberto on 14/12/17.
 */

public interface TaskRepository {
    Observable<List<Task>> tasks(List<Filter> filters);

    Observable<Task> taskById(String driverId, String taskId);
}
