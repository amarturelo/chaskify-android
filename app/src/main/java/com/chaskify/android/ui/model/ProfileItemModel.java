package com.chaskify.android.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alberto on 8/01/18.
 */

public class ProfileItemModel implements Parcelable {
    private String mProfileImage;
    private String mTeamName;
    private String mDriverId;
    private String mDriverUsername;

    public ProfileItemModel() {
    }

    protected ProfileItemModel(Parcel in) {
        mProfileImage = in.readString();
        mTeamName = in.readString();
        mDriverId = in.readString();
        mDriverUsername = in.readString();
    }

    public static final Creator<ProfileItemModel> CREATOR = new Creator<ProfileItemModel>() {
        @Override
        public ProfileItemModel createFromParcel(Parcel in) {
            return new ProfileItemModel(in);
        }

        @Override
        public ProfileItemModel[] newArray(int size) {
            return new ProfileItemModel[size];
        }
    };

    public String getProfileImage() {
        return mProfileImage;
    }

    public ProfileItemModel setProfileImage(String mProfileImage) {
        this.mProfileImage = mProfileImage;
        return this;
    }

    public String getTeamName() {
        return mTeamName;
    }

    public ProfileItemModel setTeamName(String mTeamName) {
        this.mTeamName = mTeamName;
        return this;
    }

    public String getDriverId() {
        return mDriverId;
    }

    public ProfileItemModel setDriverId(String mDriverId) {
        this.mDriverId = mDriverId;
        return this;
    }

    public String getDriverUsername() {
        return mDriverUsername;
    }

    public ProfileItemModel setDriverUsername(String mDriverUsername) {
        this.mDriverUsername = mDriverUsername;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mProfileImage);
        dest.writeString(mTeamName);
        dest.writeString(mDriverId);
        dest.writeString(mDriverUsername);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfileItemModel)) return false;

        ProfileItemModel that = (ProfileItemModel) o;

        if (mProfileImage != null ? !mProfileImage.equals(that.mProfileImage) : that.mProfileImage != null)
            return false;
        if (mTeamName != null ? !mTeamName.equals(that.mTeamName) : that.mTeamName != null)
            return false;
        if (mDriverId != null ? !mDriverId.equals(that.mDriverId) : that.mDriverId != null)
            return false;
        return mDriverUsername != null ? mDriverUsername.equals(that.mDriverUsername) : that.mDriverUsername == null;
    }

    @Override
    public int hashCode() {
        int result = mProfileImage != null ? mProfileImage.hashCode() : 0;
        result = 31 * result + (mTeamName != null ? mTeamName.hashCode() : 0);
        result = 31 * result + (mDriverId != null ? mDriverId.hashCode() : 0);
        result = 31 * result + (mDriverUsername != null ? mDriverUsername.hashCode() : 0);
        return result;
    }
}
