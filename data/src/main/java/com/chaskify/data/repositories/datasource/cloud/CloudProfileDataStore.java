package com.chaskify.data.repositories.datasource.cloud;

import com.annimon.stream.Optional;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.client.ProfileRestClient;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyProfile;
import com.chaskify.data.realm.model.RealmProfile;
import com.chaskify.data.model.chaskify.mapper.ProfileDataMapper;
import com.chaskify.data.realm.cache.ProfileCache;
import com.chaskify.data.repositories.datasource.ProfileDataStore;
import com.chaskify.domain.model.Profile;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;

/**
 * Created by alberto on 3/01/18.
 */

public class CloudProfileDataStore implements ProfileDataStore {

    private ProfileRestClient profileRestClient;

    private ProfileCache profileCache;

    public CloudProfileDataStore(ProfileRestClient profileRestClient, ProfileCache profileCache) {
        this.profileRestClient = profileRestClient;
        this.profileCache = profileCache;
    }

    @Override
    public Flowable<Optional<Profile>> getProfileByDriverId(String driverId) {
        return Single
                .create((SingleOnSubscribe<ChaskifyProfile>) emitter -> profileRestClient.getProfile(new ApiCallback<ChaskifyProfile>() {
                    @Override
                    public void onSuccess(ChaskifyProfile chaskifyProfile) {
                        if (emitter != null && !emitter.isDisposed())
                            emitter.onSuccess(chaskifyProfile);
                    }

                    @Override
                    public void onNetworkError(Exception e) {
                        if (emitter != null && !emitter.isDisposed())
                            emitter.onError(e);
                    }

                    @Override
                    public void onChaskifyError(Exception e) {
                        if (emitter != null && !emitter.isDisposed())
                            emitter.onError(e);
                    }

                    @Override
                    public void onUnexpectedError(Exception e) {
                        if (emitter != null && !emitter.isDisposed())
                            emitter.onError(e);
                    }
                }))
                .map(ProfileDataMapper::transform)
                .doOnSuccess(profile -> profileCache.put(com.chaskify.data.realm.cache.impl.mapper.ProfileDataMapper.transform(profile)))
                .map(Optional::of)
                .toFlowable();
    }
}
