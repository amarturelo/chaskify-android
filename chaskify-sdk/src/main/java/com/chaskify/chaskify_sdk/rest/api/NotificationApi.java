package com.chaskify.chaskify_sdk.rest.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alberto on 7/12/17.
 */

public interface NotificationApi {
    @GET("GetNotificationsAndroid")
    Call<String> notifications(
            @Query("timeZone") String timeZone
            , @Query("lang_id") String lang_id
            , @Query("token") String token);
}
