package com.chaskify.data.realm.mapper;

import com.annimon.stream.Stream;
import com.chaskify.data.realm.model.RealmTaskWaypoint;
import com.chaskify.domain.model.TaskWaypoint;

import java.util.ArrayList;
import java.util.List;

public class TaskWaypointDataMapper {

    public static List<TaskWaypoint> transform(List<RealmTaskWaypoint> entities) {
        final List<TaskWaypoint> list = new ArrayList<>();
        Stream.of(entities).withoutNulls()
                .forEach(realmEvent -> list.add(transform(realmEvent)));
        return list;
    }

    public static TaskWaypoint transform(RealmTaskWaypoint realmTaskWaypoint) {
        TaskWaypoint waypoint = new TaskWaypoint();
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