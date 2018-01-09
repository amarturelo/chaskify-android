package com.chaskify.android.ui.model;

/**
 * Created by alberto on 8/01/18.
 */

public class ProfileItemModel {
    private String mProfileImage;
    private String mTeamName;

    public ProfileItemModel() {
    }

    public String getmProfileImage() {
        return mProfileImage;
    }

    public ProfileItemModel setmProfileImage(String mProfileImage) {
        this.mProfileImage = mProfileImage;
        return this;
    }

    public String getmTeamName() {
        return mTeamName;
    }

    public ProfileItemModel setmTeamName(String mTeamName) {
        this.mTeamName = mTeamName;
        return this;
    }
}
