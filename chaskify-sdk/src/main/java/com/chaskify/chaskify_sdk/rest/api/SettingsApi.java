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

    @GET("SettingPush")
    Call<String> settingsUpdatePush(
            @Query("lang_id") String lang_id
            , @Query("enabled_push") String enable
            , @Query("token") String token);

    @GET("changeRingtone")
    Call<String> settingsUpdateRingTone(
            @Query("lang_id") String lang_id
            , @Query("sound") String sound
            , @Query("token") String token);
}
