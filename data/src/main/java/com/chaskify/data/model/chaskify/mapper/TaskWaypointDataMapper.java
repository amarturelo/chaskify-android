package com.chaskify.data.model.chaskify.mapper;

import com.annimon.stream.Stream;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTaskWayPoint;
import com.chaskify.domain.model.TaskWaypoint;

import java.util.ArrayList;
import java.util.List;

public class TaskWaypointDataMapper {

    public static List<TaskWaypoint> transform(List<ChaskifyTaskWayPoint> entities) {
        final List<TaskWaypoint> list = new ArrayList<>();
        Stream.of(entities).withoutNulls()
                .forEach(realmEvent -> list.add(transform(realmEvent)));
        return list;
    }

    public static TaskWaypoint transform(ChaskifyTaskWayPoint chaskifyTaskWaypoint) {
        TaskWaypoint waypoint = new TaskWaypoint();
        waypoint.setId(chaskifyTaskWaypoint.getId())
                .setTaskId(chaskifyTaskWaypoint.getTaskId())
                .setWaypointDescription(chaskifyTaskWaypoint.getWaypointDescription())
                .setType(chaskifyTaskWaypoint.getType())
                .setTaskStatus(chaskifyTaskWaypoint.getTaskStatus())
                .setStatus(chaskifyTaskWaypoint.getStatus())
                .setEmailAddress(chaskifyTaskWaypoint.getEmailAddress())
                .setDeliveryAddress(chaskifyTaskWaypoint.getDeliveryAddress())
                .setDateCreated(chaskifyTaskWaypoint.getDateCreated())
                .setCustomerName(chaskifyTaskWaypoint.getCustomerName())
                .setContactNumber(chaskifyTaskWaypoint.getContactNumber());
        return waypoint;
    }
}