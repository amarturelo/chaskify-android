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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskHistoryItemModel)) return false;

        TaskHistoryItemModel that = (TaskHistoryItemModel) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (taskId != null ? !taskId.equals(that.taskId) : that.taskId != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return dateCreated != null ? dateCreated.equals(that.dateCreated) : that.dateCreated == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (taskId != null ? taskId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        return result;
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
