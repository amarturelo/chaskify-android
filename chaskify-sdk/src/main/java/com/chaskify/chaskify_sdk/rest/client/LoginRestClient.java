package com.chaskify.chaskify_sdk.rest.client;

import com.chaskify.chaskify_sdk.ChaskifyServerConfiguration;
import com.chaskify.chaskify_sdk.RestClient;
import com.chaskify.chaskify_sdk.rest.api.LoginApi;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.model.BaseResponse;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyError;
import com.chaskify.chaskify_sdk.rest.model.login.ChaskifyCredentials;
import com.chaskify.chaskify_sdk.rest.model.login.LoginResponse;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    private void login(final String user, final String password, final ApiCallback<ChaskifyCredentials> callback) {
        mApi.login(user, password)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Type type = new TypeToken<BaseResponse<LoginResponse>>() {
                        }.getType();

                        BaseResponse<LoginResponse> baseResponse = getGson().fromJson(response.body().substring(1, response.body().length() - 1), type);
                        if (baseResponse.getCode() == 1)
                            callback.onSuccess(new ChaskifyCredentials()
                                    .setUsername(user)
                                    .setPassword(password)
                                    .setAccessToken(baseResponse
                                            .getDetails()
                                            .getToken())
                            );
                        else
                            callback.onChaskifyError(new ChaskifyError(baseResponse.getMsg()));
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
