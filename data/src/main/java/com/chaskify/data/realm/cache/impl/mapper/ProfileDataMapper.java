package com.chaskify.data.realm.cache.impl.mapper;

import com.annimon.stream.Stream;
import com.chaskify.data.realm.model.RealmProfile;
import com.chaskify.data.realm.model.RealmSettings;
import com.chaskify.domain.model.Profile;
import com.chaskify.domain.model.Settings;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by alberto on 29/12/17.
 */

public class ProfileDataMapper {
    public static List<RealmProfile> transform(List<Profile> entities) {
        final RealmList<RealmProfile> list = new RealmList<>();
        Stream.of(entities).withoutNulls()
                .forEach(realmEvent -> list.add(transform(realmEvent)));
        return list;
    }

    public static RealmProfile transform(Profile profile) {
        RealmProfile realmProfile = new RealmProfile()
                .setDriverId(profile.getDriverId())
                .setColor(profile.getColor())
                .setDriverPicture(profile.getDriverPicture())
                .setEmail(profile.getEmail())
                .setLicencePlate(profile.getLicence_plate())
                .setPhone(profile.getPhone())
                .setTeamName(profile.getTeamName())
                .setTransportDescription(profile.getTransportDescription())
                .setTransportTypeId(profile.getTransportTypeId())
                .setTransportTypeId2(profile.getTransportTypeId2())
                .setUsername(profile.getUsername());
        return realmProfile;
    }
}
