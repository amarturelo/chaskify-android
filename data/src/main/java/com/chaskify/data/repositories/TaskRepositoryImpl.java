package com.chaskify.data.repositories;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.client.TaskRestClient;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTask;
import com.chaskify.data.model.chaskify.RealmTask;
import com.chaskify.data.model.chaskify.mapper.TaskDataMapper;
import com.chaskify.data.realm.cache.TaskCache;
import com.chaskify.domain.model.Task;
import com.chaskify.domain.repositories.TaskRepository;

import java.util.Date;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Function;

/**
 * Created by alberto on 14/12/17.
 */

public class TaskRepositoryImpl implements TaskRepository {

    private TaskRestClient taskRestClient;

    private TaskCache taskCache;

    public TaskRepositoryImpl(TaskRestClient taskRestClient, TaskCache taskCache) {
        this.taskRestClient = taskRestClient;
        this.taskCache = taskCache;
    }

    @Override
    public Single<Task> taskById(String driverId, String taskId) {
        /*return Single.create((SingleOnSubscribe<Optional<RealmTask>>) emitter -> emitter.onSuccess(taskCache.findById(driverId, taskId)))
                .map(realmTaskOptional -> realmTaskOptional.isPresent() ? Optional.of(new Task()
                        .setTask_id(realmTaskOptional.get().getTask_id())
                        .setCustomer_id(realmTaskOptional.get().getCustomer_id())
                        .setDriver_id(realmTaskOptional.get().getCustomer_id())
                        .setTeam_id(realmTaskOptional.get().getCustomer_id())
                        .setTrans_type(realmTaskOptional.get().getTrans_type())
                        .setStatus(realmTaskOptional.get().getStatus())
                        .setDelivery_address(realmTaskOptional.get().getDelivery_address())
                        .setDelivery_date(realmTaskOptional.get().getDelivery_date())
                        .setDelivery_time(realmTaskOptional.get().getDelivery_time())
                        .setCustomer_name(realmTaskOptional.get().getCustomer_name())) : Optional.empty());*/
        return Single.create((SingleOnSubscribe<ChaskifyTask>) emitter -> taskRestClient.taskDetails(taskId, new ApiCallback<ChaskifyTask>() {
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
                .map(TaskDataMapper::transform);
    }

    @Override
    public Single<List<Task>> tasks(Date date) {
        return Single.create((SingleOnSubscribe<List<ChaskifyTask>>) emitter -> taskRestClient.taskByDate(date, new ApiCallback<List<ChaskifyTask>>() {
            @Override
            public void onSuccess(List<ChaskifyTask> chaskifyTasks) {
                emitter.onSuccess(chaskifyTasks);
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
        })).map(chaskifyTasks -> Stream.of(chaskifyTasks)
                .map(chaskifyTask -> new Task()
                        .setTask_id(chaskifyTask.getTaskId())
                        .setCustomer_id(chaskifyTask.getCustomerId())
                        .setDriver_id(chaskifyTask.getDriverId())
                        .setTeam_id(chaskifyTask.getTeamId())
                        .setTrans_type(chaskifyTask.getTransType())
                        .setStatus(chaskifyTask.getStatus())
                        .setDelivery_address(chaskifyTask.getDeliveryAddress())
                        .setDelivery_date(chaskifyTask.getDeliveryDate())
                        .setDelivery_time(chaskifyTask.getDeliveryTime())
                        .setCustomer_name(chaskifyTask.getCustomerName()))
                .toList())
                .doOnSuccess(tasks -> taskCache.put(Stream.of(tasks)
                        .map(task -> new RealmTask()
                                .setTask_id(task.getTask_id())
                                .setCustomer_id(task.getCustomer_id())
                                .setDriver_id(task.getDriver_id())
                                .setTeam_id(task.getTeam_id())
                                .setTrans_type(task.getTrans_type())
                                .setStatus(task.getStatus())
                                .setDelivery_address(task.getDelivery_address())
                                .setDelivery_date(task.getDelivery_date())
                                .setDelivery_time(task.getDelivery_time())
                                .setCustomer_name(task.getCustomer_name()))
                        .toList()
                ));
    }
}
