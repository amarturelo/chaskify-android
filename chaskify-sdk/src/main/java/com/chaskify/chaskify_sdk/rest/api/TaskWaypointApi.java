package com.chaskify.chaskify_sdk.rest.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alberto on 28/12/17.
 */

public interface TaskWaypointApi {
    @GET("viewWaypointDetailsAndroid")
    Call<String> wayPointDetails(
            @Query("waypoint_id") String task_id
            , @Query("token") String token);
}
