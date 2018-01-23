package com.chaskify.chaskify_sdk.rest.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alberto on 28/12/17.
 */

public interface TaskWayPointApi {
    @GET("viewWaypointDetailsAndroid")
    Call<String> wayPointDetails(
            @Query("waypoint_id") String task_id
            , @Query("token") String token);

    @GET("changeWaypointStatusNew")
    Call<String> updateStatus(
            @Query("token") String token
            , @Query("waypoint_id") String waypoint_id
            , @Query("timeZone") String timeZone
            , @Query("status_raw") String status_raw
            , @Query("lat") String lat
            , @Query("lng") String lng
    );
}
