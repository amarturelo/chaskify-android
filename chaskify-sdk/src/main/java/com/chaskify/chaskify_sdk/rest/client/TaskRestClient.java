package com.chaskify.chaskify_sdk.rest.client;

import com.chaskify.chaskify_sdk.RestClient;
import com.chaskify.chaskify_sdk.rest.api.TaskApi;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.exceptions.TokenNotFoundException;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTask;
import com.chaskify.chaskify_sdk.rest.model.login.ChaskifyCredentials;

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

    public void taskByDate(String date,  String timeZone, ApiCallback<List<ChaskifyTask>> callback) throws TokenNotFoundException {
        if (mChaskifyCredentials != null)
            taskByDate(date,  timeZone, mChaskifyCredentials.accessToken, callback);
        else
            throw new TokenNotFoundException();
    }

    private void taskByDate(String date,  String timeZone,  String token, final ApiCallback<List<ChaskifyTask>> callback) {
        mApi.taskByDate(date, timeZone, token)
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

}
