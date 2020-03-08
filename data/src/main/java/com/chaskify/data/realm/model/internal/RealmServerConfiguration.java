package com.chaskify.data.realm.model.internal;

import com.chaskify.data.realm.model.RealmIcons;
import com.chaskify.domain.model.ServerConfiguration;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by alberto on 12/12/17.
 */

public class RealmServerConfiguration extends RealmObject {
    public static String DRIVER_ID = "driver_id";
    @PrimaryKey
    private String driver_id;

    private RealmIcons realmIcons;

    public RealmServerConfiguration() {
    }

    public RealmIcons getRealmIcons() {
        return realmIcons;
    }

    public RealmServerConfiguration setRealmIcons(RealmIcons realmIcons) {
        this.realmIcons = realmIcons;
        return this;
    }

    public String getDriverId() {
        return driver_id;
    }

    public RealmServerConfiguration setDriverId(String driver_id) {
        this.driver_id = driver_id;
        return this;
    }

    public ServerConfiguration toDomain() {
        return new ServerConfiguration()
                .setIcons(getRealmIcons().toDomain());
    }
}
