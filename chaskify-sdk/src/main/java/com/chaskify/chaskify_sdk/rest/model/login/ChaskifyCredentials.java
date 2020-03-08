package com.chaskify.chaskify_sdk.rest.model.login;

/**
 * The user's credentials.
 */
public class ChaskifyCredentials {
    private String accessToken;

    private String driver_id;

    private String username;

    public String getAccessToken() {
        return accessToken;
    }

    public ChaskifyCredentials setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ChaskifyCredentials setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getDriverId() {
        return driver_id;
    }

    public ChaskifyCredentials setDriverId(String driver_id) {
        this.driver_id = driver_id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChaskifyCredentials)) return false;

        ChaskifyCredentials that = (ChaskifyCredentials) o;

        return username != null ? username.equals(that.username) : that.username == null;
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ChaskifyCredentials{" +
                "accessToken='" + accessToken + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}