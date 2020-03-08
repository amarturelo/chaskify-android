package com.chaskify.chaskify_sdk.rest.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * The login REST API.
 */
public interface LoginApi {
    @GET("login")
    Call<String> login(@Query("username") String username, @Query("password") String password);

    @GET("Logout")
    Call<String> logout(@Query("token") String token);
}