package com.chaskify.android.ui.model;

/**
 * Created by alberto on 12/12/17.
 */

public class CredentialsCacheItemModel {
    private String mDriverId;
    private String mDriverUsername;

    public CredentialsCacheItemModel() {
    }

    public String getDriverId() {
        return mDriverId;
    }

    public CredentialsCacheItemModel setDriverId(String mDriverId) {
        this.mDriverId = mDriverId;
        return this;
    }

    public String getDriverUsername() {
        return mDriverUsername;
    }

    public CredentialsCacheItemModel setDriverUsername(String mDriverUsername) {
        this.mDriverUsername = mDriverUsername;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CredentialsCacheItemModel)) return false;

        CredentialsCacheItemModel itemModel = (CredentialsCacheItemModel) o;

        return mDriverId != null ? mDriverId.equals(itemModel.mDriverId) : itemModel.mDriverId == null;
    }

    @Override
    public int hashCode() {
        return mDriverId != null ? mDriverId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CredentialsCacheItemModel{" +
                "mDriverId='" + mDriverId + '\'' +
                ", mDriverUsername='" + mDriverUsername + '\'' +
                '}';
    }
}
