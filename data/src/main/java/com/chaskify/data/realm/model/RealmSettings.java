package com.chaskify.data.realm.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by alberto on 9/01/18.
 */

public class RealmSettings extends RealmObject {
    public static final String DRIVER_ID = "driverId";
    public boolean enabledPush;
    @PrimaryKey
    public String driverId;
    public RealmIcons icons;

    public RealmSettings() {
    }

    public boolean isEnabledPush() {
        return enabledPush;
    }

    public RealmSettings setEnabledPush(boolean enabledPush) {
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
