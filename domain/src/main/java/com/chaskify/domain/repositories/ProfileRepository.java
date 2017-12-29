package com.chaskify.domain.repositories;

import com.chaskify.domain.model.Profile;

import io.reactivex.Single;

/**
 * Created by alberto on 29/12/17.
 */

public interface ProfileRepository {
    Single<Profile> profile();
}
