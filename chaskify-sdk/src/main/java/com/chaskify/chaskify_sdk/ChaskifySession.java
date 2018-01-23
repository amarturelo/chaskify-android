package com.chaskify.chaskify_sdk;

import android.content.Context;

import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallbackSuccess;
import com.chaskify.chaskify_sdk.rest.client.CalendarTaskRestClient;
import com.chaskify.chaskify_sdk.rest.client.LoginRestClient;
import com.chaskify.chaskify_sdk.rest.client.NotificationRestClient;
import com.chaskify.chaskify_sdk.rest.client.ProfileRestClient;
import com.chaskify.chaskify_sdk.rest.client.SettingsRestClient;
import com.chaskify.chaskify_sdk.rest.client.TaskRestClient;
import com.chaskify.chaskify_sdk.rest.client.TaskWayPointRestClient;
import com.chaskify.chaskify_sdk.rest.exceptions.TokenNotFoundException;
import com.chaskify.chaskify_sdk.rest.model.ChaskifySettings;
import com.chaskify.chaskify_sdk.rest.model.login.ChaskifyCredentials;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alberto on 6/12/17.
 */

public class ChaskifySession {

    private ChaskifySettings mChaskifySettings;

    private ChaskifyCredentials mChaskifyCredentials;

    private STATE mState = STATE.OFF_DUTY;

    private LoginRestClient mLoginRestClient;
    private ProfileRestClient mProfileRestClient;
    private TaskRestClient mTaskRestClient;
    private NotificationRestClient mNotificationRestClient;
    private SettingsRestClient mSettingsRestClient;
    private CalendarTaskRestClient mCalendarTaskRestClient;
    private TaskWayPointRestClient mTaskWayPointRestClient;

    public interface OnDutyChange {
        void onState(STATE state);
    }

    public List<OnDutyChange> mListenedDutyChange;

    public ChaskifySession(ChaskifyCredentials chaskifyCredentials) {
        mListenedDutyChange = new ArrayList<>();

        this.mChaskifyCredentials = chaskifyCredentials;

        mLoginRestClient = new LoginRestClient(mChaskifyCredentials);
        mProfileRestClient = new ProfileRestClient(mChaskifyCredentials);
        mTaskRestClient = new TaskRestClient(mChaskifyCredentials);
        mNotificationRestClient = new NotificationRestClient(mChaskifyCredentials);
        mSettingsRestClient = new SettingsRestClient(mChaskifyCredentials);
        mCalendarTaskRestClient = new CalendarTaskRestClient(mChaskifyCredentials);
        mTaskWayPointRestClient = new TaskWayPointRestClient(mChaskifyCredentials);
    }

    public void addDutyChangeListener(OnDutyChange onDutyChange) {
        if (onDutyChange != null)
            mListenedDutyChange.add(onDutyChange);
    }

    public void removeDutyChangeListener(OnDutyChange onDutyChange) {
        if (onDutyChange != null)
            mListenedDutyChange.remove(onDutyChange);
    }

    /**
     * Init the user-agent used by the REST requests.
     *
     * @param context the application context
     */
    public static void initUserAgent(Context context) {
        RestClient.initUserAgent(context);
    }

    public void getChaskifySettings(ApiCallback<ChaskifySettings> callback) {
        if (mChaskifySettings != null)
            callback.onSuccess(mChaskifySettings);
        else
            mSettingsRestClient.getSettings(callback);
    }

    public ChaskifyCredentials getCredentials() {
        return mChaskifyCredentials;
    }

    public void onDuty(final ApiCallbackSuccess callback) {
        ApiCallbackSuccess callbackSuccess = new ApiCallbackSuccess() {
            @Override
            public void onSuccess() {
                setState(STATE.ON_DUTY);
                if (callback != null)
                    callback.onSuccess();
            }

            @Override
            public void onNetworkError(Exception e) {
                if (callback != null)
                    callback.onNetworkError(e);
            }

            @Override
            public void onChaskifyError(Exception e) {
                if (callback != null)
                    callback.onChaskifyError(e);
            }

            @Override
            public void onUnexpectedError(Exception e) {
                if (callback != null)
                    callback.onUnexpectedError(e);
            }
        };

        mProfileRestClient.onDuty(callbackSuccess);
    }

    public void offDuty(final ApiCallbackSuccess callback) {
        ApiCallbackSuccess callbackSuccess = new ApiCallbackSuccess() {
            @Override
            public void onSuccess() {
                setState(STATE.OFF_DUTY);
                if (callback != null)
                    callback.onSuccess();
            }

            @Override
            public void onNetworkError(Exception e) {
                if (callback != null)
                    callback.onNetworkError(e);
            }

            @Override
            public void onChaskifyError(Exception e) {
                if (callback != null)
                    callback.onChaskifyError(e);
            }

            @Override
            public void onUnexpectedError(Exception e) {
                if (callback != null)
                    callback.onUnexpectedError(e);
            }
        };

        mProfileRestClient.onDuty(callbackSuccess);
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

    public ChaskifySession setChaskifySettings(ChaskifySettings mChaskifySettings) {
        this.mChaskifySettings = mChaskifySettings;
        return this;
    }

    public TaskWayPointRestClient getTaskWayPointRestClient() {
        return mTaskWayPointRestClient;
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

    public void updateSettingsPush(boolean enable, ApiCallbackSuccess callback) throws TokenNotFoundException {
        mSettingsRestClient.updateSettingsPush(enable, callback);
    }

    public void updateSettingsSound(String sound, ApiCallbackSuccess callback) throws TokenNotFoundException {
        mSettingsRestClient.updateSettingsSound(sound, callback);
    }

    public void updateImageProfile(String base64, ApiCallback<String> callback) {
        mProfileRestClient.updateImageProfileBase64(base64, callback);
    }

    public void updateProfile(String phone
            , ApiCallbackSuccess callback) throws TokenNotFoundException {
        mProfileRestClient.updateProfile(phone, callback);
    }

    public void logout(final ApiCallbackSuccess callback) throws TokenNotFoundException {
        mLoginRestClient.logout(callback);
    }

    public STATE getState() {
        return mState;
    }

    private void setState(STATE mState) {
        this.mState = mState;
        for (OnDutyChange onDutyChange :
                mListenedDutyChange) {
            onDutyChange.onState(mState);
        }
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

    public enum STATE {
        ON_DUTY, OFF_DUTY
    }
}
