package com.chaskify.chaskify_sdk.rest.client;

import com.chaskify.chaskify_sdk.RestClient;
import com.chaskify.chaskify_sdk.crypto.Base64;
import com.chaskify.chaskify_sdk.rest.api.LoginApi;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.model.BaseResponse;
import com.chaskify.chaskify_sdk.rest.model.login.ChaskifyCredentials;
import com.chaskify.chaskify_sdk.rest.model.login.LoginResponse;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alberto on 7/12/17.
 */

public class LoginRestClient extends RestClient<LoginApi> {

    public LoginRestClient(ChaskifyCredentials chaskifyCredentials) {
        super(chaskifyCredentials, LoginApi.class);
    }

    public void loginWithUser(final String user, final String password, final ApiCallback<ChaskifyCredentials> callback) {
        login(user, password, callback);
    }

    public ChaskifyCredentials loginWithUserExplicitly(final String user, final String password) throws Exception {
        Response<String> response = mApi.login(user, password).execute();
        Type type = new TypeToken<BaseResponse<LoginResponse>>() {
        }.getType();

        JsonObject baseResponse = getGson().fromJson(response.body().substring(1, response.body().length() - 1), JsonObject.class);
        if (baseResponse.get("code").getAsInt() == 1) {
            BaseResponse<LoginResponse> loginRequest = getGson().fromJson(baseResponse, type);

            return new ChaskifyCredentials()
                    .setUsername(user)
                    .setAccessToken(loginRequest.getDetails().getToken());
        } else {
            throw new Exception(baseResponse.get("msg").getAsString());
        }
    }

    private void login(final String user, final String password, final ApiCallback<ChaskifyCredentials> callback) {
        mApi.login(user, password)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Type type = new TypeToken<BaseResponse<LoginResponse>>() {
                        }.getType();

                        JsonObject baseResponse = getGson().fromJson(response.body().substring(1, response.body().length() - 1), JsonObject.class);
                        if (baseResponse.get("code").getAsInt() == 1) {
                            BaseResponse<LoginResponse> loginRequest = getGson().fromJson(baseResponse, type);

                            callback.onSuccess(new ChaskifyCredentials()
                                    .setUsername(user)
                                    .setAccessToken(loginRequest.getDetails().getToken())

                            );
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

    @Override
    protected Gson getGson() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return gson;
    }
}
