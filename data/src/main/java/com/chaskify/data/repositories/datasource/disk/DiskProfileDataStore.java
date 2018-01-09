package com.chaskify.data.repositories.datasource.disk;

import android.os.Looper;

import com.annimon.stream.Optional;
import com.chaskify.data.realm.cache.ProfileCache;
import com.chaskify.data.repositories.datasource.ProfileDataStore;
import com.chaskify.domain.model.Profile;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by alberto on 3/01/18.
 */

public class DiskProfileDataStore implements ProfileDataStore {

    private ProfileCache profileCache;

    public DiskProfileDataStore(ProfileCache profileCache) {
        this.profileCache = profileCache;
    }

    @Override
    public Flowable<Optional<Profile>> getProfileByDriverId(String driverId) {
        return profileCache.getByDriverId(driverId)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(profile -> Optional.of(new Profile()
                        .setDriver_id(profile.getDriverId())
                        .setColor(profile.getColor())
                        .setDriver_picture(profile.getDriverPicture())
                        .setEmail(profile.getEmail())
                        .setLicence_plate(profile.getLicencePlate())
                        .setPhone(profile.getPhone())
                        .setTeam_name(profile.getTeamName())
                        .setTransport_description(profile.getTransportDescription())
                        .setTransport_type_id(profile.getTransportTypeId())
                        .setTransport_type_id2(profile.getTransportTypeId2())
                        .setUsername(profile.getUsername()
                        )));
    }
}
