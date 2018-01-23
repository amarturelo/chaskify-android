package com.chaskify.android.ui.fragments.launch;

import com.chaskify.android.helper.MethodCallHelper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.model.ChaskifySettings;
import com.chaskify.data.realm.cache.impl.NotificationsCacheImpl;
import com.chaskify.data.realm.cache.impl.ProfileCacheImpl;
import com.chaskify.data.realm.cache.impl.SettingsCacheImpl;
import com.chaskify.data.realm.cache.impl.TaskCacheImpl;
import com.chaskify.data.realm.cache.impl.TaskWayPointCacheImpl;
import com.chaskify.domain.interactors.ProfileInteractor;
import com.chaskify.domain.interactors.SettingsInteractor;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import timber.log.Timber;

/**
 * Created by alberto on 12/12/17.
 */

public class SplashPresenter extends BasePresenter<SplashContract.View>
        implements SplashContract.Presenter {

    private ProfileInteractor profileInteractor;

    private SettingsInteractor settingsInteractor;

    private MethodCallHelper mMethodCallHelper;

    private ChaskifySession mChaskifySession;

    public SplashPresenter(ChaskifySession chaskifySession, ProfileInteractor profileInteractor, SettingsInteractor settingsInteractor) {
        Timber.tag(this.getClass().getSimpleName());
        this.mChaskifySession = chaskifySession;
        this.profileInteractor = profileInteractor;
        this.settingsInteractor = settingsInteractor;

        mMethodCallHelper = new MethodCallHelper(mChaskifySession
                , new TaskCacheImpl()
                , new NotificationsCacheImpl()
                , new ProfileCacheImpl()
                , new SettingsCacheImpl()
                , new TaskWayPointCacheImpl()
        );
    }

    @Override
    public void init(String driverId) {
        view.showProgress();
        mChaskifySession.getChaskifySettings(new ApiCallback<ChaskifySettings>() {
            @Override
            public void onSuccess(ChaskifySettings info) {
                view.complete();
            }

            @Override
            public void onNetworkError(Exception e) {
                view.showError(e);
            }

            @Override
            public void onChaskifyError(Exception e) {
                view.showError(e);
            }

            @Override
            public void onUnexpectedError(Exception e) {
                view.showError(e);
            }
        });

    }

}
