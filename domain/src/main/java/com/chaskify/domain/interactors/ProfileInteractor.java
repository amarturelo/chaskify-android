package com.chaskify.domain.interactors;

import com.annimon.stream.Optional;
import com.chaskify.domain.model.Profile;
import com.chaskify.domain.repositories.ProfileRepository;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by alberto on 29/12/17.
 */

public class ProfileInteractor {

    private ProfileRepository profileRepository;

    public ProfileInteractor(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Flowable<Optional<Profile>> profileByDriverId(String driver_id) {
        return profileRepository.profileByDriverId(driver_id);
    }
}
