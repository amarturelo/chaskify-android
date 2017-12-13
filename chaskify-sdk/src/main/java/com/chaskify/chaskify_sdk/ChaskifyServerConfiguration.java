package com.chaskify.chaskify_sdk;

import com.chaskify.chaskify_sdk.rest.model.ChaskifyIcons;
import com.chaskify.chaskify_sdk.rest.model.login.ChaskifyCredentials;

/**
 * Created by alberto on 7/12/17.
 */

public class ChaskifyServerConfiguration {
    private ChaskifyCredentials chaskifyCredentials;
    private ChaskifyIcons chaskifyIcons;

    public ChaskifyServerConfiguration() {
    }

    public ChaskifyCredentials getChaskifyCredentials() {
        return chaskifyCredentials;
    }

    public ChaskifyServerConfiguration setChaskifyCredentials(ChaskifyCredentials chaskifyCredentials) {
        this.chaskifyCredentials = chaskifyCredentials;
        return this;
    }

    public ChaskifyIcons getChaskifyIcons() {
        return chaskifyIcons;
    }

    public ChaskifyServerConfiguration setChaskifyIcons(ChaskifyIcons chaskifyIcons) {
        this.chaskifyIcons = chaskifyIcons;
        return this;
    }
}
