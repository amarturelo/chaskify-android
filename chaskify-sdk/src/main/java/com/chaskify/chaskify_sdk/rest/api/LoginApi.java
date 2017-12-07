package com.chaskify.chaskify_sdk.rest.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * The login REST API.
 */
public interface LoginApi {
    @GET("login")
    Call<String> login(@Query("username") String username, @Query("password") String password, @Query("lang_id") String lang_id);

    /**
     * Try to create an account
     *
     * @param params   the registration params
     * @param callback the asynchronous callback called with the response
     */
    /*@POST("/register")
    void register(@Body RegistrationParams params, Callback<JsonObject> callback);*/

    /**
     * Pass params to the server for the current login phase.
     *
     * @param loginParams the login parameters
     * @param callback    the asynchronous callback called with the response
     */
    /*@POST("/login")
    void login(@Body LoginParams loginParams, Callback<JsonObject> callback);*/

    /**
     * Invalidate the access token, so that it can no longer be used for authorization.
     *
     * @param callback the asynchronous callback called with the response
     */
    /*@POST("/logout")
    void logout(Callback<JsonObject> callback);*/
}