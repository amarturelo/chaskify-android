package com.chaskify.chaskify_sdk.rest.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by alberto on 7/12/17.
 */

public interface ProfileApi {
    @GET("GetProfile")
    Call<String> profile(
            @Query("lang_id") String lang_id
            , @Query("token") String token);

    @GET("ProfileChangePassword")
    Call<String> updatePassword(
            @Query("token") String token
            , @Query("current_pass") String current_pass
            , @Query("new_pass") String new_pass
            , @Query("confirm_pass") String confirm_pass
    );
}
