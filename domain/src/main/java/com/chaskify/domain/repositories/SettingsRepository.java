package com.chaskify.domain.repositories;

import com.annimon.stream.Optional;
import com.chaskify.domain.model.Profile;
import com.chaskify.domain.model.Settings;

import io.reactivex.Flowable;

/**
 * Created by alberto on 9/01/18.
 */

public interface SettingsRepository {
    Flowable<Optional<Settings>> settingsByDriverId(String driverId);
}
