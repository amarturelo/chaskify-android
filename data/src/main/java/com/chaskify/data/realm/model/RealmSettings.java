package com.chaskify.data.realm.model;

import io.realm.RealmObject;

/**
 * Created by alberto on 9/01/18.
 */

public class RealmSettings extends RealmObject {
    public static final String DRIVER_ID = "driverId";
    public String enabledPush;
    public String driverId;
    public RealmIcons icons;

    public RealmSettings() {
    }

    public String getEnabledPush() {
        return enabledPush;
    }

    public RealmSettings setEnabledPush(String enabledPush) {
        this.enabledPush = enabledPush;
        return this;
    }

    public String getDriverId() {
        return driverId;
    }

    public RealmSettings setDriverId(String driverId) {
        this.driverId = driverId;
        return this;
    }

    public RealmIcons getIcons() {
        return icons;
    }

    public RealmSettings setIcons(RealmIcons icons) {
        this.icons = icons;
        return this;
    }
}
