package com.chaskify.android.ui.model.mapper;

import com.annimon.stream.Stream;
import com.chaskify.android.ui.model.TaskModel;
import com.chaskify.domain.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskModelDataMapper {

    public static List<TaskModel> transform(List<Task> entities) {
        final List<TaskModel> list = new ArrayList<>();
        Stream.of(entities).withoutNulls()
                .forEach(realmEvent -> list.add(transform(realmEvent)));
        return list;
    }

    public static TaskModel transform(Task task) {
        TaskModel taskModel = new TaskModel();
        taskModel.setTaskId(task.getTaskId())
                .setCustomerName(task.getCustomer_name())
                .setContactNumber(task.getContact_number())
                .setEmailAddress(task.getEmail_address())
                .setDeliveryAddress(task.getDelivery_address())
                .setDeliveryDate(task.getDelivery_date())
                .setDescription(task.getTask_description())
                .setStatus(task.getStatus())
                .setTransType(task.getTrans_type())
                .setTaskHistoryItemModels(TaskHistoryItemModelDataMapper.transform(task.getTaskHistories()))
                .setTaskWaypointItemModels(TaskWayPointItemModelDataMapper.transform(task.getTaskWaypointList()));
        return taskModel;
    }
}