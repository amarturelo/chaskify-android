package com.chaskify.data.model.internal;

import com.chaskify.domain.model.Credentials;

import io.realm.RealmObject;

/**
 * Created by alberto on 10/12/17.
 */

public class RealmCredentials extends RealmObject {
    public String accessToken;

    public String username;

    public String password;


    public RealmCredentials() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public RealmCredentials setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public RealmCredentials setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RealmCredentials setPassword(String password) {
        this.password = password;
        return this;
    }


    @Override
    public String toString() {
        return "RealmCredentials{" +
                "accessToken='" + accessToken + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Credentials toDomain() {
        return new Credentials()
                .setAccessToken(this.getAccessToken())
                .setUsername(this.getUsername())
                .setPassword(this.getPassword())
                ;
    }
}
