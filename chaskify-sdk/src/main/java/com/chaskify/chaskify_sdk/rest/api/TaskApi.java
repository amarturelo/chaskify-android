package com.chaskify.chaskify_sdk.rest.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alberto on 7/12/17.
 */

public interface TaskApi {
    @GET("getTaskByDate")
    Call<String> taskByDate(
            @Query("date") String date
            , @Query("onduty") int onduty
            , @Query("timeZone") String timeZone
            , @Query("lang_id") String lang_id
            , @Query("token") String token);

    @GET("TaskDetails")
    Call<String> taskDetails(
            @Query("task_id") String task_id
            , @Query("onduty") int onduty
            , @Query("timeZone") String timeZone
            , @Query("lang_id") String lang_id
            , @Query("token") String token);

}
