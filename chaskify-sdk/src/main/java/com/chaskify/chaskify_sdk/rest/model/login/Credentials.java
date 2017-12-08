package com.chaskify.chaskify_sdk.rest.model.login;

/**
 * The user's credentials.
 */
public class Credentials {
    public String accessToken;
    //public String refreshToken;
    public String deviceId;

    public Credentials setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public Credentials setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "accessToken='" + accessToken + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }

}