package com.chaskify.chaskify_sdk.rest.client;

import com.chaskify.chaskify_sdk.RestClient;
import com.chaskify.chaskify_sdk.rest.api.TaskWayPointApi;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.exceptions.TokenNotFoundException;
import com.chaskify.chaskify_sdk.rest.model.BaseResponse;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTask;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTaskWayPoint;
import com.chaskify.chaskify_sdk.rest.model.login.ChaskifyCredentials;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by alberto on 28/12/17.
 */

public class TaskWayPointRestClient extends RestClient<TaskWayPointApi> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", /*Locale.getDefault()*/Locale.getDefault());

    public TaskWayPointRestClient(ChaskifyCredentials chaskifyCredentials) {
        super(chaskifyCredentials, TaskWayPointApi.class);
        Timber.tag(this.getClass().getSimpleName());
    }

    public void wayPointById(String task_id, ApiCallback<ChaskifyTaskWayPoint> callback) throws TokenNotFoundException {
        if (mChaskifyCredentials != null)
            wayPointById(task_id, mChaskifyCredentials.getAccessToken(), callback);
        else
            callback.onChaskifyError(new TokenNotFoundException());
    }

    private void wayPointById(String task_id, String accessToken, final ApiCallback<ChaskifyTaskWayPoint> callback) {
        mApi.wayPointDetails(task_id, accessToken)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Type type = new TypeToken<BaseResponse<ChaskifyTaskWayPoint>>() {
                        }.getType();

                        JsonObject baseResponse = getGson().fromJson(response.body().substring(1, response.body().length() - 1), JsonObject.class);
                        if (baseResponse.get("code").getAsInt() == 1) {
                            BaseResponse<ChaskifyTaskWayPoint> listBaseResponse = getGson().fromJson(baseResponse, type);
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

    public void changeTaskWayPointStatus(String wayPoint_id, ChaskifyTaskWayPoint.STATUS status, String lat, String lng, ApiCallback<ChaskifyTaskWayPoint> callback) {
        if (mChaskifyCredentials != null)
            changeTaskWayPointStatus(mChaskifyCredentials.getAccessToken()
                    , wayPoint_id
                    , status
                    , lat
                    , lng
                    , callback);
        else
            callback.onChaskifyError(new TokenNotFoundException());
    }

    private void changeTaskWayPointStatus(String accessToken, String wayPoint_id, ChaskifyTaskWayPoint.STATUS status, String lat, String lng, final ApiCallback<ChaskifyTaskWayPoint> callback) {
        mApi.updateStatus(accessToken
                , wayPoint_id
                , String.valueOf(new Date().getTimezoneOffset())
                , status.getText()
                , lat
                , lng).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Type type = new TypeToken<BaseResponse<ChaskifyTaskWayPoint>>() {
                    }.getType();

                    JsonObject baseResponse = getGson().fromJson(response.body().substring(1, response.body().length() - 1), JsonObject.class);
                    if (baseResponse.get("code").getAsInt() == 1) {
                        BaseResponse<ChaskifyTaskWayPoint> listBaseResponse = getGson().fromJson(baseResponse, type);
                        callback.onSuccess(listBaseResponse.getDetails());

                    } else {
                        callback.onChaskifyError(new Exception(baseResponse.get("msg").getAsString()));
                    }
                } else
                    try {
                        callback.onChaskifyError(new Exception(response.errorBody().string()));
                    } catch (IOException e) {
                        callback.onUnexpectedError(e);
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
