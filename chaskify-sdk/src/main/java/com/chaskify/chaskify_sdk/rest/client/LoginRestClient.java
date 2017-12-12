package com.chaskify.chaskify_sdk.rest.client;

import com.chaskify.chaskify_sdk.ProfileConnectionConfig;
import com.chaskify.chaskify_sdk.RestClient;
import com.chaskify.chaskify_sdk.rest.api.LoginApi;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.model.BaseResponse;
import com.chaskify.chaskify_sdk.rest.model.login.Credentials;
import com.chaskify.chaskify_sdk.rest.model.login.LoginResponse;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by alberto on 7/12/17.
 */

public class LoginRestClient extends RestClient<LoginApi> {

    public LoginRestClient(Credentials credentials) {
        super(credentials, LoginApi.class);
    }

    public void loginWithUser(final String user, final String password, final ApiCallback<ProfileConnectionConfig> callback) {
        login(user, password, callback);
    }

    private void login(String user, String password, final ApiCallback<ProfileConnectionConfig> callback) {
        mApi.login(user, password)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Type type = new TypeToken<BaseResponse<LoginResponse>>() {
                        }.getType();

                        BaseResponse<LoginResponse> baseResponse = getGson().fromJson(response.body().substring(1, response.body().length() - 1), type);
                        Timber.d(baseResponse.toString());
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
