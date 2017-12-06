package com.chaskify.android.model;

import java.util.Date;

/**
 * Created by alberto on 6/12/17.
 */

public class TaskSnapModel {
    private Date mDate;
    private String mClientName;
    private String mAddress;
    private String mStatus;

    public Date getDate() {
        return mDate;
    }

    public TaskSnapModel setDate(Date mDate) {
        this.mDate = mDate;
        return this;
    }

    public String getClientName() {
        return mClientName;
    }

    public TaskSnapModel setClientName(String mClientName) {
        this.mClientName = mClientName;
        return this;
    }

    public String getAddress() {
        return mAddress;
    }

    public TaskSnapModel setAddress(String mAddress) {
        this.mAddress = mAddress;
        return this;
    }

    public String getStatus() {
        return mStatus;
    }

    public TaskSnapModel setStatus(String mStatus) {
        this.mStatus = mStatus;
        return this;
    }
}
