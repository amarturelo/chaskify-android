package com.chaskify.data.repositories.datasource;

import com.chaskify.domain.model.Profile;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by aaherrera on 10/26/17.
 */

public interface ProfileDataStore {

    Single<Profile> getProfileByDriverId(String driverId);

}
