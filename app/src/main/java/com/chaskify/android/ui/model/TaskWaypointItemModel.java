package com.chaskify.android.ui.model;

/**
 * Created by alberto on 27/12/17.
 */

public class TaskWaypointItemModel {
    private String mTaskStatus;
    private String mId;
    private String mTaskType;
    private String mDeliveryAddress;

    public TaskWaypointItemModel() {
    }

    public String getTaskStatus() {
        return mTaskStatus;
    }

    public TaskWaypointItemModel setTaskStatus(String mTaskStatus) {
        this.mTaskStatus = mTaskStatus;
        return this;
    }

    public String getId() {
        return mId;
    }

    public TaskWaypointItemModel setId(String mId) {
        this.mId = mId;
        return this;
    }

    public String getTaskType() {
        return mTaskType;
    }

    public TaskWaypointItemModel setTaskType(String mTaskType) {
        this.mTaskType = mTaskType;
        return this;
    }

    public String getDeliveryAddress() {
        return mDeliveryAddress;
    }

    public TaskWaypointItemModel setDeliveryAddress(String mDeliveryAddress) {
        this.mDeliveryAddress = mDeliveryAddress;
        return this;
    }
}
