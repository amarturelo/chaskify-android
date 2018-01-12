package com.chaskify.data.repositories.datasource.disk;

import com.annimon.stream.Optional;
import com.chaskify.data.realm.cache.ProfileCache;
import com.chaskify.data.repositories.datasource.ProfileDataStore;
import com.chaskify.domain.model.Profile;

import io.reactivex.Flowable;

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
        return profileCache.getByDriverIdAsObservable(driverId)
                .map(value -> {
                    if (value.isPresent())
                        return Optional.of(new Profile()
                                .setDriver_id(value.get().getDriverId())
                                .setColor(value.get().getColor())
                                .setDriver_picture(value.get().getDriverPicture())
                                .setEmail(value.get().getEmail())
                                .setLicence_plate(value.get().getLicencePlate())
                                .setPhone(value.get().getPhone())
                                .setTeam_name(value.get().getTeamName())
                                .setTransport_description(value.get().getTransportDescription())
                                .setTransport_type_id(value.get().getTransportTypeId())
                                .setTransport_type_id2(value.get().getTransportTypeId2())
                                .setUsername(value.get().getUsername()
                                ));
                    else
                        return Optional.empty();
                })
                ;
    }
}
