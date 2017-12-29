package com.chaskify.data.repositories;

import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.client.TaskWaypointRestClient;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTaskWaypoint;
import com.chaskify.domain.model.TaskWaypoint;
import com.chaskify.domain.repositories.TaskWaypointRepository;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;

/**
 * Created by alberto on 28/12/17.
 */

public class TaskWaypointRepositoryImpl implements TaskWaypointRepository {

    private TaskWaypointRestClient taskWaypointRestClient;

    public TaskWaypointRepositoryImpl(TaskWaypointRestClient taskWaypointRestClient) {
        this.taskWaypointRestClient = taskWaypointRestClient;
    }

    @Override
    public Single<TaskWaypoint> wayPointById(String driverId, String waypointId) {
        return Single.create((SingleOnSubscribe<ChaskifyTaskWaypoint>) emitter -> taskWaypointRestClient.wayPointById(waypointId, new ApiCallback<ChaskifyTaskWaypoint>() {
            @Override
            public void onSuccess(ChaskifyTaskWaypoint waypoint) {
                emitter.onSuccess(waypoint);
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
                .map(waypoint -> new TaskWaypoint()
                        .setId(waypoint.getId())
                        .setTaskId(waypoint.getTaskId())
                        .setContactNumber(waypoint.getContactNumber())
                        .setCustomerName(waypoint.getCustomerName())
                        .setDateCreated(waypoint.getDateCreated())
                        .setDeliveryAddress(waypoint.getDeliveryAddress())
                        .setEmailAddress(waypoint.getEmailAddress())
                        .setStatus(waypoint.getStatus())
                        .setTaskStatus(waypoint.getTaskStatus())
                        .setType(waypoint.getType())
                        .setWaypointDescription(waypoint.getWaypointDescription()));
    }
}
