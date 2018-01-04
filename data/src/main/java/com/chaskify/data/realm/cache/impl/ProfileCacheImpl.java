package com.chaskify.data.realm.cache.impl;

import com.chaskify.data.model.chaskify.RealmProfile;
import com.chaskify.data.realm.cache.ProfileCache;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.disposables.Disposables;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by alberto on 3/01/18.
 */

public class ProfileCacheImpl implements ProfileCache {

    public ProfileCacheImpl() {
    }

    @Override
    public void put(RealmProfile realmProfile) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(realmProfile);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public Single<RealmProfile> getByDriverId(String driverId) {
        return Single.create(emitter -> {
            final Realm observableRealm = Realm.getDefaultInstance();
            final RealmResults<RealmProfile> results = observableRealm.where(RealmProfile.class)
                    .equalTo(RealmProfile.DRIVER_ID, driverId)
                    .findAll();

            if (results.size() != 0)
                emitter.onSuccess(results.first());

            emitter.setDisposable(Disposables.fromRunnable(observableRealm::close));

        });
    }
}
