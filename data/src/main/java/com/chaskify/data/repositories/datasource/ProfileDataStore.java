package com.chaskify.data.repositories.datasource;

import com.annimon.stream.Optional;
import com.chaskify.domain.model.Profile;

import io.reactivex.Flowable;

/**
 * Created by aaherrera on 10/26/17.
 */

public interface ProfileDataStore {

    Flowable<Optional<Profile>> getProfileByDriverId(String driverId);

}
