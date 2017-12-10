package com.chaskify.domain.interactors;

import com.chaskify.domain.model.HomeServerConnectionConfig;
import com.chaskify.domain.repositories.HomeServerConfigurationRepository;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by alberto on 10/12/17.
 */

public class HomeServerConfigurationInteractor {

    private HomeServerConfigurationRepository homeServerConfigurationRepository;

    public HomeServerConfigurationInteractor(HomeServerConfigurationRepository homeServerConfigurationRepository) {
        this.homeServerConfigurationRepository = homeServerConfigurationRepository;
    }

    public Single<List<HomeServerConnectionConfig>> homeServerConnectionConfigs() {
        return homeServerConfigurationRepository.getHomeServerConfigurations();
    }
}
