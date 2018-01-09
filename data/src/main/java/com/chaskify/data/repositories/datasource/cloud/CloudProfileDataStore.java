package com.chaskify.data.repositories.datasource.cloud;

import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.client.ProfileRestClient;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyProfile;
import com.chaskify.data.model.chaskify.RealmProfile;
import com.chaskify.data.model.chaskify.mapper.ProfileDataMapper;
import com.chaskify.data.realm.cache.ProfileCache;
import com.chaskify.data.repositories.datasource.ProfileDataStore;
import com.chaskify.domain.model.Profile;

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
    public Single<Profile> getProfileByDriverId(String driverId) {
        return Single
                .create((SingleOnSubscribe<ChaskifyProfile>) emitter -> profileRestClient.getProfile(new ApiCallback<ChaskifyProfile>() {
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
                }))
                .map(ProfileDataMapper::transform)
                .doOnSuccess(profile -> profileCache.put(new RealmProfile()
                        .setDriverId(profile.getDriver_id())
                        .setColor(profile.getColor())
                        .setDriverPicture(profile.getDriverPicture())
                        .setEmail(profile.getEmail())
                        .setLicencePlate(profile.getLicence_plate())
                        .setPhone(profile.getPhone())
                        .setTeamName(profile.getTeamName())
                        .setTransportDescription(profile.getTransport_description())
                        .setTransportTypeId(profile.getTransport_type_id())
                        .setTransportTypeId2(profile.getTransport_type_id2())
                        .setUsername(profile.getUsername())
                ));
    }
}
