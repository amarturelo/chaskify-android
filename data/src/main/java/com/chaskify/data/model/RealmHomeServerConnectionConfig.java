package com.chaskify.data.model;

import com.chaskify.domain.model.HomeServerConnectionConfig;

import io.realm.RealmObject;

/**
 * Created by alberto on 10/12/17.
 */

public class RealmHomeServerConnectionConfig extends RealmObject {
    private String timeZone;
    private String lang_id;
    public String deviceId;
    private boolean isDefault;
    private RealmCredentials credentials;

    public RealmHomeServerConnectionConfig() {
    }

    public String getTimeZone() {
        return timeZone;
    }

    public RealmHomeServerConnectionConfig setTimeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public String getLang_id() {
        return lang_id;
    }

    public RealmHomeServerConnectionConfig setLang_id(String lang_id) {
        this.lang_id = lang_id;
        return this;
    }

    public RealmCredentials getCredentials() {
        return credentials;
    }

    public RealmHomeServerConnectionConfig setCredentials(RealmCredentials credentials) {
        this.credentials = credentials;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public RealmHomeServerConnectionConfig setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public HomeServerConnectionConfig toDomain() {
        return new HomeServerConnectionConfig()
                .setDefault(this.isDefault())
                .setLang_id(this.getLang_id())
                .setTimeZone(this.getTimeZone())
                .setCredentials(this.getCredentials().toDomain());
    }

    public boolean isDefault() {
        return isDefault;
    }

    public RealmHomeServerConnectionConfig setDefault(boolean aDefault) {
        isDefault = aDefault;
        return this;
    }

    @Override
    public String toString() {
        return "RealmHomeServerConnectionConfig{" +
                "timeZone='" + timeZone + '\'' +
                ", lang_id='" + lang_id + '\'' +
                ", credentials=" + credentials +
                '}';
    }
}
