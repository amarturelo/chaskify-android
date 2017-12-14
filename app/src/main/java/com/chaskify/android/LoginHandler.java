package com.chaskify.android;

import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.client.LoginRestClient;
import com.chaskify.chaskify_sdk.rest.model.login.ChaskifyCredentials;
import com.chaskify.domain.model.Credentials;

/**
 * Created by alberto on 11/12/17.
 */

public class LoginHandler {


    public LoginHandler() {
    }

    public void login(String username, String password, ApiCallback<Credentials> callback) {

        ApiCallback<ChaskifyCredentials> loginApiCallback = new ApiCallback<ChaskifyCredentials>() {
            @Override
            public void onSuccess(ChaskifyCredentials homeServerConnectionConfig) {
                onRegistrationDone(homeServerConnectionConfig, callback);
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

    private void onRegistrationDone(ChaskifyCredentials chaskifyCredentials, ApiCallback<Credentials> callback) {
        Credentials credentials = new Credentials()
                .setUsername(chaskifyCredentials.getUsername())
                .setPassword(chaskifyCredentials.getPassword())
                .setAccessToken(chaskifyCredentials.getAccessToken());

        ChaskifySession session = Chaskify.createSession(credentials);
        Chaskify.getInstance().addSession(session);
        callback.onSuccess(credentials);
    }
}
