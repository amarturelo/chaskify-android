package com.chaskify.data.realm.mapper;

import com.annimon.stream.Stream;
import com.chaskify.data.realm.model.RealmTaskHistory;
import com.chaskify.domain.model.TaskHistory;

import java.util.ArrayList;
import java.util.List;

public class TaskHistoryDataMapper {

    public static List<TaskHistory> transform(List<RealmTaskHistory> entities) {
        final List<TaskHistory> list = new ArrayList<>();
        Stream.of(entities).withoutNulls()
                .forEach(realmEvent -> list.add(transform(realmEvent)));
        return list;
    }

    public static TaskHistory transform(RealmTaskHistory realmTaskHistory) {
        TaskHistory taskHistory = new TaskHistory();
        taskHistory.setId(realmTaskHistory.getId())
                .setDateCreated(realmTaskHistory.getDateCreated())
                .setDriverLocationLat(realmTaskHistory.getDriverLocationLat())
                .setDriverLocationLng(realmTaskHistory.getDriverLocationLng())
                .setRemarks(realmTaskHistory.getRemarks())
                .setStatus(realmTaskHistory.getStatus());
        return taskHistory;
    }
}