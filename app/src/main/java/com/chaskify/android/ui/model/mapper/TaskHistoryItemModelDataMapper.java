package com.chaskify.android.ui.model.mapper;

import com.annimon.stream.Stream;
import com.chaskify.android.ui.model.TaskHistoryItemModel;
import com.chaskify.android.ui.model.TaskModel;
import com.chaskify.domain.model.Task;
import com.chaskify.domain.model.TaskHistory;

import java.util.ArrayList;
import java.util.List;

public class TaskHistoryItemModelDataMapper {

    public static List<TaskHistoryItemModel> transform(List<TaskHistory> entities) {
        final List<TaskHistoryItemModel> list = new ArrayList<>();
        Stream.of(entities).withoutNulls()
                .forEach(realmEvent -> list.add(transform(realmEvent)));
        return list;
    }

    public static TaskHistoryItemModel transform(TaskHistory taskHistory) {
        TaskHistoryItemModel taskHistoryItemModel = new TaskHistoryItemModel();
        taskHistoryItemModel.setTaskId(taskHistory.getId())
                .setDateCreated(taskHistory.getDateCreated())
                .setStatus(taskHistory.getStatus())
                .setId(taskHistory.getId());
        return taskHistoryItemModel;
    }
}