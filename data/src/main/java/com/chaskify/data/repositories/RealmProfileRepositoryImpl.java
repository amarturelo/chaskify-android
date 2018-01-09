package com.chaskify.data.repositories;

import com.chaskify.data.repositories.datasource.disk.DiskProfileDataStore;
import com.chaskify.domain.model.Profile;
import com.chaskify.domain.repositories.ProfileRepository;

import io.reactivex.Observable;

/**
 * Created by alberto on 8/01/18.
 */

public class RealmProfileRepositoryImpl implements ProfileRepository {

    private DiskProfileDataStore diskProfileDataStore;

    public RealmProfileRepositoryImpl(DiskProfileDataStore diskProfileDataStore) {
        this.diskProfileDataStore = diskProfileDataStore;
    }

    @Override
    public Observable<Profile> profileByDriverId(String driver_id) {
        return diskProfileDataStore.getProfileByDriverId(driver_id)
                .toObservable();
    }
}
