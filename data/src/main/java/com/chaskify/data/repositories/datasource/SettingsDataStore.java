package com.chaskify.data.repositories.datasource;

import com.annimon.stream.Optional;
import com.chaskify.domain.model.Settings;

import io.reactivex.Flowable;

/**
 * Created by alberto on 9/01/18.
 */

public interface SettingsDataStore {
    Flowable<Optional<Settings>> getByDriverId(String driverId);
}
