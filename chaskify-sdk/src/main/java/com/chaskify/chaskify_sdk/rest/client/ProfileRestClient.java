package com.chaskify.chaskify_sdk.rest.client;

import com.chaskify.chaskify_sdk.ProfileConnectionConfig;
import com.chaskify.chaskify_sdk.RestClient;
import com.chaskify.chaskify_sdk.rest.api.ProfileApi;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.exceptions.TokenNotFoundException;
import com.chaskify.chaskify_sdk.rest.model.Profile;
import com.chaskify.chaskify_sdk.rest.model.login.Credentials;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alberto on 7/12/17.
 */

public class ProfileRestClient extends RestClient<ProfileApi> {
    public ProfileRestClient(Credentials credentials) {
        super(credentials, ProfileApi.class);
    }

    public void getProfile(ApiCallback<Profile> callback) throws TokenNotFoundException {
        if (mCredentials != null)
            getProfile("es", mCredentials.accessToken, callback);
        else
            throw new TokenNotFoundException();
    }

    private void getProfile(String lang_id, String accessToken, final ApiCallback<Profile> callback) {
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
