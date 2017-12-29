package com.chaskify.data.model.chaskify.mapper;

import com.annimon.stream.Stream;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyProfile;
import com.chaskify.domain.model.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alberto on 29/12/17.
 */

public class ProfileDataMapper {
    public static List<Profile> transform(List<ChaskifyProfile> entities) {
        final List<Profile> list = new ArrayList<>();
        Stream.of(entities).withoutNulls()
                .forEach(realmEvent -> list.add(transform(realmEvent)));
        return list;
    }

    public static Profile transform(ChaskifyProfile chaskifyProfile) {
        Profile profile = new Profile();
        profile.setDriver_id(chaskifyProfile.getDriverId());
        profile.setUsername(chaskifyProfile.getUsername());
        profile.setColor(chaskifyProfile.getColor());
        profile.setDriver_picture(chaskifyProfile.getDriverPicture());
        profile.setEmail(chaskifyProfile.getEmail());
        profile.setLicence_plate(chaskifyProfile.getLicencePlate());
        profile.setPhone(chaskifyProfile.getPhone());
        profile.setTeam_name(chaskifyProfile.getTeamName());
        profile.setTransport_description(chaskifyProfile.getTransportDescription());
        profile.setTransport_type_id(chaskifyProfile.getTransportTypeId());
        profile.setTransport_type_id2(chaskifyProfile.getTransportTypeId2());
        return profile;
    }
}
