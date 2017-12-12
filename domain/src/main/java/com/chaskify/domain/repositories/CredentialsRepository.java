package com.chaskify.domain.repositories;

import com.chaskify.domain.model.Credentials;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by alberto on 11/12/17.
 */

public interface CredentialsRepository {
    Single<List<Credentials>> credentials();
}
