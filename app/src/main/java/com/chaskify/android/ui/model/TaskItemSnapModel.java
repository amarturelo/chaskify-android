package com.chaskify.android.ui.model;

import java.util.Date;

/**
 * Created by alberto on 6/12/17.
 */

public class TaskItemSnapModel {
    private Date mDate;
    private String mClientName;
    private String mAddress;
    private String mStatus;

    public Date getDate() {
        return mDate;
    }

    public TaskItemSnapModel setDate(Date mDate) {
        this.mDate = mDate;
        return this;
    }

    public String getClientName() {
        return mClientName;
    }

    public TaskItemSnapModel setClientName(String mClientName) {
        this.mClientName = mClientName;
        return this;
    }

    public String getAddress() {
        return mAddress;
    }

    public TaskItemSnapModel setAddress(String mAddress) {
        this.mAddress = mAddress;
        return this;
    }

    public String getStatus() {
        return mStatus;
    }

    public TaskItemSnapModel setStatus(String mStatus) {
        this.mStatus = mStatus;
        return this;
    }
}
