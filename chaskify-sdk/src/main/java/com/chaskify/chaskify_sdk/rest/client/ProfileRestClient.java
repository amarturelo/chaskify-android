package com.chaskify.chaskify_sdk.rest.client;

import com.chaskify.chaskify_sdk.RestClient;
import com.chaskify.chaskify_sdk.rest.api.ProfileApi;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallbackSuccess;
import com.chaskify.chaskify_sdk.rest.exceptions.TokenNotFoundException;
import com.chaskify.chaskify_sdk.rest.model.BaseResponse;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyProfile;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTask;
import com.chaskify.chaskify_sdk.rest.model.login.ChaskifyCredentials;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alberto on 7/12/17.
 */

public class ProfileRestClient extends RestClient<ProfileApi> {
    public ProfileRestClient(ChaskifyCredentials chaskifyCredentials) {
        super(chaskifyCredentials, ProfileApi.class);
    }

    public void updatePassword(String currentPassword, String newPassword, String confirmNewPassoword, ApiCallbackSuccess callback) throws TokenNotFoundException {
        if (mChaskifyCredentials != null)
            updatePassword(mChaskifyCredentials.getAccessToken()
                    , currentPassword
                    , newPassword
                    , confirmNewPassoword
                    , callback);
        else
            throw new TokenNotFoundException();
    }

    private void updatePassword(String accessToken, String currentPassword, String newPassword, String confirmNewPassoword, final ApiCallbackSuccess callback) {
        mApi.updatePassword(accessToken, currentPassword, newPassword, confirmNewPassoword)
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


    public void getProfile(ApiCallback<ChaskifyProfile> callback) throws TokenNotFoundException {
        if (mChaskifyCredentials != null)
            getProfile("es", mChaskifyCredentials.getAccessToken(), callback);
        else
            throw new TokenNotFoundException();
    }

    private void getProfile(String lang_id, String accessToken, final ApiCallback<ChaskifyProfile> callback) {
        mApi.profile(lang_id, accessToken)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Type type = new TypeToken<BaseResponse<ChaskifyProfile>>() {
                        }.getType();

                        JsonObject baseResponse = getGson().fromJson(response.body().substring(1, response.body().length() - 1), JsonObject.class);
                        if (baseResponse.get("code").getAsInt() == 1) {
                            BaseResponse<ChaskifyProfile> listBaseResponse = getGson().fromJson(baseResponse, type);
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

    public void updateProfile(String phone, ApiCallbackSuccess callback) throws TokenNotFoundException {
        if (mChaskifyCredentials != null)
            updateProfile(mChaskifyCredentials.getAccessToken(), phone, callback);
        else
            throw new TokenNotFoundException();
    }

    private void updateProfile(String accessToken, String phone, final ApiCallbackSuccess callback) {
        mApi.updateProfile(accessToken, phone)
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
