package com.chaskify.chaskify_sdk;

import android.content.Context;

import com.chaskify.chaskify_sdk.rest.client.LoginRestClient;
import com.chaskify.chaskify_sdk.rest.client.NotificationRestClient;
import com.chaskify.chaskify_sdk.rest.client.ProfileRestClient;
import com.chaskify.chaskify_sdk.rest.client.SettingsRestClient;
import com.chaskify.chaskify_sdk.rest.client.TaskRestClient;
import com.chaskify.chaskify_sdk.rest.model.login.ChaskifyCredentials;

/**
 * Created by alberto on 6/12/17.
 */

public class ChaskifySession {
    private ChaskifyCredentials mChaskifyCredentials;

    private LoginRestClient mLoginRestClient;
    private ProfileRestClient mProfileRestClient;
    private TaskRestClient mTaskRestClient;
    private NotificationRestClient mNotificationRestClient;
    private SettingsRestClient mSettingsRestClient;

    public ChaskifySession(ChaskifyCredentials chaskifyCredentials) {
        this.mChaskifyCredentials = chaskifyCredentials;

        mLoginRestClient = new LoginRestClient(mChaskifyCredentials);
        mProfileRestClient = new ProfileRestClient(mChaskifyCredentials);
        mTaskRestClient = new TaskRestClient(mChaskifyCredentials);
        mNotificationRestClient = new NotificationRestClient(mChaskifyCredentials);
        mSettingsRestClient = new SettingsRestClient(mChaskifyCredentials);
    }

    /**
     * Init the user-agent used by the REST requests.
     *
     * @param context the application context
     */
    public static void initUserAgent(Context context) {
        RestClient.initUserAgent(context);
    }

    public ChaskifyCredentials getCredentials() {
        return mChaskifyCredentials;
    }

    public LoginRestClient getLoginRestClient() {
        return mLoginRestClient;
    }

    public ProfileRestClient getProfileRestClient() {
        return mProfileRestClient;
    }

    public TaskRestClient getTaskRestClient() {
        return mTaskRestClient;
    }

    public NotificationRestClient getNotificationRestClient() {
        return mNotificationRestClient;
    }

    public SettingsRestClient getSettingsRestClient() {
        return mSettingsRestClient;
    }
}
