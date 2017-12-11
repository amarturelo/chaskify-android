package com.chaskify.domain.interactors;

import com.chaskify.domain.model.HomeServerConnectionConfig;
import com.chaskify.domain.repositories.LoginRepository;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by alberto on 10/12/17.
 */

public class HomeServerConfigurationInteractor {

    private LoginRepository homeServerConfigurationRepository;

    public HomeServerConfigurationInteractor(LoginRepository homeServerConfigurationRepository) {
        this.homeServerConfigurationRepository = homeServerConfigurationRepository;
    }

    public Single<List<HomeServerConnectionConfig>> homeServerConnectionConfigs() {
        return homeServerConfigurationRepository.getHomeServerConfigurations();
    }
}
