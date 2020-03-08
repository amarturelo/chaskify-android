package com.chaskify.data.model.chaskify.mapper;

import com.annimon.stream.Stream;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTask;
import com.chaskify.data.realm.model.RealmTask;

import java.util.ArrayList;
import java.util.List;

public class ChaskifyTaskToRealmTaskDataMapper {

    public static List<RealmTask> transform(List<ChaskifyTask> entities) {
        final List<RealmTask> list = new ArrayList<>();
        Stream.of(entities).withoutNulls()
                .forEach(realmEvent -> list.add(transform(realmEvent)));
        return list;
    }

    public static RealmTask transform(ChaskifyTask task) {
        RealmTask realmTask = new RealmTask();
        realmTask.setTask_id(task.getTaskId())
                .setCustomer_id(task.getCustomerId())
                .setDriver_id(task.getDriverId())
                .setTeam_id(task.getTeamId())
                .setTrans_type(task.getTransType())
                .setStatus(task.getStatus())
                .setDelivery_address(task.getDeliveryAddress())
                .setDelivery_date(task.getDeliveryDate())
                .setDelivery_time(task.getDeliveryTime())
                .setCustomer_name(task.getCustomerName());
        return realmTask;
    }
}