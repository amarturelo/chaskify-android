package com.chaskify.data.model.chaskify.mapper;

import com.annimon.stream.Stream;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTask;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTaskHistory;
import com.chaskify.data.model.chaskify.RealmTaskHistory;
import com.chaskify.domain.model.Task;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

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
                .setDriver_id(chaskifyTask.getDriverId())
                .setTeam_id(chaskifyTask.getTeamId())
                .setTrans_type(chaskifyTask.getTransType())
                .setStatus(chaskifyTask.getStatus())
                .setDelivery_address(chaskifyTask.getDeliveryAddress())
                .setDelivery_date(chaskifyTask.getDeliveryDate())
                .setDelivery_time(chaskifyTask.getDeliveryTime())
                .setContact_number(chaskifyTask.getContactNumber())
                .setEmail_address(chaskifyTask.getEmailAddress());
        return task;
    }
}