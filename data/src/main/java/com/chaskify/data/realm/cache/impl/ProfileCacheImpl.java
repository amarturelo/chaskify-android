package com.chaskify.data.realm.cache.impl;

import android.graphics.Path;
import android.os.Looper;
import android.util.Pair;

import com.annimon.stream.Optional;
import com.chaskify.data.realm.model.RealmProfile;
import com.chaskify.data.realm.cache.ProfileCache;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
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
    public Optional<RealmProfile> getByDriverId(String driverId) {
        Realm realm = Realm.getDefaultInstance();
        RealmProfile result = realm.where(RealmProfile.class)
                .equalTo(RealmProfile.DRIVER_ID, driverId)
                .findFirst();
        return result == null ? Optional.empty() : Optional.of(realm.copyFromRealm(result));
    }

    @Override
    public Flowable<Optional<RealmProfile>> getByDriverIdAsObservable(String driverId) {
        return Flowable.defer(() -> Flowable.using(() -> new Pair<>(Realm.getDefaultInstance(), Looper.myLooper()), pair -> {
                    RealmResults<RealmProfile> realmProfiles = pair.first.where(RealmProfile.class)
                            .equalTo(RealmProfile.DRIVER_ID, driverId)
                            .findAll();

                    return realmProfiles.<RealmProfile>asFlowable()
                            .switchMap((Function<RealmResults<RealmProfile>, Publisher<Optional<RealmProfile>>>) realmProfiles1 -> {
                                if (realmProfiles1.isEmpty())
                                    return Flowable.just(Optional.empty());
                                else
                                    return Flowable.just(Optional.of(pair.first.copyFromRealm(realmProfiles1)))
                                            .map(Optional::get)
                                            .map(value -> value.get(0))
                                            .filter(
                                                    profile -> profile.isLoaded()
                                                            && profile.isValid())
                                            .map(Optional::of);
                            });
                }
                , pair -> close(pair.first, pair.second))
                .unsubscribeOn(AndroidSchedulers.from(Looper.myLooper())))
                .map(optional -> {
                    if (optional.isPresent()) {
                        return Optional.of(optional.get());
                    }
                    return Optional.empty();
                });
    }
}
