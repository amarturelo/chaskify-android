package com.chaskify.chaskify_sdk.rest.client;

import com.chaskify.chaskify_sdk.HomeServerConnectionConfig;
import com.chaskify.chaskify_sdk.RestClient;
import com.chaskify.chaskify_sdk.rest.api.TaskApi;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.exceptions.TokenNotFoundException;
import com.chaskify.chaskify_sdk.rest.model.Task;
import com.chaskify.chaskify_sdk.rest.model.login.Credentials;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alberto on 7/12/17.
 */

public class TaskRestClient extends RestClient<TaskApi> {
    public TaskRestClient(HomeServerConnectionConfig hsConfig) {
        super(hsConfig, TaskApi.class);
    }

    public void getTaskByDate(String date, int onduty, String timeZone, ApiCallback<List<Task>> callback) throws TokenNotFoundException {
        if (mCredentials != null)
            getTaskByDate(date, onduty, timeZone, "es", mCredentials.accessToken, callback);
        else
            throw new TokenNotFoundException();
    }

    private void getTaskByDate(String date, int onduty, String timeZone, String lang_id, String token, final ApiCallback<List<Task>> callback) {
        mApi.taskByDate(date, onduty, timeZone, lang_id, token).enqueue(new Callback<String>() {
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
