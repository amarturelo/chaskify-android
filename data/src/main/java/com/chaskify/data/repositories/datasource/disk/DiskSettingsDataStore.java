package com.chaskify.data.repositories.datasource.disk;

import android.graphics.Path;

import com.annimon.stream.Optional;
import com.chaskify.data.realm.cache.SettingsCache;
import com.chaskify.data.realm.mapper.SettingsDataMapper;
import com.chaskify.data.realm.model.RealmSettings;
import com.chaskify.data.repositories.datasource.SettingsDataStore;
import com.chaskify.domain.model.Settings;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

/**
 * Created by alberto on 9/01/18.
 */

public class DiskSettingsDataStore implements SettingsDataStore {

    private SettingsCache settingsCache;

    public DiskSettingsDataStore(SettingsCache settingsCache) {
        this.settingsCache = settingsCache;
    }

    @Override
    public Flowable<Optional<Settings>> getByDriverId(String driverId) {
        return settingsCache.getByDriverId(driverId)
                .map(value -> {
                    if (value.isPresent())
                        return Optional.of(SettingsDataMapper.transform(value.get()));
                    else
                        return Optional.empty();
                })
                ;
    }
}
