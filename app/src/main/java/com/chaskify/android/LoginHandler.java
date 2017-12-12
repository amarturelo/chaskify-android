package com.chaskify.android;

import android.text.TextUtils;

import com.chaskify.android.store.LoginStorage;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.chaskify_sdk.ProfileConnectionConfig;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.client.LoginRestClient;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyError;
import com.chaskify.chaskify_sdk.rest.model.login.Credentials;

import java.util.Collection;

/**
 * Created by alberto on 11/12/17.
 */

public class LoginHandler {

    private LoginStorage mLoginStorage;

    public LoginHandler() {
        mLoginStorage = new LoginStorage();
    }

    public void login(String username, String password, ApiCallback<ProfileConnectionConfig> callback) {

        ApiCallback<ProfileConnectionConfig> loginApiCallback = new ApiCallback<ProfileConnectionConfig>() {
            @Override
            public void onSuccess(ProfileConnectionConfig homeServerConnectionConfig) {
                onRegistrationDone(homeServerConnectionConfig, callback);
            }

            @Override
            public void onNetworkError(Exception e) {
                callback.onNetworkError(e);
            }

            @Override
            public void onMatrixError(ChaskifyError e) {
                callback.onMatrixError(e);
            }

            @Override
            public void onUnexpectedError(Exception e) {
                callback.onUnexpectedError(e);
            }
        };

        callLogin(username, password, loginApiCallback);

    }

    private void callLogin(String username, String password, ApiCallback<ProfileConnectionConfig> loginApiCallback) {
        LoginRestClient loginRestClient = new LoginRestClient(null);
        loginRestClient.loginWithUser(username, password, loginApiCallback);
    }

    private void onRegistrationDone(ProfileConnectionConfig homeServerConnectionConfig, ApiCallback<ProfileConnectionConfig> callback) {
        ChaskifySession session = Chaskify.getInstance().createSession(homeServerConnectionConfig);
        mLoginStorage.addCredentials(homeServerConnectionConfig.getCredentials());
        Chaskify.getInstance(session);
        callback.onSuccess(homeServerConnectionConfig);
    }
}
