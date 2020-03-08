package com.chaskify.data.model.chaskify.mapper;

import com.annimon.stream.Stream;
import com.chaskify.chaskify_sdk.rest.model.ChaskifySettings;
import com.chaskify.domain.model.Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alberto on 29/12/17.
 */

public class SettingsDataMapper {
    public static List<Settings> transform(List<ChaskifySettings> entities) {
        final List<Settings> list = new ArrayList<>();
        Stream.of(entities).withoutNulls()
                .forEach(realmEvent -> list.add(transform(realmEvent)));
        return list;
    }

    public static Settings transform(ChaskifySettings chaskifySettings) {
        Settings settings = new Settings();
        settings.setDriverId(chaskifySettings.getDriverId());
        settings.setEnabledPush(chaskifySettings.getEnabledPush().equals("1"));
        settings.setIcons(IconsDataMapper.transform(chaskifySettings.getIcons()));
        return settings;
    }
}
