package com.chaskify.domain.model;

/**
 * Created by alberto on 11/12/17.
 */

public class Credentials {
    private boolean isDefault;

    private String accessToken;

    private String username;

    private String password;

    private String deviceId;

    public Credentials() {
    }

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
}
