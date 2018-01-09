package com.chaskify.data.repositories;

import com.annimon.stream.Optional;
import com.chaskify.data.repositories.datasource.disk.DiskProfileDataStore;
import com.chaskify.domain.model.Profile;
import com.chaskify.domain.repositories.ProfileRepository;

import io.reactivex.Flowable;

/**
 * Created by alberto on 8/01/18.
 */

public class RealmProfileRepositoryImpl implements ProfileRepository {

    private DiskProfileDataStore diskProfileDataStore;

    public RealmProfileRepositoryImpl(DiskProfileDataStore diskProfileDataStore) {
        this.diskProfileDataStore = diskProfileDataStore;
    }

    @Override
    public Flowable<Optional<Profile>> profileByDriverId(String driver_id) {
        return diskProfileDataStore.getProfileByDriverId(driver_id)
                ;
    }
}
