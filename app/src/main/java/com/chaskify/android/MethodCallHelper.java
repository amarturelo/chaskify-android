package com.chaskify.android;

import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.exceptions.TokenNotFoundException;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTask;
import com.chaskify.data.realm.cache.NotificationsCache;
import com.chaskify.data.realm.cache.ProfileCache;
import com.chaskify.data.realm.cache.SettingsCache;
import com.chaskify.data.realm.cache.TaskCache;

import java.util.Date;
import java.util.List;

import bolts.Task;
import bolts.TaskCompletionSource;
import timber.log.Timber;

/**
 * Created by alberto on 11/01/18.
 */

public class MethodCallHelper {
    private ChaskifySession mChaskifySession;

    private TaskCache mTaskCache;
    private NotificationsCache mNotificationsCache;
    private ProfileCache mProfileCache;
    private SettingsCache mSettingsCache;

    public MethodCallHelper(ChaskifySession mChaskifySession, TaskCache mTaskCache, NotificationsCache mNotificationsCache, ProfileCache mProfileCache, SettingsCache mSettingsCache) {
        Timber.tag(this.getClass().getSimpleName());
        this.mChaskifySession = mChaskifySession;
        this.mTaskCache = mTaskCache;
        this.mNotificationsCache = mNotificationsCache;
        this.mProfileCache = mProfileCache;
        this.mSettingsCache = mSettingsCache;
    }

    public Task<Void> getTasksByDate(Date date) {
        TaskCompletionSource<Void> task = new TaskCompletionSource<>();
        try {
            mChaskifySession.getTaskRestClient().taskByDate(date, new ApiCallback<List<ChaskifyTask>>() {
                @Override
                public void onSuccess(List<ChaskifyTask> info) {
                    task.setResult(null);
                }

                @Override
                public void onNetworkError(Exception e) {
                    task.trySetError(e);
                }

                @Override
                public void onChaskifyError(Exception e) {
                    task.trySetError(e);
                }

                @Override
                public void onUnexpectedError(Exception e) {
                    task.trySetError(e);
                }
            });
        } catch (TokenNotFoundException e) {
            task.trySetError(e);
        }
        return task.getTask();
    }
}
