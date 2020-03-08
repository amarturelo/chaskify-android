package com.chaskify.data.model.chaskify.mapper;

import com.annimon.stream.Stream;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTask;
import com.chaskify.domain.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDataMapper {

    public static List<Task> transform(List<ChaskifyTask> entities) {
        final List<Task> list = new ArrayList<>();
        Stream.of(entities).withoutNulls()
                .forEach(realmEvent -> list.add(transform(realmEvent)));
        return list;
    }

    public static Task transform(ChaskifyTask chaskifyTask) {
        Task task = new Task();
        task.setTask_id(chaskifyTask.getTaskId())
                .setCustomer_id(chaskifyTask.getCustomerId())
                .setDriver_id(chaskifyTask.getDriverId())
                .setTask_description(chaskifyTask.getTaskDescription())
                .setTeam_id(chaskifyTask.getTeamId())
                .setTrans_type(chaskifyTask.getTransType())
                .setStatus(chaskifyTask.getStatusRaw())
                .setDelivery_address(chaskifyTask.getDeliveryAddress())
                .setDelivery_date(chaskifyTask.getDeliveryDate())
                .setDeliveryTime(chaskifyTask.getDeliveryTime())
                .setCustomerName(chaskifyTask.getCustomerName())
                .setContactNumber(chaskifyTask.getContactNumber())
                .setEmail_address(chaskifyTask.getEmailAddress())
                .setTaskHistories(TaskHistoryDataMapper.transform(chaskifyTask.getHistory()))
                .setTaskWaypointList(TaskWaypointDataMapper.transform(chaskifyTask.getWaypoints()));
        return task;
    }
}