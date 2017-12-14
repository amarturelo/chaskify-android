package com.chaskify.android;

import android.content.Context;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Consumer;
import com.chaskify.android.store.LoginStorage;
import com.chaskify.android.store.ConfigurationServerStorage;
import com.chaskify.android.store.PreferenceStorage;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.chaskify_sdk.rest.model.login.ChaskifyCredentials;
import com.chaskify.domain.model.Credentials;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.Action;

/**
 * Created by alberto on 9/12/17.
 */

public class Chaskify {

    private static final String LOG_TAG = Chaskify.class.getSimpleName();

    List<ChaskifySession> mChaskifySessions;

    PreferenceStorage mPreferenceStorage;

    private ConfigurationServerStorage mConfigurationServer;

    private LoginStorage mAccountStorage;

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
        mPreferenceStorage = new PreferenceStorage(context);
        mAccountStorage = new LoginStorage(context);
        mChaskifySessions = new ArrayList<>();
        mConfigurationServer = new ConfigurationServerStorage();
    }

    public synchronized Optional<ChaskifySession> getDefaultSession() {
        Optional<String> username = mPreferenceStorage.getDefault();
        if (username.isPresent())
            return Stream.of(mChaskifySessions)
                    .filter(value -> value.getCredentials().getUsername().equals(username.get())).findFirst();
        else
            return Optional.empty();
    }

    public Completable synchronize() {
        return Completable.create(emitter -> {
            Stream.of(mConfigurationServer.getConfigurations())
                    .forEach(serverConfiguration -> mAccountStorage.addCredentials(
                            new Credentials()
                                    .setUsername(serverConfiguration.getUsername())
                            , null
                    ));
            emitter.onComplete();
        })
                .doOnComplete(() -> Stream.of(mAccountStorage.getCredentials())
                        .forEach(credentials -> addSession(createSession(credentials))));
    }

    public synchronized void addSession(ChaskifySession chaskifySession) {
        synchronized (LOG_TAG) {
            mChaskifySessions.add(chaskifySession);
        }
    }

    public static ChaskifySession createSession(Credentials credentials) {
        return new ChaskifySession(new ChaskifyCredentials()
                .setAccessToken(credentials.getAccessToken())
                .setUsername(credentials.getUsername()));
    }

    public List<ChaskifySession> getSessions() {
        List<ChaskifySession> sessions = new ArrayList<>();

        synchronized (LOG_TAG) {
            if (null != mChaskifySessions) {
                sessions = new ArrayList<>(mChaskifySessions);
            }
        }
        return sessions;
    }
}
