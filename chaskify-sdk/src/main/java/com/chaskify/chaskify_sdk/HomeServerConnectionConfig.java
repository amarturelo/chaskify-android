package com.chaskify.chaskify_sdk;

import com.chaskify.chaskify_sdk.rest.model.login.Credentials;

/**
 * Created by alberto on 7/12/17.
 */

public class HomeServerConnectionConfig {
    private String timeZone;
    private String lang_id;
    private Credentials credentials;

    public HomeServerConnectionConfig(String timeZone, String lang_id, Credentials credentials) {
        this.timeZone = timeZone;
        this.lang_id = lang_id;
        this.credentials = credentials;
    }

    public HomeServerConnectionConfig(String timeZone, String lang_id) {
        this(timeZone, lang_id, null);
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


    @Override
    public String toString() {
        return "HomeServerConnectionConfig{" +
                "timeZone='" + timeZone + '\'' +
                ", lang_id='" + lang_id + '\'' +
                ", credentials=" + credentials +
                '}';
    }
}
