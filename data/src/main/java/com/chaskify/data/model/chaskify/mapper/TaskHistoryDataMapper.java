package com.chaskify.data.model.chaskify.mapper;

import com.annimon.stream.Stream;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTaskHistory;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTaskWaypoint;
import com.chaskify.domain.model.TaskHistory;
import com.chaskify.domain.model.TaskWaypoint;

import java.util.ArrayList;
import java.util.List;

public class TaskHistoryDataMapper {

    public static List<TaskHistory> transform(List<ChaskifyTaskHistory> entities) {
        final List<TaskHistory> list = new ArrayList<>();
        Stream.of(entities).withoutNulls()
                .forEach(realmEvent -> list.add(transform(realmEvent)));
        return list;
    }

    public static TaskHistory transform(ChaskifyTaskHistory chaskifyTaskHistory) {
        TaskHistory taskHistory = new TaskHistory();
        taskHistory.setId(chaskifyTaskHistory.getId())
                .setDateCreated(chaskifyTaskHistory.getDateCreated())
                .setDriverLocationLat(chaskifyTaskHistory.getDriverLocationLat())
                .setDriverLocationLng(chaskifyTaskHistory.getDriverLocationLng())
                .setRemarks(chaskifyTaskHistory.getRemarks())
                .setStatus(chaskifyTaskHistory.getStatus());
        return taskHistory;
    }
}