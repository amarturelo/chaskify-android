package com.chaskify.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alberto on 11/12/17.
 */

public class Credentials implements Parcelable {
    private boolean isDefault;

    private String accessToken;

    private String username;

    private String password;

    private String deviceId;

    public Credentials() {
    }

    protected Credentials(Parcel in) {
        isDefault = in.readByte() != 0;
        accessToken = in.readString();
        username = in.readString();
        password = in.readString();
        deviceId = in.readString();
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

    public String getPassword() {
        return password;
    }

    public Credentials setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Credentials setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public Credentials setDefault(boolean aDefault) {
        this.isDefault = aDefault;
        return this;
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "isDefault=" + isDefault +
                ", accessToken='" + accessToken + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isDefault ? 1 : 0));
        dest.writeString(accessToken);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(deviceId);
    }
}
