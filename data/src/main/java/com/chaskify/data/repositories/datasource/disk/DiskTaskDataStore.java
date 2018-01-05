package com.chaskify.data.repositories.datasource.disk;

import com.chaskify.data.realm.cache.TaskCache;
import com.chaskify.data.realm.mapper.TaskDataMapper;
import com.chaskify.data.repositories.datasource.TaskDataStore;
import com.chaskify.domain.model.Task;

import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by alberto on 5/01/18.
 */

public class DiskTaskDataStore implements TaskDataStore {

    private TaskCache mTaskCache;

    public DiskTaskDataStore(TaskCache taskCache) {
        this.mTaskCache = taskCache;
    }

    @Override
    public Single<List<Task>> tasks(String driverId, Date date) {
        return mTaskCache.findAllByDate(driverId, date)
                .map(TaskDataMapper::transform);
    }

    @Override
    public Single<Task> taskById(String driverId, String taskId) {
        return mTaskCache.findById(driverId, taskId)
                .map(TaskDataMapper::transform);
    }
}
