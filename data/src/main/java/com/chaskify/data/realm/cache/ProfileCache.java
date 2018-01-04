package com.chaskify.data.realm.cache;

import com.chaskify.data.model.chaskify.RealmProfile;

import io.reactivex.Single;

/**
 * Created by alberto on 3/01/18.
 */

public interface ProfileCache {

    void put(RealmProfile realmProfile);

    Single<RealmProfile> getByDriverId(String driver_id);
}
