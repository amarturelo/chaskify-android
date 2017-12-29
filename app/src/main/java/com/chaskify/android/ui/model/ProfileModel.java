package com.chaskify.android.ui.model;

/**
 * Created by alberto on 29/12/17.
 */

public class ProfileModel {
    private String mDriverId;
    private String mDriverName;
    private String mDriverTeam;
    private String mUrlAvatar;

    public ProfileModel() {
    }

    public String getmDriverId() {
        return mDriverId;
    }

    public ProfileModel setmDriverId(String mDriverId) {
        this.mDriverId = mDriverId;
        return this;
    }

    public String getmDriverName() {
        return mDriverName;
    }

    public ProfileModel setmDriverName(String mDriverName) {
        this.mDriverName = mDriverName;
        return this;
    }

    public String getmDriverTeam() {
        return mDriverTeam;
    }

    public ProfileModel setmDriverTeam(String mDriverTeam) {
        this.mDriverTeam = mDriverTeam;
        return this;
    }

    public String getmUrlAvatar() {
        return mUrlAvatar;
    }

    public ProfileModel setmUrlAvatar(String mUrlAvatar) {
        this.mUrlAvatar = mUrlAvatar;
        return this;
    }
}
