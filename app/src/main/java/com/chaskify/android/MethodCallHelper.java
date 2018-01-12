package com.chaskify.android;

import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallbackSuccess;
import com.chaskify.chaskify_sdk.rest.exceptions.TokenNotFoundException;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyCalendarTask;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyProfile;
import com.chaskify.chaskify_sdk.rest.model.ChaskifySettings;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTask;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTaskWaypoint;
import com.chaskify.data.realm.cache.NotificationsCache;
import com.chaskify.data.realm.cache.ProfileCache;
import com.chaskify.data.realm.cache.SettingsCache;
import com.chaskify.data.realm.cache.TaskCache;
import com.chaskify.data.realm.cache.impl.mapper.ProfileDataMapper;
import com.chaskify.data.realm.cache.impl.mapper.SettingsDataMapper;
import com.chaskify.data.realm.cache.impl.mapper.TaskDataMapper;

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

    public Task<Void> getWaypointById(String id) {
        TaskCompletionSource<ChaskifyTaskWaypoint> task = new TaskCompletionSource<>();
        try {
            mChaskifySession.getTaskWaypointRestClient().wayPointById(id, new ApiCallback<ChaskifyTaskWaypoint>() {
                @Override
                public void onSuccess(ChaskifyTaskWaypoint info) {
                    task.trySetResult(info);
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
        return task.getTask()
                .onSuccessTask(value -> {
                    //TODO save to cacheWayPoint
                    return null;
                });
    }

    public Task<Void> getSettings() {
        TaskCompletionSource<ChaskifySettings> task = new TaskCompletionSource<>();

        try {
            mChaskifySession.getSettingsRestClient().getSettings(new ApiCallback<ChaskifySettings>() {
                @Override
                public void onSuccess(ChaskifySettings info) {
                    task.setResult(info);
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

        return task.getTask()
                .onSuccessTask(task1 -> {
                    mSettingsCache.put(SettingsDataMapper.transform(task1.getResult()));
                    return null;
                });
    }

    public Task<Void> getProfile() {
        TaskCompletionSource<ChaskifyProfile> task = new TaskCompletionSource<>();
        try {
            mChaskifySession.getProfileRestClient()
                    .getProfile(new ApiCallback<ChaskifyProfile>() {
                        @Override
                        public void onSuccess(ChaskifyProfile info) {
                            task.setResult(info);
                        }

                        @Override
                        public void onNetworkError(Exception e) {
                            task.setError(e);
                        }

                        @Override
                        public void onChaskifyError(Exception e) {
                            task.setError(e);
                        }

                        @Override
                        public void onUnexpectedError(Exception e) {
                            task.setError(e);
                        }
                    });
        } catch (TokenNotFoundException e) {
            task.setError(e);
        }
        return task.getTask()
                .onSuccessTask(task1 -> {
                    mProfileCache.put(ProfileDataMapper.transform(task1.getResult()));
                    return null;
                });
    }

    public Task<Void> getCalendarTaskByRangeOfDate(Date start, Date end) {
        TaskCompletionSource<List<ChaskifyCalendarTask>> task = new TaskCompletionSource<>();
        try {
            mChaskifySession.getCalendarTaskRestClient().getCalendarTaskByRangeOfDate(start, end, new ApiCallback<List<ChaskifyCalendarTask>>() {
                @Override
                public void onSuccess(List<ChaskifyCalendarTask> info) {
                    task.trySetResult(info);
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
        return task.getTask()
                .onSuccessTask(value -> {
                    //TODO save to cacheCalendar
                    return null;
                });
    }

    public Task<Void> getTasksByDate(Date date) {
        TaskCompletionSource<List<ChaskifyTask>> task = new TaskCompletionSource<>();
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
        return task
                .getTask()
                .onSuccessTask(value -> {
                    mTaskCache.put(TaskDataMapper.transform(value.getResult()));
                    return null;
                });
    }

    public Task<Void> getTaskDetails(String taskId) {
        TaskCompletionSource<ChaskifyTask> task = new TaskCompletionSource<>();

        try {
            mChaskifySession.getTaskRestClient().taskDetails(taskId, new ApiCallback<ChaskifyTask>() {
                @Override
                public void onSuccess(ChaskifyTask info) {
                    task.trySetResult(info);
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

        return task
                .getTask()
                .onSuccessTask(value -> {
                    mTaskCache.put(TaskDataMapper.transform(value.getResult()));
                    return null;
                });
    }

    public Task<Void> updateProfile(String newPhone) {
        TaskCompletionSource<Void> task = new TaskCompletionSource<>();

        try {
            mChaskifySession.updateProfile(newPhone, new ApiCallbackSuccess() {
                @Override
                public void onSuccess() {
                    task.trySetResult(null);
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
        return task.getTask()
                .onSuccessTask(value -> {
                    mProfileCache.getByDriverId(mChaskifySession
                            .getCredentials()
                            .getDriverId()
                    )
                            .ifPresent(realmProfile -> {
                                realmProfile.setPhone(newPhone);
                                mProfileCache.put(realmProfile);
                            });
                    return null;
                });
    }

    public Task<Void> updateProfileVehicle(String transportTypeTd, String transportDescription, String licencePlate, String color) {
        TaskCompletionSource<Void> task = new TaskCompletionSource<>();
        try {
            mChaskifySession.updateVehicle(transportTypeTd, transportDescription, licencePlate, color, new ApiCallbackSuccess() {
                @Override
                public void onSuccess() {
                    task.trySetResult(null);
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
        return task.getTask()
                .onSuccessTask(value -> {
                    mProfileCache.getByDriverId(mChaskifySession
                            .getCredentials()
                            .getDriverId()
                    )
                            .ifPresent(realmProfile -> {
                                realmProfile.setTransportTypeId(transportTypeTd)
                                        .setTransportDescription(transportDescription)
                                        .setLicencePlate(licencePlate)
                                        .setColor(color);
                                mProfileCache.put(realmProfile);
                            });

                    return null;
                });
    }

    /**
     * update image profile and if complete success save in db
     *
     * @param base64
     * @return
     */
    public Task<Void> updateProfileImage(String base64) {
        TaskCompletionSource<String> task = new TaskCompletionSource<>();
        try {
            mChaskifySession.updateImageProfile(base64, new ApiCallback<String>() {
                @Override
                public void onSuccess(String info) {
                    task.trySetResult(info);

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
        return task.getTask()
                .onSuccessTask(value -> {
                    mProfileCache.getByDriverId(mChaskifySession
                            .getCredentials()
                            .getDriverId()
                    )
                            .ifPresent(realmProfile -> mProfileCache.put(realmProfile
                                    .setDriverPicture(value.getResult())));
                    return null;
                });
    }

    public Task<Void> updateSettings(boolean enable) {
        TaskCompletionSource<Void> task = new TaskCompletionSource<>();

        try {
            mChaskifySession.updateSettingsPush(enable, new ApiCallbackSuccess() {
                @Override
                public void onSuccess() {
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
