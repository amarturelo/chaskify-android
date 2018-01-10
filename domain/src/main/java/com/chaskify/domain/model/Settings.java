package com.chaskify.domain.model;

/**
 * Created by alberto on 9/01/18.
 */

public class Settings {
    public String enabledPush;
    public String driverId;
    public Icons icons;

    public Settings() {
    }

    public String getEnabledPush() {
        return enabledPush;
    }

    public Settings setEnabledPush(String enabledPush) {
        this.enabledPush = enabledPush;
        return this;
    }

    public String getDriverId() {
        return driverId;
    }

    public Settings setDriverId(String driverId) {
        this.driverId = driverId;
        return this;
    }

    public Icons getIcons() {
        return icons;
    }

    public Settings setIcons(Icons icons) {
        this.icons = icons;
        return this;
    }
}
