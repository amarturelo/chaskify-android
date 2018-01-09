package com.chaskify.android.ui.model.mapper;

import com.annimon.stream.Stream;
import com.chaskify.android.ui.model.ProfileModel;
import com.chaskify.domain.model.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alberto on 29/12/17.
 */

public class ProfileModelDataMapper {
    public static List<ProfileModel> transform(List<Profile> entities) {
        final List<ProfileModel> list = new ArrayList<>();
        Stream.of(entities).withoutNulls()
                .forEach(realmEvent -> list.add(transform(realmEvent)));
        return list;
    }

    public static ProfileModel transform(Profile profile) {
        ProfileModel taskModel = new ProfileModel();
        taskModel.setDriverId(profile.getDriver_id())
                .setUsername(profile.getUsername())
                .setColor(profile.getColor())
                .setDriverPicture(profile.getDriverPicture())
                .setEmail(profile.getEmail())
                .setLicencePlate(profile.getLicence_plate())
                .setPhone(profile.getPhone())
                .setTeamName(profile.getTeamName())
                .setTransportDescription(profile.getTransport_description())
                .setTransportTypeId(profile.getTransport_type_id())
                .setTransportTypeId2(profile.getTransport_type_id2());
        return taskModel;
    }
}
