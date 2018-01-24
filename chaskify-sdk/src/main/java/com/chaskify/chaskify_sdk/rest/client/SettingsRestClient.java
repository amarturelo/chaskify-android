package com.chaskify.chaskify_sdk.rest.client;

import com.chaskify.chaskify_sdk.RestClient;
import com.chaskify.chaskify_sdk.rest.api.SettingsApi;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallbackSuccess;
import com.chaskify.chaskify_sdk.rest.exceptions.TokenNotFoundException;
import com.chaskify.chaskify_sdk.rest.model.BaseResponse;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyProfile;
import com.chaskify.chaskify_sdk.rest.model.ChaskifySettings;
import com.chaskify.chaskify_sdk.rest.model.login.ChaskifyCredentials;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

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

    public void getSettings(ApiCallback<ChaskifySettings> callback) {
        if (mChaskifyCredentials != null)
            getSettings(mChaskifyCredentials.getAccessToken(), callback);
        else
            callback.onChaskifyError(new TokenNotFoundException());
    }

    private void getSettings(String accessToken, final ApiCallback<ChaskifySettings> callback) {
        mApi.settings("es", accessToken)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Type type = new TypeToken<BaseResponse<ChaskifySettings>>() {
                        }.getType();

                        JsonObject baseResponse = getGson().fromJson(response.body().substring(1, response.body().length() - 1), JsonObject.class);
                        if (baseResponse.get("code").getAsInt() == 1) {
                            BaseResponse<ChaskifySettings> listBaseResponse = getGson().fromJson(baseResponse, type);
                            callback.onSuccess(listBaseResponse.getDetails());

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

    public void updateSettingsPush(boolean enable, ApiCallbackSuccess callback) {
        if (mChaskifyCredentials != null)
            updateSettingsPush(enable, mChaskifyCredentials.getAccessToken(), callback);
        else
            callback.onChaskifyError(new TokenNotFoundException());
    }

    public void updateSettingsSound(String ringTone, ApiCallbackSuccess callback) throws TokenNotFoundException {
        if (mChaskifyCredentials != null)
            updateSettingsSound(ringTone, mChaskifyCredentials.getAccessToken(), callback);
        else
            throw new TokenNotFoundException();
    }

    private void updateSettingsSound(String sound, String accessToken, final ApiCallbackSuccess callback) {
        mApi.settingsUpdateRingTone("es", sound, accessToken).enqueue(new Callback<String>() {
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

    private void updateSettingsPush(boolean enable, String accessToken, final ApiCallbackSuccess callback) {
        mApi.settingsUpdatePush("es", enable ? "1" : "0", accessToken)
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
                        callback.onNetworkError((Exception) t);
                    }
                });
    }
}
