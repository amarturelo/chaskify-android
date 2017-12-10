package com.chaskify.domain.model;

/**
 * Created by alberto on 7/12/17.
 */

public class HomeServerConnectionConfig {
    private boolean isDefault;
    private String timeZone;
    private String lang_id;
    private Credentials credentials;
    private String deviceId;

    public HomeServerConnectionConfig() {
    }

    public String getTimeZone() {
        return timeZone;
    }

    public HomeServerConnectionConfig setTimeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public String getLang_id() {
        return lang_id;
    }

    public HomeServerConnectionConfig setLang_id(String lang_id) {
        this.lang_id = lang_id;
        return this;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public HomeServerConnectionConfig setCredentials(Credentials credentials) {
        this.credentials = credentials;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public HomeServerConnectionConfig setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public HomeServerConnectionConfig setDefault(boolean aDefault) {
        isDefault = aDefault;
        return this;
    }

    @Override
    public String toString() {
        return "HomeServerConnectionConfig{" +
                "timeZone='" + timeZone + '\'' +
                ", lang_id='" + lang_id + '\'' +
                ", credentials=" + credentials +
                '}';
    }
}
