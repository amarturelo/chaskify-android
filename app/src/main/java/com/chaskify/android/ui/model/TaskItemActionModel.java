package com.chaskify.android.ui.model;

/**
 * Created by alberto on 14/01/18.
 */

public class TaskItemActionModel {
    private String id;
    private String status;

    public TaskItemActionModel() {
    }

    public String getId() {
        return id;
    }

    public TaskItemActionModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public TaskItemActionModel setStatus(String status) {
        this.status = status;
        return this;
    }
}
