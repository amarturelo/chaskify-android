package com.chaskify.domain.repositories;

import com.chaskify.domain.model.HomeServerConnectionConfig;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by alberto on 10/12/17.
 */

public interface LoginRepository {
    Single<List<HomeServerConnectionConfig>> getHomeServerConfigurations();
}
