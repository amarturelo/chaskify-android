package com.chaskify.chaskify_sdk.rest.client;

import com.chaskify.chaskify_sdk.RestClient;
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

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alberto on 7/12/17.
 */

public class TaskRestClient extends RestClient<TaskApi> {
    public TaskRestClient(ChaskifyCredentials chaskifyCredentials) {
        super(chaskifyCredentials, TaskApi.class);
    }

    public void taskByDate(String date, String timeZone, ApiCallback<List<ChaskifyTask>> callback) throws TokenNotFoundException {
        if (mChaskifyCredentials != null)
            taskByDate(date, timeZone, mChaskifyCredentials.accessToken, callback);
        else
            throw new TokenNotFoundException();
    }

    private void taskByDate(String date, String timeZone, String token, final ApiCallback<List<ChaskifyTask>> callback) {
        mApi.taskByDate(date, timeZone, token)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Type listTask = new TypeToken<List<ChaskifyTask>>() {
                        }.getType();

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

    public void taskDetails(String task_id, ApiCallback<ChaskifyTask> callback) throws TokenNotFoundException {
        if (mChaskifyCredentials != null)
            taskDetails(task_id, "300", "en", mChaskifyCredentials.accessToken, callback);
        else
            throw new TokenNotFoundException();
    }

    private void taskDetails(String task_id, String timeZone, String lang_id, String accessToken, final ApiCallback<ChaskifyTask> callback) {
        mApi.taskDetails(task_id, timeZone, accessToken)
                .enqueue(new Callback<String>() {
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
        return new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
    }
}
