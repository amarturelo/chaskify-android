package com.chaskify.chaskify_sdk.rest.model.login;

/**
 * The user's credentials.
 */
public class ChaskifyCredentials {
    public String accessToken;

    public String username;

    public String password;

    public String deviceId;

    public String getAccessToken() {
        return accessToken;
    }

    public ChaskifyCredentials setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ChaskifyCredentials setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ChaskifyCredentials setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public ChaskifyCredentials setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    @Override
    public String toString() {
        return "ChaskifyCredentials{" +
                "accessToken='" + accessToken + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}