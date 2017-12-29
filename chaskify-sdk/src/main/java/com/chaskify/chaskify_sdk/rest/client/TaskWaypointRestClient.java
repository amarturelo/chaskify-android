package com.chaskify.chaskify_sdk.rest.client;

import com.chaskify.chaskify_sdk.RestClient;
import com.chaskify.chaskify_sdk.rest.api.TaskWaypointApi;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.exceptions.TokenNotFoundException;
import com.chaskify.chaskify_sdk.rest.model.BaseResponse;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTask;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTaskWaypoint;
import com.chaskify.chaskify_sdk.rest.model.login.ChaskifyCredentials;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alberto on 28/12/17.
 */

public class TaskWaypointRestClient extends RestClient<TaskWaypointApi> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", /*Locale.getDefault()*/Locale.getDefault());

    public TaskWaypointRestClient(ChaskifyCredentials chaskifyCredentials) {
        super(chaskifyCredentials, TaskWaypointApi.class);
    }

    public void wayPointById(String task_id, ApiCallback<ChaskifyTaskWaypoint> callback) throws TokenNotFoundException {
        if (mChaskifyCredentials != null)
            wayPointById(task_id, mChaskifyCredentials.getAccessToken(), callback);
        else
            throw new TokenNotFoundException();
    }

    private void wayPointById(String task_id, String accessToken, final ApiCallback<ChaskifyTaskWaypoint> callback) {
        mApi.wayPointDetails(task_id, accessToken)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Type type = new TypeToken<BaseResponse<ChaskifyTaskWaypoint>>() {
                        }.getType();

                        JsonObject baseResponse = getGson().fromJson(response.body().substring(1, response.body().length() - 1), JsonObject.class);
                        if (baseResponse.get("code").getAsInt() == 1) {
                            BaseResponse<ChaskifyTaskWaypoint> listBaseResponse = getGson().fromJson(baseResponse, type);
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

    @Override
    protected Gson getGson() {
        return new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
    }
}
