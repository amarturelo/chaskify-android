package com.chaskify.data.repositories;

import com.annimon.stream.Optional;
import com.chaskify.chaskify_sdk.rest.client.ProfileRestClient;
import com.chaskify.data.realm.cache.ProfileCache;
import com.chaskify.data.repositories.datasource.cloud.CloudProfileDataStore;
import com.chaskify.data.repositories.datasource.disk.DiskProfileDataStore;
import com.chaskify.domain.model.Profile;
import com.chaskify.domain.repositories.ProfileRepository;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by alberto on 29/12/17.
 */

public class ProfileRepositoryImpl implements ProfileRepository {

    private DiskProfileDataStore diskProfileDataStore;

    private CloudProfileDataStore cloudProfileDataStore;

    public ProfileRepositoryImpl(ProfileCache profileCache, ProfileRestClient profileRestClient) {
        this.diskProfileDataStore = new DiskProfileDataStore(profileCache);
        this.cloudProfileDataStore = new CloudProfileDataStore(profileRestClient, profileCache);
    }

    @Override
    public Flowable<Optional<Profile>> profileByDriverId(String driver_id) {
        return Flowable.concatArrayDelayError(
                diskProfileDataStore.getProfileByDriverId(driver_id)
                , cloudProfileDataStore
                        .getProfileByDriverId(driver_id));
    }

}
