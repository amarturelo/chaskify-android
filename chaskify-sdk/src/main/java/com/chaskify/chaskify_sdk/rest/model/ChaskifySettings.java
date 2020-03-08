package com.chaskify.chaskify_sdk.rest.model;

import android.util.Pair;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by alberto on 7/12/17.
 */

public class ChaskifySettings {
    @SerializedName("enabled_push")
    @Expose
    public String enabledPush;
    @SerializedName("driver_id")
    @Expose
    public String driverId;
    @SerializedName("icons")
    @Expose
    public ChaskifyIcons icons;
    @SerializedName("language")
    @Expose
    public Pair<String, String> language;
    @SerializedName("notification_sound_url")
    @Expose
    public String notification_sound_url;


    public ChaskifySettings() {
    }

    public String getEnabledPush() {
        return enabledPush;
    }

    public ChaskifySettings setEnabledPush(String enabledPush) {
        this.enabledPush = enabledPush;
        return this;
    }

    public String getDriverId() {
        return driverId;
    }

    public ChaskifySettings setDriverId(String driverId) {
        this.driverId = driverId;
        return this;
    }

    public Pair<String, String> getLanguage() {
        return language;
    }

    public ChaskifySettings setLanguage(Pair<String, String> language) {
        this.language = language;
        return this;
    }

    public ChaskifyIcons getIcons() {
        return icons;
    }

    public ChaskifySettings setIcons(ChaskifyIcons icons) {
        this.icons = icons;
        return this;
    }
}
