package com.chaskify.data.repositories;

import com.annimon.stream.Stream;
import com.chaskify.data.model.RealmHomeServerConnectionConfig;
import com.chaskify.domain.model.HomeServerConnectionConfig;
import com.chaskify.domain.repositories.HomeServerConfigurationRepository;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.realm.Realm;

/**
 * Created by alberto on 10/12/17.
 */

public class RealmServerConfigurationRepositoryImpl implements HomeServerConfigurationRepository {

    public RealmServerConfigurationRepositoryImpl() {
    }

    @Override
    public Single<List<HomeServerConnectionConfig>> getHomeServerConfigurations() {
        return Single
                .create((SingleOnSubscribe<List<RealmHomeServerConnectionConfig>>) emitter -> {
                    Realm realm = Realm.getDefaultInstance();
                    emitter.onSuccess(realm.where(RealmHomeServerConnectionConfig.class).findAll());
                })
                .map(toMapper -> Stream.of(toMapper)
                        .map(value -> value.toDomain())
                        .toList());
    }
}
