package com.chaskify.data.model;

import com.chaskify.data.model.internal.RealmIcons;
import com.chaskify.domain.model.HomeServerConnectionConfig;

import io.realm.RealmObject;

/**
 * Created by alberto on 10/12/17.
 */

public class RealmHomeServerConnectionConfig extends RealmObject {
    private boolean isDefault;
    private RealmCredentials credentials;
    private RealmIcons realmIcons;

    public RealmHomeServerConnectionConfig() {
    }

    public RealmCredentials getCredentials() {
        return credentials;
    }

    public RealmHomeServerConnectionConfig setCredentials(RealmCredentials credentials) {
        this.credentials = credentials;
        return this;
    }

    public RealmIcons getRealmIcons() {
        return realmIcons;
    }

    public RealmHomeServerConnectionConfig setRealmIcons(RealmIcons realmIcons) {
        this.realmIcons = realmIcons;
        return this;
    }

    public HomeServerConnectionConfig toDomain() {
        return new HomeServerConnectionConfig()
                .setDefault(this.isDefault())
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
                ", credentials=" + credentials +
                '}';
    }
}
