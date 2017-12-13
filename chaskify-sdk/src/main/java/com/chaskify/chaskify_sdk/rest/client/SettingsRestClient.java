package com.chaskify.chaskify_sdk.rest.client;

import com.chaskify.chaskify_sdk.RestClient;
import com.chaskify.chaskify_sdk.rest.api.SettingsApi;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.exceptions.TokenNotFoundException;
import com.chaskify.chaskify_sdk.rest.model.ChaskifySettings;
import com.chaskify.chaskify_sdk.rest.model.login.ChaskifyCredentials;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alberto on 7/12/17.
 */

public class SettingsRestClient extends RestClient<SettingsApi> {
    public SettingsRestClient(ChaskifyCredentials chaskifyCredentials) {
        super(chaskifyCredentials, SettingsApi.class);
    }

    public void getSettings(ApiCallback<ChaskifySettings> callback) throws TokenNotFoundException {
        if (mChaskifyCredentials != null)
            getSettings("es", mChaskifyCredentials.accessToken, callback);
        else
            throw new TokenNotFoundException();
    }

    private void getSettings(String lang_id, String accessToken, final ApiCallback<ChaskifySettings> callback) {
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
