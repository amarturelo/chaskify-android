package com.chaskify.domain.repositories;

import com.annimon.stream.Optional;
import com.chaskify.domain.model.Profile;


import io.reactivex.Flowable;

/**
 * Created by alberto on 29/12/17.
 */

public interface ProfileRepository {
    Flowable<Optional<Profile>> profileByDriverId(String driver_id);
}
