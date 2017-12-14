package com.chaskify.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alberto on 11/12/17.
 */

public class Credentials implements Parcelable {
    private String accessToken;

    private String username;

    public Credentials() {
    }

    protected Credentials(Parcel in) {
        accessToken = in.readString();
        username = in.readString();
    }

    public static final Creator<Credentials> CREATOR = new Creator<Credentials>() {
        @Override
        public Credentials createFromParcel(Parcel in) {
            return new Credentials(in);
        }

        @Override
        public Credentials[] newArray(int size) {
            return new Credentials[size];
        }
    };

    public String getAccessToken() {
        return accessToken;
    }

    public Credentials setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Credentials setUsername(String username) {
        this.username = username;
        return this;
    }


    @Override
    public String toString() {
        return "Credentials{" +
                ", accessToken='" + accessToken + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(accessToken);
        dest.writeString(username);
    }
}
