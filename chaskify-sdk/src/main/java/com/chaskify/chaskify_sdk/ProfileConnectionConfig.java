package com.chaskify.chaskify_sdk;

import com.chaskify.chaskify_sdk.rest.model.Icons;
import com.chaskify.chaskify_sdk.rest.model.login.Credentials;

/**
 * Created by alberto on 7/12/17.
 */

public class ProfileConnectionConfig {
    private int on_duty;
    private Credentials credentials;
    private Icons icons;

    public ProfileConnectionConfig() {
    }

    public int getOn_duty() {
        return on_duty;
    }

    public ProfileConnectionConfig setOn_duty(int on_duty) {
        this.on_duty = on_duty;
        return this;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public ProfileConnectionConfig setCredentials(Credentials credentials) {
        this.credentials = credentials;
        return this;
    }

    public Icons getIcons() {
        return icons;
    }

    public ProfileConnectionConfig setIcons(Icons icons) {
        this.icons = icons;
        return this;
    }
}
