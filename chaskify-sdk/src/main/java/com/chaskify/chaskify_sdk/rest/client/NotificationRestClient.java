package com.chaskify.chaskify_sdk.rest.client;

import com.chaskify.chaskify_sdk.ProfileConnectionConfig;
import com.chaskify.chaskify_sdk.RestClient;
import com.chaskify.chaskify_sdk.rest.api.NotificationApi;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.exceptions.TokenNotFoundException;
import com.chaskify.chaskify_sdk.rest.model.Notification;
import com.chaskify.chaskify_sdk.rest.model.login.Credentials;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alberto on 7/12/17.
 */

public class NotificationRestClient extends RestClient<NotificationApi> {
    public NotificationRestClient(Credentials credentials) {
        super(credentials, NotificationApi.class);
    }

    public void getNotifications(final ApiCallback<List<Notification>> callback) throws TokenNotFoundException {
        if (mCredentials != null)
            getNotifications("300", "en", mCredentials.accessToken, callback);
        else
            throw new TokenNotFoundException();
    }

    private void getNotifications(String timeZone, String lang_id, String accessToken, final ApiCallback<List<Notification>> callback) throws TokenNotFoundException {
        mApi.notifications(timeZone, lang_id, accessToken)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        callback.onNetworkError((Exception) t);
                    }
                });
    }
}
