package com.chaskify.data.realm.cache.impl;

import android.os.Looper;
import android.util.Pair;

import com.annimon.stream.Optional;
import com.chaskify.data.realm.model.RealmProfile;
import com.chaskify.data.realm.cache.ProfileCache;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by alberto on 3/01/18.
 */

public class ProfileCacheImpl extends RealmCache implements ProfileCache {

    public ProfileCacheImpl() {
        Timber.tag(this.getClass().getSimpleName());
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
    public Flowable<Optional<RealmProfile>> getByDriverId(String driverId) {
        return Flowable.defer(() -> Flowable.using(() -> new Pair<>(Realm.getDefaultInstance(), Looper.myLooper()), pair -> {
                    RealmProfile realmRoom = pair.first.where(RealmProfile.class)
                            .equalTo(RealmProfile.DRIVER_ID, driverId)
                            .findFirst();

                    if (realmRoom == null) {
                        return Flowable.just(Optional.empty());
                    }

                    return realmRoom.<RealmProfile>asFlowable()
                            .filter(
                                    profile -> profile.isLoaded()
                                            && profile.isValid())
                            .map(Optional::of);
                }
                , pair -> close(pair.first, pair.second))
                        .unsubscribeOn(AndroidSchedulers.from(Looper.myLooper())))
                .map(optional -> {
                    if (optional.isPresent()) {
                        return Optional.of((RealmProfile) optional.get());
                    }
                    return Optional.empty();
                });
    }
}
