package com.chaskify.chaskify_sdk;

import com.chaskify.chaskify_sdk.rest.model.Icons;
import com.chaskify.chaskify_sdk.rest.model.login.Credentials;

/**
 * Created by alberto on 7/12/17.
 */

public class HomeServerConnectionConfig {
    private Credentials credentials;
    private Icons icons;

    public HomeServerConnectionConfig() {
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public HomeServerConnectionConfig setCredentials(Credentials credentials) {
        this.credentials = credentials;
        return this;
    }

    public Icons getIcons() {
        return icons;
    }

    public HomeServerConnectionConfig setIcons(Icons icons) {
        this.icons = icons;
        return this;
    }
}
