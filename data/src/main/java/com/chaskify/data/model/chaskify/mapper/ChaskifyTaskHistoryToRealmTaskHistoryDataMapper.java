package com.chaskify.data.model.chaskify.mapper;

import com.annimon.stream.Stream;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTaskHistory;
import com.chaskify.data.model.chaskify.RealmTaskHistory;

import java.util.ArrayList;
import java.util.List;

public class ChaskifyTaskHistoryToRealmTaskHistoryDataMapper {

    public static List<RealmTaskHistory> transform(List<ChaskifyTaskHistory> entities) {
        final List<RealmTaskHistory> list = new ArrayList<>();
        Stream.of(entities).withoutNulls()
                .forEach(realmEvent -> list.add(transform(realmEvent)));
        return list;
    }

    public static RealmTaskHistory transform(ChaskifyTaskHistory taskHistory) {
        RealmTaskHistory realmTaskHistory = new RealmTaskHistory();
        realmTaskHistory.setTaskId(taskHistory.getTaskId())
                .setId(taskHistory.getId())
                .setDriverId(taskHistory.getDriverId())
                .setDateCreated(taskHistory.getDateCreated())
                .setReason(taskHistory.getReason())
                .setDriverLocationLat(taskHistory.getDriverLocationLat())
                .setDriverLocationLng(taskHistory.getDriverLocationLng());
        return realmTaskHistory;
    }
}