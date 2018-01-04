package com.chaskify.data.repositories.datasource.disk;

import com.chaskify.data.realm.cache.ProfileCache;
import com.chaskify.data.repositories.datasource.ProfileDataStore;
import com.chaskify.domain.model.Profile;

import io.reactivex.Single;

/**
 * Created by alberto on 3/01/18.
 */

public class DiskProfileDataStore implements ProfileDataStore {

    private ProfileCache profileCache;

    public DiskProfileDataStore(ProfileCache profileCache) {
        this.profileCache = profileCache;
    }

    @Override
    public Single<Profile> getProfileByDriverId(String driverId) {
        return profileCache.getByDriverId(driverId)
                .map(profile -> new Profile()
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
                        ));
    }
}
