package com.chaskify.chaskify_sdk.rest.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alberto on 15/12/17.
 */

public interface CalendarTaskApi {
    @GET("CalendarTaskAndroid")
    Call<String> calendarTaskByRange(
            @Query("start") String start
            , @Query("end") String end
            , @Query("timeZone") String timeZone
            , @Query("token") String token);
}
