package com.chaskify.data.repositories.datasource.disk;

import com.annimon.stream.Optional;
import com.chaskify.data.realm.cache.SettingsCache;
import com.chaskify.data.realm.mapper.SettingsDataMapper;
import com.chaskify.data.repositories.datasource.SettingsDataStore;
import com.chaskify.domain.model.Settings;

import io.reactivex.Flowable;

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
        return settingsCache.getByDriverIdAsObservable(driverId)
                .map(value -> {
                    if (value.isPresent())
                        return Optional.of(SettingsDataMapper.transform(value.get()));
                    else
                        return Optional.empty();
                })
                ;
    }
}
