package com.chaskify.android;

import android.accounts.AccountManager;
import android.text.TextUtils;

import com.chaskify.android.store.LoginStorage;
import com.chaskify.android.store.PreferenceStorage;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallbackSuccess;
import com.chaskify.chaskify_sdk.rest.client.LoginRestClient;
import com.chaskify.chaskify_sdk.rest.model.login.ChaskifyCredentials;
import com.chaskify.domain.model.Credentials;

import java.util.List;

/**
 * Created by alberto on 11/12/17.
 */

public class LoginHandler {

    private LoginStorage mLoginStorage;

    private PreferenceStorage mPreferenceStorage;

    public LoginHandler(LoginStorage mLoginStorage, PreferenceStorage preferenceStorage) {
        this.mLoginStorage = mLoginStorage;
        this.mPreferenceStorage = preferenceStorage;
    }

    public void login(String username, String password, ApiCallback<Credentials> callback) {

        ApiCallback<ChaskifyCredentials> loginApiCallback = new ApiCallback<ChaskifyCredentials>() {
            @Override
            public void onSuccess(ChaskifyCredentials chaskifyCredentials) {
                onRegistrationDone(chaskifyCredentials, password, callback);
            }

            @Override
            public void onNetworkError(Exception e) {
                callback.onNetworkError(e);
            }

            @Override
            public void onChaskifyError(Exception e) {
                callback.onChaskifyError(e);
            }

            @Override
            public void onUnexpectedError(Exception e) {
                callback.onUnexpectedError(e);
            }
        };

        callLogin(username, password, loginApiCallback);

    }

    private void callLogin(String username, String password, ApiCallback<ChaskifyCredentials> loginApiCallback) {
        LoginRestClient loginRestClient = new LoginRestClient(null);
        loginRestClient.loginWithUser(username, password, loginApiCallback);
    }

    private void onRegistrationDone(ChaskifyCredentials chaskifyCredentials, String password, ApiCallback<Credentials> callback) {
        Credentials credentials = new Credentials()
                .setUsername(chaskifyCredentials.getUsername())
                .setDriverId(chaskifyCredentials.getDriverId())
                .setAccessToken(chaskifyCredentials.getAccessToken());

        if (mLoginStorage
                .addCredentials(credentials
                        , password
                )) {
            ChaskifySession session = Chaskify.createSession(credentials);
            Chaskify.getInstance().addSession(session);
        }
        mPreferenceStorage.setDefault(credentials.getUsername());
        callback.onSuccess(credentials);
    }

    public void changePassword(String currentPassword, String newPassword, String confirmNewPassword, ApiCallbackSuccess callback) {
        ApiCallbackSuccess callbackSuccess = new ApiCallbackSuccess() {
            @Override
            public void onSuccess() {
                onChangePasswordDone(newPassword, callback);
            }

            @Override
            public void onNetworkError(Exception e) {

            }

            @Override
            public void onChaskifyError(Exception e) {

            }

            @Override
            public void onUnexpectedError(Exception e) {

            }
        };

        callChangePassword(currentPassword, newPassword, confirmNewPassword, callbackSuccess);
    }

    private void onChangePasswordDone(String newPassword, ApiCallbackSuccess callback) {

    }

    private void callChangePassword(String currentPassword, String newPassword, String confirmNewPassword, ApiCallbackSuccess callbackSuccess) {
        LoginRestClient loginRestClient = new LoginRestClient(null);
    }

}
