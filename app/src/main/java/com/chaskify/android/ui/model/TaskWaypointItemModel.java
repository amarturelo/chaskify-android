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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskWaypointItemModel)) return false;

        TaskWaypointItemModel that = (TaskWaypointItemModel) o;

        if (mId != null ? !mId.equals(that.mId) : that.mId != null) return false;
        if (mType != null ? !mType.equals(that.mType) : that.mType != null) return false;
        if (mDeliveryAddress != null ? !mDeliveryAddress.equals(that.mDeliveryAddress) : that.mDeliveryAddress != null)
            return false;
        return mStatus != null ? mStatus.equals(that.mStatus) : that.mStatus == null;
    }

    @Override
    public int hashCode() {
        int result = mId != null ? mId.hashCode() : 0;
        result = 31 * result + (mType != null ? mType.hashCode() : 0);
        result = 31 * result + (mDeliveryAddress != null ? mDeliveryAddress.hashCode() : 0);
        result = 31 * result + (mStatus != null ? mStatus.hashCode() : 0);
        return result;
    }
}
