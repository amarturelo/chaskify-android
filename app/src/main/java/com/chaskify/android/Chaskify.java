package com.chaskify.android;

import android.content.Context;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.chaskify.android.store.AccountStorage;
import com.chaskify.android.store.LoginStorage;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.chaskify_sdk.rest.model.login.ChaskifyCredentials;
import com.chaskify.domain.model.Credentials;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;

/**
 * Created by alberto on 9/12/17.
 */

public class Chaskify {

    private static final String LOG_TAG = Chaskify.class.getSimpleName();

    List<ChaskifySession> mChaskifySessions;

    private LoginStorage mLoginStorage;

    private AccountStorage mAccountStorage;

    private static Chaskify ourInstance = null;

    public static Chaskify getInstance() {
        return ourInstance;
    }

    public static Chaskify getInstance(Context context) {
        if (ourInstance == null && context != null)
            ourInstance = new Chaskify(context);
        return ourInstance;
    }

    private Chaskify(Context context) {
        mAccountStorage = new AccountStorage(context);
        mChaskifySessions = new ArrayList<>();
        mLoginStorage = new LoginStorage();
    }

    public synchronized Optional<ChaskifySession> getDefaultSession() {
        List<ChaskifySession> sessions = getSessions();

        if (sessions.isEmpty())
            return Optional.empty();

        String name = mAccountStorage.getDefaultAccount();

        return Stream.of(getSessions())
                .filter(value -> value.equals(name))
                .findFirst();
    }

    public synchronized void addSession(ChaskifySession chaskifySession) {
        Credentials credentials = new Credentials()
                .setAccessToken(chaskifySession.getCredentials()
                        .getAccessToken())
                .setPassword(chaskifySession.getCredentials()
                        .getPassword())
                .setUsername(chaskifySession.getCredentials()
                        .getUsername());


        mLoginStorage.addCredentials(credentials);
        mLoginStorage.setDefault(credentials);
        synchronized (LOG_TAG) {
            mChaskifySessions.add(chaskifySession);
        }
    }

    public static ChaskifySession createSession(Credentials credentials) {
        return new ChaskifySession(new ChaskifyCredentials()
                .setAccessToken(credentials.getAccessToken())
                .setPassword(credentials.getPassword())
                .setUsername(credentials.getUsername()));
    }

    public Completable fetch() {
        return Completable.create(emitter -> {
            Stream.of(mLoginStorage.getCredentialsList())
                    .filter(value -> mAccountStorage.insert(value))
                    .forEach(credentials -> mChaskifySessions.add(createSession(credentials)));
            emitter.onComplete();
        });
    }

    public List<ChaskifySession> getSessions() {
        List<ChaskifySession> sessions = new ArrayList<>();

        synchronized (this.getClass().getSimpleName()) {
            if (null != mChaskifySessions) {
                sessions = new ArrayList<>(mChaskifySessions);
            }
        }
        return sessions;
    }
}
