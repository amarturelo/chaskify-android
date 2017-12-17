package com.chaskify.chaskify_sdk.rest.client;

import com.chaskify.chaskify_sdk.RestClient;
import com.chaskify.chaskify_sdk.rest.api.CalendarTaskApi;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.exceptions.TokenNotFoundException;
import com.chaskify.chaskify_sdk.rest.model.BaseResponse;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyCalendarTask;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTask;
import com.chaskify.chaskify_sdk.rest.model.login.ChaskifyCredentials;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alberto on 15/12/17.
 */

public class CalendarTaskRestClient extends RestClient<CalendarTaskApi> {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", /*Locale.getDefault()*/Locale.getDefault());

    public CalendarTaskRestClient(ChaskifyCredentials chaskifyCredentials) {
        super(chaskifyCredentials, CalendarTaskApi.class);
    }

    public void calendarRaskByRange(Date start, Date end, ApiCallback<List<ChaskifyCalendarTask>> callback) throws TokenNotFoundException {
        if (mChaskifyCredentials != null)
            calendarRaskByRange(start, end, mChaskifyCredentials.getAccessToken(), callback);
        else
            throw new TokenNotFoundException();
    }

    private void calendarRaskByRange(Date start, Date end, String accessToken, final ApiCallback<List<ChaskifyCalendarTask>> callback) {
        mApi.calendarTaskByRange(dateFormat.format(start), dateFormat.format(end), String.valueOf(new Date().getTimezoneOffset()), accessToken)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Type type = new TypeToken<BaseResponse<List<ChaskifyCalendarTask>>>() {
                        }.getType();

                        JsonObject baseResponse = getGson().fromJson(response.body().substring(1, response.body().length() - 1), JsonObject.class);
                        if (baseResponse.get("code").getAsInt() == 1) {
                            BaseResponse<List<ChaskifyCalendarTask>> listBaseResponse = getGson().fromJson(baseResponse, type);
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
}
