package com.chaskify.android.ui.model.mapper;

import com.annimon.stream.Stream;
import com.chaskify.android.ui.model.ProfileModel;
import com.chaskify.android.ui.model.SettingsModel;
import com.chaskify.domain.model.Profile;
import com.chaskify.domain.model.Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alberto on 29/12/17.
 */

public class SettingsModelDataMapper {
    public static List<SettingsModel> transform(List<Settings> entities) {
        final List<SettingsModel> list = new ArrayList<>();
        Stream.of(entities).withoutNulls()
                .forEach(realmEvent -> list.add(transform(realmEvent)));
        return list;
    }

    public static SettingsModel transform(Settings settings) {
        SettingsModel settingsModel = new SettingsModel();
        settingsModel.setEnabledPush(settings.isEnabledPush());
        return settingsModel;
    }
}
