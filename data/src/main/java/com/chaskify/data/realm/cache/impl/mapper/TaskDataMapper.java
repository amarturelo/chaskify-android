package com.chaskify.data.realm.cache.impl.mapper;

import com.annimon.stream.Stream;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTask;
import com.chaskify.data.realm.model.RealmTask;
import com.chaskify.domain.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDataMapper {

    public static List<RealmTask> transform(List<ChaskifyTask> entities) {
        final List<RealmTask> list = new ArrayList<>();
        Stream.of(entities).withoutNulls()
                .forEach(realmEvent -> list.add(transform(realmEvent)));
        return list;
    }

    public static RealmTask transform(ChaskifyTask realmTask) {
        RealmTask task = new RealmTask();
        task.setTask_id(realmTask.getTaskId())
                .setCustomer_id(realmTask.getCustomerId())
                .setDriver_id(realmTask.getDriverId())
                .setTask_description(realmTask.getTaskDescription())
                .setTeam_id(realmTask.getTeamId())
                .setTrans_type(realmTask.getTransType())
                .setStatus(realmTask.getStatus())
                .setDelivery_address(realmTask.getDeliveryAddress())
                .setDelivery_date(realmTask.getDeliveryDate())
                .setDelivery_time(realmTask.getDeliveryTime())
                .setCustomer_name(realmTask.getCustomerName())
                .setContact_number(realmTask.getContactNumber())
                .setEmail_address(realmTask.getEmailAddress())
                .setHistory(TaskHistoryDataMapper.transform(realmTask.getHistory()))
                .setWaypoints(TaskWaypointDataMapper.transform(realmTask.getWaypoints()));
        return task;
    }
}