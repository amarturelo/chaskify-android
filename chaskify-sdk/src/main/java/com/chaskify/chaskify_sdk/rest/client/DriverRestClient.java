package com.chaskify.chaskify_sdk.rest.client;

import com.chaskify.chaskify_sdk.RestClient;
import com.chaskify.chaskify_sdk.rest.api.DriverApi;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallbackSuccess;
import com.chaskify.chaskify_sdk.rest.exceptions.TokenNotFoundException;
import com.chaskify.chaskify_sdk.rest.model.login.ChaskifyCredentials;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alberto on 23/01/18.
 */

public class DriverRestClient extends RestClient<DriverApi> {
    public DriverRestClient(ChaskifyCredentials chaskifyCredentials) {
        super(chaskifyCredentials, DriverApi.class);
    }

    public void updatePosition(String lat, String lng, ApiCallbackSuccess callback) {
        if (mChaskifyCredentials != null)
            updatePosition(mChaskifyCredentials.getAccessToken(), lat, lng, callback);
        else
            callback.onChaskifyError(new TokenNotFoundException());
    }

    private void updatePosition(String accessToken, String lat, String lng, final ApiCallbackSuccess callback) {
        mApi.updateDriverPosition(accessToken, lat, lng)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        JsonObject baseResponse = getGson().fromJson(response.body().substring(1, response.body().length() - 1), JsonObject.class);
                        if (baseResponse.get("code").getAsInt() == 1) {
                            callback.onSuccess();

                        } else {
                            callback.onChaskifyError(new Exception(baseResponse.get("msg").getAsString()));
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        callback.onChaskifyError((Exception) t);
                    }
                });
    }

    public void onDuty(ApiCallbackSuccess callback) {
        if (mChaskifyCredentials != null)
            changeStatus(mChaskifyCredentials.getAccessToken(), "1", callback);
        else
            callback.onChaskifyError(new TokenNotFoundException());
    }

    public void offDuty(ApiCallbackSuccess callback) {
        if (mChaskifyCredentials != null)
            changeStatus(mChaskifyCredentials.getAccessToken(), "2", callback);
    }

    private void changeStatus(String accessToken, String status, final ApiCallbackSuccess callback) {
        mApi.changeDutyStatus(accessToken, status).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                JsonObject baseResponse = getGson().fromJson(response.body().substring(1, response.body().length() - 1), JsonObject.class);
                if (baseResponse.get("code").getAsInt() == 1) {
                    callback.onSuccess();

                } else {
                    callback.onChaskifyError(new Exception(baseResponse.get("msg").getAsString()));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onNetworkError((Exception) t);
            }
        });
    }
}
