package com.chaskify.data.repositories.datasource.cloud;

import com.annimon.stream.Stream;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.client.TaskRestClient;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTask;
import com.chaskify.data.realm.model.RealmTask;
import com.chaskify.data.realm.model.RealmTaskHistory;
import com.chaskify.data.realm.model.RealmTaskWaypoint;
import com.chaskify.data.model.chaskify.mapper.TaskDataMapper;
import com.chaskify.data.realm.cache.TaskCache;
import com.chaskify.data.repositories.datasource.TaskDataStore;
import com.chaskify.domain.model.Task;

import java.util.Date;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.realm.RealmList;
import timber.log.Timber;

/**
 * Created by alberto on 5/01/18.
 */

public class CloudTaskDataStore implements TaskDataStore {

    private TaskRestClient mTaskRestClient;

    private TaskCache mTaskCache;

    public CloudTaskDataStore(TaskRestClient taskRestClient, TaskCache taskCache) {
        Timber.tag(this.getClass().getSimpleName());
        this.mTaskRestClient = taskRestClient;
        this.mTaskCache = taskCache;
    }

    @Override
    public Single<List<Task>> tasks(String driverId, Date date) {
        return Single.create((SingleOnSubscribe<List<ChaskifyTask>>) emitter -> mTaskRestClient.taskByDate(date, new ApiCallback<List<ChaskifyTask>>() {
            @Override
            public void onSuccess(List<ChaskifyTask> chaskifyTasks) {
                Timber.d("::onSuccess " + chaskifyTasks.toString() + " ::");
                emitter.onSuccess(chaskifyTasks);
            }

            @Override
            public void onNetworkError(Exception e) {
                Timber.d("::onNetworkError " + e.toString() + " ::");
                emitter.onError(e);
            }

            @Override
            public void onChaskifyError(Exception e) {
                Timber.d("::onChaskifyError " + e.toString() + " ::");
                emitter.onError(e);
            }

            @Override
            public void onUnexpectedError(Exception e) {
                Timber.d("::onUnexpectedError " + e.toString() + " ::");
                emitter.onError(e);
            }
        }))
                .map(TaskDataMapper::transform)
                .doOnSuccess(tasks -> mTaskCache.put(Stream.of(tasks)
                        .map(this::toRealm)
                        .toList()));
    }

    @Override
    public Single<Task> taskById(String driverId, String taskId) {
        return Single.create((SingleOnSubscribe<ChaskifyTask>) emitter -> mTaskRestClient.taskDetails(taskId, new ApiCallback<ChaskifyTask>() {
            @Override
            public void onSuccess(ChaskifyTask chaskifyTask) {
                emitter.onSuccess(chaskifyTask);
            }

            @Override
            public void onNetworkError(Exception e) {
                emitter.onError(e);
            }

            @Override
            public void onChaskifyError(Exception e) {
                emitter.onError(e);
            }

            @Override
            public void onUnexpectedError(Exception e) {
                emitter.onError(e);
            }
        }))
                .map(TaskDataMapper::transform)
                .doOnSuccess(task -> mTaskCache.put(toRealm(task)));
    }

    private RealmTask toRealm(Task task) {
        RealmList<RealmTaskWaypoint> taskWaypoints = new RealmList<>();
        Stream.of(task.getTaskWaypointList())
                .map(taskWaypoint -> new RealmTaskWaypoint()
                        .setId(taskWaypoint.getId())
                        .setTaskId(taskWaypoint.getTaskId())
                        .setWaypointDescription(taskWaypoint.getWaypointDescription())
                        .setType(taskWaypoint.getType())
                        .setTaskStatus(taskWaypoint.getTaskStatus())
                        .setStatus(taskWaypoint.getStatus())
                        .setEmailAddress(taskWaypoint.getEmailAddress())
                        .setDeliveryAddress(taskWaypoint.getDeliveryAddress())
                        .setDateCreated(taskWaypoint.getDateCreated())
                        .setCustomerName(taskWaypoint.getCustomerName())
                        .setContactNumber(taskWaypoint.getContactNumber()))
                .forEach(taskWaypoints::add);

        RealmList<RealmTaskHistory> taskHistories = new RealmList<>();
        Stream.of(task.getTaskHistories())
                .map(taskHistory -> new RealmTaskHistory()
                        .setId(taskHistory.getId())
                        .setDateCreated(taskHistory.getDateCreated())
                        .setDriverLocationLat(taskHistory.getDriverLocationLat())
                        .setDriverLocationLng(taskHistory.getDriverLocationLng())
                        .setRemarks(taskHistory.getRemarks())
                        .setStatus(taskHistory.getStatus()))
                .forEach(taskHistories::add);

        return new RealmTask()
                .setTask_id(task.getTaskId())
                .setCustomer_id(task.getCustomer_id())
                .setDriver_id(task.getDriver_id())
                .setTask_description(task.getTask_description())
                .setTeam_id(task.getTeam_id())
                .setTrans_type(task.getTrans_type())
                .setStatus(task.getStatus())
                .setDelivery_address(task.getDelivery_address())
                .setDelivery_date(task.getDelivery_date())
                .setDelivery_time(task.getDelivery_time())
                .setCustomer_name(task.getCustomer_name())
                .setContact_number(task.getContact_number())
                .setEmail_address(task.getEmail_address())
                .setWaypoints(taskWaypoints)
                .setHistory(taskHistories);
    }
}
