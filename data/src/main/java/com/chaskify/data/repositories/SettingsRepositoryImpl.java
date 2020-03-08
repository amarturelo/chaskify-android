package com.chaskify.data.repositories;

import com.annimon.stream.Optional;
import com.chaskify.chaskify_sdk.rest.client.SettingsRestClient;
import com.chaskify.data.realm.cache.SettingsCache;
import com.chaskify.data.repositories.datasource.cloud.CloudSettingsDataStore;
import com.chaskify.data.repositories.datasource.disk.DiskSettingsDataStore;
import com.chaskify.domain.model.Settings;
import com.chaskify.domain.repositories.SettingsRepository;

import io.reactivex.Flowable;
import timber.log.Timber;

/**
 * Created by alberto on 9/01/18.
 */

public class SettingsRepositoryImpl implements SettingsRepository {

    private DiskSettingsDataStore diskSettingsDataStore;

    public SettingsRepositoryImpl(SettingsCache settingsCache) {
        Timber.tag(this.getClass().getSimpleName());
        this.diskSettingsDataStore = new DiskSettingsDataStore(settingsCache);
    }

    @Override
    public Flowable<Optional<Settings>> settingsByDriverId(String driverId) {
        return
                diskSettingsDataStore.getByDriverId(driverId)
                        .doOnNext(settingsOptional -> Timber.d("::diskSettingsDataStore " + settingsOptional.toString()));

    }
}
