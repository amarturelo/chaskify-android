package com.chaskify.chaskify_sdk.rest.client;

import com.chaskify.chaskify_sdk.RestClient;
import com.chaskify.chaskify_sdk.rest.api.ProfileApi;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.exceptions.TokenNotFoundException;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyProfile;
import com.chaskify.chaskify_sdk.rest.model.login.ChaskifyCredentials;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alberto on 7/12/17.
 */

public class ProfileRestClient extends RestClient<ProfileApi> {
    public ProfileRestClient(ChaskifyCredentials chaskifyCredentials) {
        super(chaskifyCredentials, ProfileApi.class);
    }

    public void getProfile(ApiCallback<ChaskifyProfile> callback) throws TokenNotFoundException {
        if (mChaskifyCredentials != null)
            getProfile("es", mChaskifyCredentials.accessToken, callback);
        else
            throw new TokenNotFoundException();
    }

    private void getProfile(String lang_id, String accessToken, final ApiCallback<ChaskifyProfile> callback) {
        mApi.profile(lang_id, accessToken)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        callback.onNetworkError((Exception) t);
                    }
                });
    }

}
