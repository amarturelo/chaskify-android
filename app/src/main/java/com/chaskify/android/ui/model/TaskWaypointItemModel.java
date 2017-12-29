package com.chaskify.android.ui.model;

/**
 * Created by alberto on 27/12/17.
 */

public class TaskWaypointItemModel {
    private String mId;
    private String mType;
    private String mDeliveryAddress;
    private String mStatus;

    public TaskWaypointItemModel() {
    }

    public String getId() {
        return mId;
    }

    public TaskWaypointItemModel setId(String mId) {
        this.mId = mId;
        return this;
    }

    public String getDeliveryAddress() {
        return mDeliveryAddress;
    }

    public TaskWaypointItemModel setDeliveryAddress(String mDeliveryAddress) {
        this.mDeliveryAddress = mDeliveryAddress;
        return this;
    }

    public String getType() {
        return mType;
    }

    public TaskWaypointItemModel setType(String mType) {
        this.mType = mType;
        return this;
    }


    public String getStatus() {
        return mStatus;
    }

    public TaskWaypointItemModel setStatus(String mStatus) {
        this.mStatus = mStatus;
        return this;
    }
}
