package com.chaskify.chaskify_sdk.rest.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alberto on 23/01/18.
 */

public interface DriverApi {
    @GET("ChangeDutyStatus")
    Call<String> changeDutyStatus(
            @Query("token") String token
            , @Query("onduty") String value
    );

    @GET("updateDriverPosition")
    Call<String> updateDriverPosition(
            @Query("token") String token
            , @Query("lat") String lat
            , @Query("lng") String lng
    );
}
