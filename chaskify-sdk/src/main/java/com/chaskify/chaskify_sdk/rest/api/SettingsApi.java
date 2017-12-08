package com.chaskify.chaskify_sdk.rest.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alberto on 7/12/17.
 */

public interface SettingsApi {
    @GET("GetSettings")
    Call<String> settings(
            @Query("lang_id") String lang_id
            , @Query("token") String token);
}
