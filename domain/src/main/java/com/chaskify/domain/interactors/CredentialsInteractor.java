package com.chaskify.domain.interactors;

import com.chaskify.domain.model.Credentials;
import com.chaskify.domain.repositories.CredentialsRepository;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by alberto on 10/12/17.
 */

public class CredentialsInteractor {

    private CredentialsRepository credentialsRepository;

    public CredentialsInteractor(CredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    public Single<List<Credentials>> getCredentials() {
        return credentialsRepository.credentials();
    }
}
