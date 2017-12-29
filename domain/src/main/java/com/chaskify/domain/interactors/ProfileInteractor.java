package com.chaskify.domain.interactors;

import com.chaskify.domain.model.Profile;
import com.chaskify.domain.repositories.ProfileRepository;

import io.reactivex.Single;

/**
 * Created by alberto on 29/12/17.
 */

public class ProfileInteractor {

    private ProfileRepository profileRepository;

    public ProfileInteractor(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Single<Profile> profile() {
        return profileRepository.profile();
    }
}
