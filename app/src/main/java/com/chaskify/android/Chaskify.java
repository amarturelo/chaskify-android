package com.chaskify.android;

import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.chaskify_sdk.ProfileConnectionConfig;

/**
 * Created by alberto on 9/12/17.
 */

public class Chaskify {

    ChaskifySession mChaskifySession;

    private static final Chaskify ourInstance = null;

    public static Chaskify getInstance() {
        return ourInstance;
    }

    private Chaskify(ChaskifySession chaskifySession) {
        mChaskifySession = chaskifySession;
    }

    public ChaskifySession createSession(ProfileConnectionConfig homeServerConnectionConfig) {
        return new ChaskifySession(homeServerConnectionConfig);
    }

    public ChaskifySession getChaskifySession() {
        return mChaskifySession;
    }

}
