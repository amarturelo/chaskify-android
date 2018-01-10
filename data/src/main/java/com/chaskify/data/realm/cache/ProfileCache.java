package com.chaskify.data.realm.cache;

import com.annimon.stream.Optional;
import com.chaskify.data.realm.model.RealmProfile;

import io.reactivex.Flowable;

/**
 * Created by alberto on 3/01/18.
 */

public interface ProfileCache {

    void put(RealmProfile realmProfile);

    Flowable<Optional<RealmProfile>> getByDriverId(String driver_id);
}
