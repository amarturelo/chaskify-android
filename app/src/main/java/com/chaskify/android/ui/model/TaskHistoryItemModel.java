package com.chaskify.android.ui.model;

import java.util.Date;

/**
 * Created by alberto on 27/12/17.
 */

public class TaskHistoryItemModel {
    private String id;
    private String taskId;
    private String status;
    private Date dateCreated;

    public TaskHistoryItemModel() {
    }

    public String getId() {
        return id;
    }

    public TaskHistoryItemModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public TaskHistoryItemModel setStatus(String status) {
        this.status = status;
        return this;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public TaskHistoryItemModel setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public TaskHistoryItemModel setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    @Override
    public String toString() {
        return "TaskHistoryItemModel{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
