package com.chaskify.data.repositories;

import com.annimon.stream.Stream;
import com.chaskify.data.cache.CredentialsCache;
import com.chaskify.data.model.internal.RealmCredentials;
import com.chaskify.domain.model.Credentials;
import com.chaskify.domain.repositories.CredentialsRepository;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * Created by alberto on 11/12/17.
 */

public class RealmCredentialsRepositoryImpl implements CredentialsRepository {

    private CredentialsCache credentialsCache;

    public RealmCredentialsRepositoryImpl(CredentialsCache credentialsCache) {
        this.credentialsCache = credentialsCache;
    }

    @Override
    public Single<List<Credentials>> credentials() {
        return credentialsCache.findAll()
                .map(realmCredentials -> Stream
                        .of(realmCredentials)
                        .map(RealmCredentials::toDomain)
                        .toList());
    }
}
