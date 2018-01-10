package com.chaskify.data.realm.mapper;

import com.annimon.stream.Stream;
import com.chaskify.data.realm.model.RealmSettings;
import com.chaskify.domain.model.Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alberto on 29/12/17.
 */

public class SettingsDataMapper {
    public static List<Settings> transform(List<RealmSettings> entities) {
        final List<Settings> list = new ArrayList<>();
        Stream.of(entities).withoutNulls()
                .forEach(realmEvent -> list.add(transform(realmEvent)));
        return list;
    }

    public static Settings transform(RealmSettings realmSettings) {
        Settings settings = new Settings();
        settings.setDriverId(realmSettings.getDriverId());
        settings.setEnabledPush(realmSettings.isEnabledPush());
        settings.setIcons(IconsDataMapper.transform(realmSettings.getIcons()));
        return settings;
    }
}
