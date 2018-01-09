package com.chaskify.android;

import android.content.Context;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.chaskify.android.store.ConfigurationServerStorage;
import com.chaskify.android.store.LoginStorage;
import com.chaskify.android.store.PreferenceStorage;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallbackSuccess;
import com.chaskify.chaskify_sdk.rest.exceptions.TokenNotFoundException;
import com.chaskify.chaskify_sdk.rest.model.login.ChaskifyCredentials;
import com.chaskify.domain.model.Credentials;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import timber.log.Timber;

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
                        .forEach(credentials -> mChaskifySessions.add(createSession(credentials))));
    }

    public synchronized void addSession(ChaskifySession chaskifySession, String password) {
        synchronized (LOG_TAG) {
            if (mAccountStorage
                    .addCredentials(new Credentials()
                                    .setUsername(chaskifySession.getCredentials().getUsername())
                                    .setDriverId(chaskifySession.getCredentials().getDriverId())
                                    .setAccessToken(chaskifySession.getCredentials().getAccessToken())
                            , password
                    )) {
                mChaskifySessions.add(chaskifySession);
                setDefault(chaskifySession);
            }
        }
    }

    public void replaceSession(ChaskifySession session, String password) {
        synchronized (LOG_TAG) {
            mAccountStorage
                    .replaceCredentials(new Credentials()
                                    .setUsername(session.getCredentials().getUsername())
                                    .setDriverId(session.getCredentials().getDriverId())
                                    .setAccessToken(session.getCredentials().getAccessToken())
                            , password);
            Stream.of(mChaskifySessions)
                    .filter(value -> value.getCredentials().getDriverId().equals(session.getCredentials().getDriverId())).findFirst()
                    .ifPresent(chaskifySession -> mChaskifySessions.remove(chaskifySession));
            mChaskifySessions.add(session);
            setDefault(session);
        }
    }

    public void setDefault(ChaskifySession chaskifySession) {
        mPreferenceStorage.setDefault(chaskifySession.getCredentials().getUsername());
    }

    public static ChaskifySession createSession(Credentials credentials) {
        return new ChaskifySession(new ChaskifyCredentials()
                .setAccessToken(credentials.getAccessToken())
                .setDriverId(credentials.getDriverId())
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

    public void logout(ChaskifySession chaskifySession, ApiCallbackSuccess callback) throws TokenNotFoundException {
        Timber.d("::logout " + chaskifySession.toString() + " ::");

        ApiCallbackSuccess c = new ApiCallbackSuccess() {
            @Override
            public void onSuccess() {
                invalidateSession(chaskifySession);
                callback.onSuccess();
            }

            @Override
            public void onNetworkError(Exception e) {
                invalidateSession(chaskifySession);
                callback.onNetworkError(e);
            }

            @Override
            public void onChaskifyError(Exception e) {
                invalidateSession(chaskifySession);
                callback.onChaskifyError(e);
            }

            @Override
            public void onUnexpectedError(Exception e) {
                invalidateSession(chaskifySession);
                callback.onUnexpectedError(e);
            }
        };

        chaskifySession.logout(c);
    }

    private void invalidateSession(ChaskifySession chaskifySession) {
        mAccountStorage.invalidateCredentials(chaskifySession.getCredentials().getUsername());
        mPreferenceStorage.removeDefault(chaskifySession.getCredentials().getUsername());
    }

    private void clear(ChaskifySession chaskifySession) {

    }


}
