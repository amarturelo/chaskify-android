package com.chaskify.chaskify_sdk.rest.client;

import com.chaskify.chaskify_sdk.HomeServerConnectionConfig;
import com.chaskify.chaskify_sdk.RestClient;
import com.chaskify.chaskify_sdk.rest.api.LoginApi;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.model.BaseResponse;
import com.chaskify.chaskify_sdk.rest.model.login.Credentials;
import com.chaskify.chaskify_sdk.rest.model.login.LoginResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by alberto on 7/12/17.
 */

public class LoginRestClient extends RestClient<LoginApi> {

    public LoginRestClient(HomeServerConnectionConfig hsConfig) {
        super(hsConfig, LoginApi.class);
    }

    public void loginWithUser(final String user, final String password, final ApiCallback<Credentials> callback) {
        login(user, password, callback);
    }

    private void login(String user, String password, final ApiCallback<Credentials> callback) {
        mApi.login(user, password, mHsConfig.getLang_id()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

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
                .registerTypeAdapter(BaseResponse.class, new JsonDeserializer<BaseResponse>() {
                    @Override
                    public BaseResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return null;
                    }
                })
                .create();
        return gson;
    }
}
