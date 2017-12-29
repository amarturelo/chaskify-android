package com.chaskify.data.repositories;

import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.client.ProfileRestClient;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyProfile;
import com.chaskify.data.model.chaskify.mapper.ProfileDataMapper;
import com.chaskify.domain.model.Profile;
import com.chaskify.domain.repositories.ProfileRepository;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Function;

/**
 * Created by alberto on 29/12/17.
 */

public class ProfileRepositoryImpl implements ProfileRepository {

    private ProfileRestClient profileRestClient;

    public ProfileRepositoryImpl(ProfileRestClient profileRestClient) {
        this.profileRestClient = profileRestClient;
    }

    @Override
    public Single<Profile> profile() {
        return Single.create((SingleOnSubscribe<ChaskifyProfile>) emitter -> profileRestClient.getProfile(new ApiCallback<ChaskifyProfile>() {
            @Override
            public void onSuccess(ChaskifyProfile chaskifyProfile) {
                emitter.onSuccess(chaskifyProfile);
            }

            @Override
            public void onNetworkError(Exception e) {
                emitter.onError(e);
            }

            @Override
            public void onChaskifyError(Exception e) {
                emitter.onError(e);
            }

            @Override
            public void onUnexpectedError(Exception e) {
                emitter.onError(e);
            }
        })).map(ProfileDataMapper::transform);
    }
}
