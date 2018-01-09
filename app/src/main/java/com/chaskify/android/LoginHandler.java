package com.chaskify.android;

import android.text.TextUtils;

import com.annimon.stream.Stream;
import com.chaskify.android.store.LoginStorage;
import com.chaskify.android.store.PreferenceStorage;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.client.LoginRestClient;
import com.chaskify.chaskify_sdk.rest.model.login.ChaskifyCredentials;
import com.chaskify.domain.model.Credentials;

import java.util.Collection;

/**
 * Created by alberto on 11/12/17.
 */

public class LoginHandler {


    public LoginHandler() {
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

        Collection<ChaskifySession> sessions = Chaskify.getInstance().getSessions();

        if (Stream.of(sessions)
                .filter(value -> TextUtils.equals(credentials.getDriverId(), value.getCredentials().getDriverId()))
                .toList().isEmpty()) {
            ChaskifySession session = Chaskify.createSession(credentials);
            Chaskify.getInstance().addSession(session, password);
        }
        callback.onSuccess(credentials);
    }

}
