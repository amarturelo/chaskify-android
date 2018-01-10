package com.chaskify.data.realm.cache.impl.mapper;

import com.annimon.stream.Stream;
import com.chaskify.data.realm.model.RealmSettings;
import com.chaskify.domain.model.Settings;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by alberto on 29/12/17.
 */

public class SettingsDataMapper {
    public static List<RealmSettings> transform(List<Settings> entities) {
        final RealmList<RealmSettings> list = new RealmList<>();
        Stream.of(entities).withoutNulls()
                .forEach(realmEvent -> list.add(transform(realmEvent)));
        return list;
    }

    public static RealmSettings transform(Settings settings) {
        RealmSettings realmSettings = new RealmSettings();
        realmSettings.setDriverId(realmSettings.getDriverId());
        realmSettings.setEnabledPush(realmSettings.getEnabledPush());
        realmSettings.setIcons(IconsDataMapper.transform(settings.getIcons()));
        return realmSettings;
    }
}
