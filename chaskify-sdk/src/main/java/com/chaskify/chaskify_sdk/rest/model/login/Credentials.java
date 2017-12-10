package com.chaskify.chaskify_sdk.rest.model.login;

/**
 * The user's credentials.
 */
public class Credentials {
    public String accessToken;

    public String username;

    public String password;

    public String deviceId;

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

    @Override
    public String toString() {
        return "Credentials{" +
                "accessToken='" + accessToken + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}