package com.chaskify.android.ui.model.mapper;

import com.annimon.stream.Stream;
import com.chaskify.android.ui.model.TaskHistoryItemModel;
import com.chaskify.android.ui.model.TaskModel;
import com.chaskify.android.ui.model.TaskWaypointItemModel;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTask;
import com.chaskify.data.model.chaskify.mapper.TaskHistoryDataMapper;
import com.chaskify.data.model.chaskify.mapper.TaskWaypointDataMapper;
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
        taskModel.setTaskId(task.getTask_id())
                .setCustomerName(task.getCustomer_name())
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