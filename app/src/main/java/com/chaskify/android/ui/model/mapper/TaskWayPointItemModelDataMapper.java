package com.chaskify.android.ui.model.mapper;

import com.annimon.stream.Stream;
import com.chaskify.android.ui.model.TaskHistoryItemModel;
import com.chaskify.android.ui.model.TaskWaypointItemModel;
import com.chaskify.domain.model.TaskHistory;
import com.chaskify.domain.model.TaskWaypoint;

import java.util.ArrayList;
import java.util.List;

public class TaskWayPointItemModelDataMapper {

    public static List<TaskWaypointItemModel> transform(List<TaskWaypoint> entities) {
        final List<TaskWaypointItemModel> list = new ArrayList<>();
        Stream.of(entities).withoutNulls()
                .forEach(realmEvent -> list.add(transform(realmEvent)));
        return list;
    }

    public static TaskWaypointItemModel transform(TaskWaypoint taskWaypoint) {
        TaskWaypointItemModel taskWaypointItemModel = new TaskWaypointItemModel();
        taskWaypointItemModel.setId(taskWaypoint.getId())
                .setDeliveryAddress(taskWaypoint.getDeliveryAddress())
                .setTaskStatus(taskWaypoint.getTaskStatus())
                .setTaskType(taskWaypoint.getType());
        return taskWaypointItemModel;
    }
}