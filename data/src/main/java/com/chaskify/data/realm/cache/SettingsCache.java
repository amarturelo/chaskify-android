package com.chaskify.data.realm.cache;

import com.annimon.stream.Optional;
import com.chaskify.data.realm.model.RealmSettings;
import com.chaskify.domain.model.Settings;

import io.reactivex.Flowable;

/**
 * Created by alberto on 9/01/18.
 */

public interface SettingsCache {
    Flowable<Optional<RealmSettings>> getByDriverId(String driver_id);

    void put(RealmSettings settings);
}
