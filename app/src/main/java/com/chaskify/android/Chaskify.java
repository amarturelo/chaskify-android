package com.chaskify.android;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Consumer;
import com.chaskify.android.store.LoginStorage;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.chaskify_sdk.ChaskifyServerConfiguration;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyIcons;
import com.chaskify.chaskify_sdk.rest.model.login.ChaskifyCredentials;
import com.chaskify.domain.model.Credentials;
import com.chaskify.domain.model.ServerConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alberto on 9/12/17.
 */

public class Chaskify {

    private static final String LOG_TAG = Chaskify.class.getSimpleName();

    List<ChaskifySession> mChaskifySessions;

    private LoginStorage mLoginStorage;

    private static Chaskify ourInstance = null;

    public static Chaskify getInstance() {
        if (ourInstance == null)
            ourInstance = new Chaskify();
        return ourInstance;
    }

    private Chaskify() {
        mChaskifySessions = new ArrayList<>();
        mLoginStorage = new LoginStorage();
        fetchChaskifySessions();
    }

    private void fetchChaskifySessions() {
        List<ChaskifySession> sessions = getSessions();

        List<Credentials> hsConfigList = mLoginStorage.getCredentialsList();

        Stream.of(hsConfigList)
                .map(Chaskify::createSession)
                .filter(value -> !mChaskifySessions.contains(value))
                .forEach(sessions::add);

        synchronized (LOG_TAG) {
            mChaskifySessions = sessions;
        }
    }

    public synchronized Optional<ChaskifySession> getDefaultSession() {
        List<Credentials> credentialsList = mLoginStorage.getCredentialsList();

        Optional<Credentials> credentialsOptional = Stream.of(credentialsList)
                .filter(value -> value.isDefault())
                .findFirst();

        Optional<ChaskifySession> defaultSession;
        if (credentialsOptional.isPresent()) {
            defaultSession = Stream.of(mChaskifySessions)
                    .filter(value -> value.getCredentials().equals(credentialsOptional.get().getUsername()))
                    .findFirst();
            if (defaultSession.isPresent())
                return defaultSession;
            else {
                ChaskifySession chaskifySession = createSession(credentialsOptional.get());
                mChaskifySessions.add(chaskifySession);
                return Optional.of(chaskifySession);
            }
        } else
            return Optional.empty();
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
