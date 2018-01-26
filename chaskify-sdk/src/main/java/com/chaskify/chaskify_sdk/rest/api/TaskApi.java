package com.chaskify.chaskify_sdk.rest.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alberto on 7/12/17.
 */

public interface TaskApi {
    @GET("getTaskByDateAndroid")
    Call<String> taskByDate(
            @Query("date") String date
            , @Query("timeZone") String timeZone
            , @Query("token") String token);

    @GET("TaskDetailsAndroid")
    Call<String> taskDetails(
            @Query("task_id") String task_id
            , @Query("timeZone") String timeZone
            , @Query("token") String token);

    @GET("changeTaskStatusNewAndroid")
    Call<String> updateStatus(
            @Query("token") String token
            , @Query("task_id") String task_id
            , @Query("timeZone") String timeZone
            , @Query("status_raw") String status_raw
            , @Query("reason") String reason
            , @Query("lat") String lat
            , @Query("lng") String lng
    );

}
