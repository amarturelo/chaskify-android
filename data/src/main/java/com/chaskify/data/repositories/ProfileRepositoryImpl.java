package com.chaskify.data.repositories;

import com.chaskify.data.repositories.datasource.cloud.CloudProfileDataStore;
import com.chaskify.data.repositories.datasource.disk.DiskProfileDataStore;
import com.chaskify.domain.model.Profile;
import com.chaskify.domain.repositories.ProfileRepository;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by alberto on 29/12/17.
 */

public class ProfileRepositoryImpl implements ProfileRepository {

    private DiskProfileDataStore diskProfileDataStore;

    private CloudProfileDataStore cloudProfileDataStore;

    public ProfileRepositoryImpl(DiskProfileDataStore diskProfileDataStore, CloudProfileDataStore cloudProfileDataStore) {
        this.diskProfileDataStore = diskProfileDataStore;
        this.cloudProfileDataStore = cloudProfileDataStore;
    }

    @Override
    public Observable<Profile> profileByDriverId(String driver_id) {
        return Single.merge(
                diskProfileDataStore.getProfileByDriverId(driver_id)
                , cloudProfileDataStore
                        .getProfileByDriverId(driver_id))
                .toObservable();
    }

}
