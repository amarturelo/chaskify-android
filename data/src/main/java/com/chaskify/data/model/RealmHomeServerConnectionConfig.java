package com.chaskify.data.model;

import io.realm.RealmObject;

/**
 * Created by alberto on 10/12/17.
 */

public class RealmHomeServerConnectionConfig extends RealmObject {
    private String timeZone;
    private String lang_id;
    private RealmCredentials credentials;

    public RealmHomeServerConnectionConfig(String timeZone, String lang_id, RealmCredentials credentials) {
        this.timeZone = timeZone;
        this.lang_id = lang_id;
        this.credentials = credentials;
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

    @Override
    public String toString() {
        return "RealmHomeServerConnectionConfig{" +
                "timeZone='" + timeZone + '\'' +
                ", lang_id='" + lang_id + '\'' +
                ", credentials=" + credentials +
                '}';
    }
}
