package com.chaskify.android;

import com.chaskify.android.store.LoginStorage;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.chaskify_sdk.ProfileConnectionConfig;

/**
 * Created by alberto on 9/12/17.
 */

public class Chaskify {

    ChaskifySession mChaskifySession;

    private LoginStorage mLoginStorage;

    private static Chaskify ourInstance = null;

    public static Chaskify getInstance() {
        return ourInstance;
    }

    public static Chaskify getInstance(ChaskifySession mChaskifySession) {
        if (mChaskifySession != null && ourInstance == null)
            ourInstance = new Chaskify(mChaskifySession);
        return ourInstance;
    }

    private Chaskify(ChaskifySession chaskifySession) {
        mChaskifySession = chaskifySession;
    }

    public static ChaskifySession createSession(ProfileConnectionConfig homeServerConnectionConfig) {
        return new ChaskifySession(homeServerConnectionConfig);
    }

    public ChaskifySession getChaskifySession() {
        return mChaskifySession;
    }

    public void logout(){

    }

}
