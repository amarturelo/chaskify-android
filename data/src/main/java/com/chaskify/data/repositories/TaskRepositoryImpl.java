package com.chaskify.data.repositories;

import com.chaskify.chaskify_sdk.rest.client.TaskRestClient;
import com.chaskify.data.realm.cache.TaskCache;
import com.chaskify.data.realm.cache.impl.TaskCacheImpl;
import com.chaskify.data.repositories.datasource.cloud.CloudTaskDataStore;
import com.chaskify.data.repositories.datasource.disk.DiskTaskDataStore;
import com.chaskify.domain.filter.Filter;
import com.chaskify.domain.model.Task;
import com.chaskify.domain.repositories.TaskRepository;

import java.util.Date;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import timber.log.Timber;

/**
 * Created by alberto on 14/12/17.
 */

public class TaskRepositoryImpl implements TaskRepository {

    private DiskTaskDataStore diskTaskDataStore;

    public TaskRepositoryImpl(TaskCache taskCache) {
        Timber.tag(this.getClass().getSimpleName());
        this.diskTaskDataStore = new DiskTaskDataStore(taskCache);
    }

    @Override
    public Observable<Task> taskById(String driverId, String taskId) {
        return diskTaskDataStore.taskById(driverId, taskId).toObservable();
    }

    @Override
    public Flowable<List<Task>> tasks(List<Filter> filters) {
        return diskTaskDataStore.tasks(filters);
    }
}
