package com.chaskify.data.realm.cache.impl.mapper;

import com.annimon.stream.Stream;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTaskWayPoint;
import com.chaskify.data.realm.model.RealmTaskWaypoint;

import java.util.List;

import io.realm.RealmList;

public class TaskWaypointDataMapper {

    public static RealmList<RealmTaskWaypoint> transform(List<ChaskifyTaskWayPoint> entities) {
        final RealmList<RealmTaskWaypoint> list = new RealmList<>();
        Stream.of(entities).withoutNulls()
                .forEach(realmEvent -> list.add(transform(realmEvent)));
        return list;
    }

    public static RealmTaskWaypoint transform(ChaskifyTaskWayPoint realmTaskWaypoint) {
        RealmTaskWaypoint waypoint = new RealmTaskWaypoint();
        waypoint.setId(realmTaskWaypoint.getId())
                .setTaskId(realmTaskWaypoint.getTaskId())
                .setWaypointDescription(realmTaskWaypoint.getWaypointDescription())
                .setType(realmTaskWaypoint.getType())
                .setTaskStatus(realmTaskWaypoint.getTaskStatus())
                .setStatus(realmTaskWaypoint.getStatus())
                .setEmailAddress(realmTaskWaypoint.getEmailAddress())
                .setDeliveryAddress(realmTaskWaypoint.getDeliveryAddress())
                .setDateCreated(realmTaskWaypoint.getDateCreated())
                .setCustomerName(realmTaskWaypoint.getCustomerName())
                .setContactNumber(realmTaskWaypoint.getContactNumber());
        return waypoint;
    }
}