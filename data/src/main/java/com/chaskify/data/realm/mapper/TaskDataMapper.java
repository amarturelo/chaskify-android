package com.chaskify.data.realm.mapper;

import com.annimon.stream.Stream;
import com.chaskify.data.realm.model.RealmTask;
import com.chaskify.domain.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDataMapper {

    public static List<Task> transform(List<RealmTask> entities) {
        final List<Task> list = new ArrayList<>();
        Stream.of(entities).withoutNulls()
                .forEach(realmEvent -> list.add(transform(realmEvent)));
        return list;
    }

    public static Task transform(RealmTask realmTask) {
        Task task = new Task();
        task.setTask_id(realmTask.getTask_id())
                .setCustomer_id(realmTask.getCustomer_id())
                .setDriver_id(realmTask.getDriver_id())
                .setTask_description(realmTask.getTask_description())
                .setTeam_id(realmTask.getTeam_id())
                .setTrans_type(realmTask.getTrans_type())
                .setStatus(realmTask.getStatus())
                .setTask_lat(realmTask.getTask_lat())
                .setTask_lng(realmTask.getTask_lng())
                .setDelivery_address(realmTask.getDelivery_address())
                .setDelivery_date(realmTask.getDelivery_date())
                .setDeliveryTime(realmTask.getDelivery_time())
                .setCustomerName(realmTask.getCustomer_name())
                .setOrderNumber(realmTask.getOrderNumber())
                .setContactNumber(realmTask.getContact_number())
                .setEmail_address(realmTask.getEmail_address())
                .setTaskHistories(TaskHistoryDataMapper.transform(realmTask.getHistory()))
                .setTaskWaypointList(TaskWaypointDataMapper.transform(realmTask.getWaypoints()));
        return task;
    }
}