package com.chaskify.data.realm.cache.impl.mapper;

import com.annimon.stream.Stream;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTaskHistory;
import com.chaskify.data.realm.model.RealmTaskHistory;
import com.chaskify.domain.model.TaskHistory;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

public class TaskHistoryDataMapper {

    public static RealmList<RealmTaskHistory> transform(List<ChaskifyTaskHistory> entities) {
        final RealmList<RealmTaskHistory> list = new RealmList<>();
        Stream.of(entities).withoutNulls()
                .forEach(realmEvent -> list.add(transform(realmEvent)));
        return list;
    }

    public static RealmTaskHistory transform(ChaskifyTaskHistory realmTaskHistory) {
        RealmTaskHistory taskHistory = new RealmTaskHistory();
        taskHistory.setId(realmTaskHistory.getId())
                .setDateCreated(realmTaskHistory.getDateCreated())
                .setDriverLocationLat(realmTaskHistory.getDriverLocationLat())
                .setDriverLocationLng(realmTaskHistory.getDriverLocationLng())
                .setRemarks(realmTaskHistory.getRemarks())
                .setStatus(realmTaskHistory.getStatus());
        return taskHistory;
    }
}