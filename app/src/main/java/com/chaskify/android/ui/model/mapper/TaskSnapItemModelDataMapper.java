package com.chaskify.android.ui.model.mapper;

import com.annimon.stream.Stream;
import com.chaskify.android.ui.model.TaskItemSnapModel;
import com.chaskify.android.ui.model.TaskModel;
import com.chaskify.domain.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alberto on 29/12/17.
 */

public class TaskSnapItemModelDataMapper {
    public static List<TaskItemSnapModel> transform(List<Task> entities) {
        final List<TaskItemSnapModel> list = new ArrayList<>();
        Stream.of(entities).withoutNulls()
                .forEach(realmEvent -> list.add(transform(realmEvent)));
        return list;
    }

    public static TaskItemSnapModel transform(Task task) {
        TaskItemSnapModel taskItemSnapModel = new TaskItemSnapModel();
        taskItemSnapModel.setTask_id(task.getTask_id())
                .setTrans_type(task.getTrans_type())
                .setStatus(task.getStatus())
                .setDelivery_address(task.getDelivery_address())
                .setDelivery_date(task.getDelivery_date());
        return taskItemSnapModel;
    }
}
