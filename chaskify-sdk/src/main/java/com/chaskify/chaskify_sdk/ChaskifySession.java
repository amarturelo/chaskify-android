package com.chaskify.chaskify_sdk;

import android.content.Context;

import com.chaskify.chaskify_sdk.rest.client.LoginRestClient;
import com.chaskify.chaskify_sdk.rest.client.NotificationRestClient;
import com.chaskify.chaskify_sdk.rest.client.ProfileRestClient;
import com.chaskify.chaskify_sdk.rest.client.SettingsRestClient;
import com.chaskify.chaskify_sdk.rest.client.TaskRestClient;
import com.chaskify.chaskify_sdk.rest.model.login.Credentials;

/**
 * Created by alberto on 6/12/17.
 */

public class ChaskifySession {
    private Credentials mCredentials;
    private ProfileConnectionConfig mHsConfig;

    private LoginRestClient mLoginRestClient;
    private ProfileRestClient mProfileRestClient;
    private TaskRestClient mTaskRestClient;
    private NotificationRestClient mNotificationRestClient;
    private SettingsRestClient mSettingsRestClient;

    public ChaskifySession(ProfileConnectionConfig mHsConfig) {
        this.mHsConfig = mHsConfig;
        this.mCredentials = mHsConfig.getCredentials();

        mLoginRestClient = new LoginRestClient(mCredentials);
        mProfileRestClient = new ProfileRestClient(mCredentials);
        mTaskRestClient = new TaskRestClient(mCredentials);
        mNotificationRestClient = new NotificationRestClient(mCredentials);
        mSettingsRestClient = new SettingsRestClient(mCredentials);
    }

    /**
     * Init the user-agent used by the REST requests.
     *
     * @param context the application context
     */
    public static void initUserAgent(Context context) {
        RestClient.initUserAgent(context);
    }

    public Credentials getCredentials() {
        return mCredentials;
    }

    public ProfileConnectionConfig getHsConfig() {
        return mHsConfig;
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
