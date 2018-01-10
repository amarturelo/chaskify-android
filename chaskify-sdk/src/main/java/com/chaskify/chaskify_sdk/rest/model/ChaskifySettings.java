package com.chaskify.chaskify_sdk.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    public ChaskifyIcons getIcons() {
        return icons;
    }

    public ChaskifySettings setIcons(ChaskifyIcons icons) {
        this.icons = icons;
        return this;
    }
}
