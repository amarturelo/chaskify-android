package com.chaskify.domain.model;

/**
 * Created by alberto on 12/12/17.
 */

public class ServerConfiguration {
    private Icons icons;

    private String username;

    public ServerConfiguration() {
    }

    public Icons getIcons() {
        return icons;
    }

    public ServerConfiguration setIcons(Icons icons) {
        this.icons = icons;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ServerConfiguration setUsername(String username) {
        this.username = username;
        return this;
    }
}
