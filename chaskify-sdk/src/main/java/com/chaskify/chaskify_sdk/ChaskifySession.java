package com.chaskify.chaskify_sdk;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;

import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallbackSuccess;
import com.chaskify.chaskify_sdk.rest.client.CalendarTaskRestClient;
import com.chaskify.chaskify_sdk.rest.client.LoginRestClient;
import com.chaskify.chaskify_sdk.rest.client.NotificationRestClient;
import com.chaskify.chaskify_sdk.rest.client.ProfileRestClient;
import com.chaskify.chaskify_sdk.rest.client.SettingsRestClient;
import com.chaskify.chaskify_sdk.rest.client.TaskRestClient;
import com.chaskify.chaskify_sdk.rest.client.TaskWaypointRestClient;
import com.chaskify.chaskify_sdk.rest.exceptions.TokenNotFoundException;
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
    private CalendarTaskRestClient mCalendarTaskRestClient;
    private TaskWaypointRestClient mTaskWaypointRestClient;

    public ChaskifySession(ChaskifyCredentials chaskifyCredentials) {
        this.mChaskifyCredentials = chaskifyCredentials;

        mLoginRestClient = new LoginRestClient(mChaskifyCredentials);
        mProfileRestClient = new ProfileRestClient(mChaskifyCredentials);
        mTaskRestClient = new TaskRestClient(mChaskifyCredentials);
        mNotificationRestClient = new NotificationRestClient(mChaskifyCredentials);
        mSettingsRestClient = new SettingsRestClient(mChaskifyCredentials);
        mCalendarTaskRestClient = new CalendarTaskRestClient(mChaskifyCredentials);
        mTaskWaypointRestClient = new TaskWaypointRestClient(mChaskifyCredentials);
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

    public CalendarTaskRestClient getCalendarTaskRestClient() {
        return mCalendarTaskRestClient;
    }

    public ChaskifySession setCalendarTaskRestClient(CalendarTaskRestClient mCalendarTaskRestClient) {
        this.mCalendarTaskRestClient = mCalendarTaskRestClient;
        return this;
    }

    public TaskWaypointRestClient getTaskWaypointRestClient() {
        return mTaskWaypointRestClient;
    }

    public void updatePasswordProfile(String oldPassword
            , String newPassword
            , String confirmNewPassword
            , ApiCallbackSuccess callback) throws TokenNotFoundException {
        mProfileRestClient.updatePassword(oldPassword, newPassword, confirmNewPassword, callback);
    }

    public void updateVehicle(String transport_type_id
            , String transport_description
            , String licence_plate
            , String color
            , ApiCallbackSuccess callback) throws TokenNotFoundException {
        mProfileRestClient.updateVehicle(transport_type_id, transport_description, licence_plate, color, callback);
    }

    public void updateImageProfile(String base64, ApiCallbackSuccess callback) throws TokenNotFoundException {
        mProfileRestClient.updateImageProfileBase64(base64, callback);
    }

    public void updateProfile(String phone
            , ApiCallbackSuccess callback) throws TokenNotFoundException {
        mProfileRestClient.updateProfile(phone, callback);
    }

    public void logout(final ApiCallbackSuccess callback) throws TokenNotFoundException {
        mLoginRestClient.logout(callback);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChaskifySession)) return false;

        ChaskifySession that = (ChaskifySession) o;

        return mChaskifyCredentials != null ? mChaskifyCredentials.equals(that.mChaskifyCredentials) : that.mChaskifyCredentials == null;
    }

    @Override
    public String toString() {
        return "ChaskifySession{" +
                "mChaskifyCredentials=" + mChaskifyCredentials +
                '}';
    }

    @Override
    public int hashCode() {
        return mChaskifyCredentials != null ? mChaskifyCredentials.hashCode() : 0;
    }
}
