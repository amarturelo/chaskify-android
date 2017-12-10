package com.chaskify.data.repositories;

import com.chaskify.domain.model.HomeServerConnectionConfig;
import com.chaskify.domain.repositories.HomeServerConfigurationRepository;

import io.reactivex.Single;

/**
 * Created by alberto on 10/12/17.
 */

public class RealmServerConfigurationRepository implements HomeServerConfigurationRepository {

    public RealmServerConfigurationRepository() {
    }

    @Override
    public Single<HomeServerConnectionConfig> getHomeServerConfigurations() {
        return null;
    }
}
