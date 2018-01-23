package com.chaskify.chaskify_sdk.rest.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @GET("updateProfile")
    Call<String> updateProfile(
            @Query("token") String token
            , @Query("phone") String phone
    );

    @GET("updateVehicle")
    Call<String> updateVehicle(
            @Query("token") String token
            , @Query("transport_type_id") String transportTypeId
            , @Query("transport_description") String transportDescription
            , @Query("licence_plate") String licencePlate
            , @Query("color") String color
    );

    @GET("ChangeDutyStatus")
    Call<String> changeDutyStatus(
            @Query("token") String token
            , @Query("onduty") String value
    );

    @FormUrlEncoded
    @POST("AddPhotoToPerfil")
    Call<String> updateImageProfile(
            @Query("token") String accessToken
            , @Field("image") String image);
}
