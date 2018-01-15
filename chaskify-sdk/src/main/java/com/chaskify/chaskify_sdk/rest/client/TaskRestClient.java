package com.chaskify.chaskify_sdk.rest.client;

import com.chaskify.chaskify_sdk.RestClient;
import com.chaskify.chaskify_sdk.mapper.TaskDeserializer;
import com.chaskify.chaskify_sdk.rest.api.TaskApi;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.exceptions.TokenNotFoundException;
import com.chaskify.chaskify_sdk.rest.model.BaseResponse;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTask;
import com.chaskify.chaskify_sdk.rest.model.login.ChaskifyCredentials;
import com.chaskify.chaskify_sdk.rest.model.login.LoginResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alberto on 7/12/17.
 */

public class TaskRestClient extends RestClient<TaskApi> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", /*Locale.getDefault()*/Locale.getDefault());


    public TaskRestClient(ChaskifyCredentials chaskifyCredentials) {
        super(chaskifyCredentials, TaskApi.class);
    }

    public void taskByDate(Date date, ApiCallback<List<ChaskifyTask>> callback) {
        if (mChaskifyCredentials != null)
            taskByDate(date, mChaskifyCredentials.getAccessToken(), callback);
        else
            callback.onChaskifyError(new TokenNotFoundException());
    }

    private void taskByDate(Date date, String token, final ApiCallback<List<ChaskifyTask>> callback) {
        mApi.taskByDate(dateFormat.format(date), String.valueOf(new Date().getTimezoneOffset()), token)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Type type = new TypeToken<BaseResponse<List<ChaskifyTask>>>() {
                        }.getType();

                        JsonObject baseResponse = getGson().fromJson(response.body().substring(1, response.body().length() - 1), JsonObject.class);
                        if (baseResponse.get("code").getAsInt() == 1) {
                            BaseResponse<List<ChaskifyTask>> listBaseResponse = getGson().fromJson(baseResponse, type);
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

    public void taskDetails(String task_id, ApiCallback<ChaskifyTask> callback) {
        if (mChaskifyCredentials != null)
            taskDetails(task_id, mChaskifyCredentials.getAccessToken(), callback);
        else
            callback.onChaskifyError(new TokenNotFoundException());
    }

    private void taskDetails(String task_id, String accessToken, final ApiCallback<ChaskifyTask> callback) {
        mApi.taskDetails(task_id, String.valueOf(new Date().getTimezoneOffset()), accessToken)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Type type = new TypeToken<BaseResponse<ChaskifyTask>>() {
                        }.getType();

                        JsonObject baseResponse = getGson().fromJson(response.body().substring(1, response.body().length() - 1), JsonObject.class);
                        if (baseResponse.get("code").getAsInt() == 1) {
                            BaseResponse<ChaskifyTask> listBaseResponse = getGson().fromJson(baseResponse, type);
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

    public void changeTaskStatus(String taskId, String status, String lat, String lng, ApiCallback<ChaskifyTask> callback) {
        if (mChaskifyCredentials != null)
            changeTaskStatus(taskId, status, lat, lng, mChaskifyCredentials.getAccessToken(), callback);
        else
            callback.onChaskifyError(new TokenNotFoundException());
    }

    private void changeTaskStatus(String taskId, String status, String lat, String lng, String accessToken, final ApiCallback<ChaskifyTask> callback) {
        mApi.updateStatus(accessToken
                , taskId
                , String.valueOf(new Date().getTimezoneOffset())
                , status
                , lat
                , lng
        ).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Type type = new TypeToken<BaseResponse<ChaskifyTask>>() {
                    }.getType();

                    JsonObject baseResponse = getGson().fromJson(response.body().substring(1, response.body().length() - 1), JsonObject.class);
                    if (baseResponse.get("code").getAsInt() == 1) {
                        BaseResponse<ChaskifyTask> listBaseResponse = getGson().fromJson(baseResponse, type);
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
                .registerTypeAdapter(ChaskifyTask.class, new TaskDeserializer())
                .create();
    }
}
