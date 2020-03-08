package com.chaskify.android.ui.model;

/**
 * Created by alberto on 29/12/17.
 */

public class SettingsModel {
    private boolean enabledPush;
    private String sound;

    public SettingsModel() {
    }

    public boolean isEnabledPush() {
        return enabledPush;
    }

    public SettingsModel setEnabledPush(boolean enabledPush) {
        this.enabledPush = enabledPush;
        return this;
    }

    public String getSound() {
        return sound;
    }

    public SettingsModel setSound(String sound) {
        this.sound = sound;
        return this;
    }
}
