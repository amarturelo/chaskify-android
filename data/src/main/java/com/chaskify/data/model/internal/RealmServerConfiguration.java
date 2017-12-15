package com.chaskify.data.model.internal;

import com.chaskify.data.model.chaskify.RealmIcons;
import com.chaskify.domain.model.ServerConfiguration;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by alberto on 12/12/17.
 */

public class RealmServerConfiguration extends RealmObject {
    public static String USERNAME = "username";
    @PrimaryKey
    private String username;

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

    public String getUsername() {
        return username;
    }

    public RealmServerConfiguration setUsername(String username) {
        this.username = username;
        return this;
    }

    public ServerConfiguration toDomain() {
        return new ServerConfiguration()
                .setIcons(getRealmIcons().toDomain());
    }
}
