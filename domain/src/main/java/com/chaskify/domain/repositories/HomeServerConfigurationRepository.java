package com.chaskify.domain.repositories;

import com.chaskify.domain.model.HomeServerConnectionConfig;

import io.reactivex.Single;

/**
 * Created by alberto on 10/12/17.
 */

public interface HomeServerConfigurationRepository {
    Single<HomeServerConnectionConfig> getHomeServerConfigurations();
}
