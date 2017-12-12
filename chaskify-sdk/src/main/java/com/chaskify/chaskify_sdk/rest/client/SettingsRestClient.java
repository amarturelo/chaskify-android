package com.chaskify.chaskify_sdk.rest.client;

import com.chaskify.chaskify_sdk.RestClient;
import com.chaskify.chaskify_sdk.rest.api.SettingsApi;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.exceptions.TokenNotFoundException;
import com.chaskify.chaskify_sdk.rest.model.Settings;
import com.chaskify.chaskify_sdk.rest.model.login.Credentials;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alberto on 7/12/17.
 */

public class SettingsRestClient extends RestClient<SettingsApi> {
    public SettingsRestClient(Credentials credentials) {
        super(credentials, SettingsApi.class);
    }

    public void getSettings(ApiCallback<Settings> callback) throws TokenNotFoundException {
        if (mCredentials != null)
            getSettings("es", mCredentials.accessToken, callback);
        else
            throw new TokenNotFoundException();
    }

    private void getSettings(String lang_id, String accessToken, final ApiCallback<Settings> callback) {
        mApi.settings(lang_id, accessToken)
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
