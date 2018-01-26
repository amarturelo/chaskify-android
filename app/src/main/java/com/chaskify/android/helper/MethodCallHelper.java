package com.chaskify.android.helper;

import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallbackSuccess;
import com.chaskify.chaskify_sdk.rest.exceptions.TokenNotFoundException;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyCalendarTask;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyProfile;
import com.chaskify.chaskify_sdk.rest.model.ChaskifySettings;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTask;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyTaskWayPoint;
import com.chaskify.data.realm.cache.NotificationsCache;
import com.chaskify.data.realm.cache.ProfileCache;
import com.chaskify.data.realm.cache.SettingsCache;
import com.chaskify.data.realm.cache.TaskCache;
import com.chaskify.data.realm.cache.TaskWayPointCache;
import com.chaskify.data.realm.cache.impl.mapper.ProfileDataMapper;
import com.chaskify.data.realm.cache.impl.mapper.TaskDataMapper;
import com.chaskify.data.realm.cache.impl.mapper.TaskWaypointDataMapper;

import java.util.Date;
import java.util.List;

import bolts.Task;
import bolts.TaskCompletionSource;

/**
 * Created by alberto on 11/01/18.
 */

public class MethodCallHelper {
    private ChaskifySession mChaskifySession;

    private TaskCache mTaskCache;
    private NotificationsCache mNotificationsCache;
    private ProfileCache mProfileCache;
    private SettingsCache mSettingsCache;
    private TaskWayPointCache mTaskWayPointCache;

    public MethodCallHelper(ChaskifySession mChaskifySession, TaskCache mTaskCache, NotificationsCache mNotificationsCache, ProfileCache mProfileCache, SettingsCache mSettingsCache, TaskWayPointCache mTaskWayPointCache) {
        this.mChaskifySession = mChaskifySession;
        this.mTaskCache = mTaskCache;
        this.mNotificationsCache = mNotificationsCache;
        this.mProfileCache = mProfileCache;
        this.mSettingsCache = mSettingsCache;
        this.mTaskWayPointCache = mTaskWayPointCache;
    }

    public ChaskifySession getChaskifySession() {
        return mChaskifySession;
    }

    public MethodCallHelper setmChaskifySession(ChaskifySession mChaskifySession) {
        this.mChaskifySession = mChaskifySession;
        return this;
    }

    public Task<Void> getWaypointById(String id) {
        TaskCompletionSource<ChaskifyTaskWayPoint> task = new TaskCompletionSource<>();
        try {
            mChaskifySession.getTaskWayPointRestClient().wayPointById(id, new ApiCallback<ChaskifyTaskWayPoint>() {
                @Override
                public void onSuccess(ChaskifyTaskWayPoint info) {
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
                    mTaskWayPointCache.put(TaskWaypointDataMapper.transform(value.getResult()));
                    return null;
                });
    }

    public Task<ChaskifySettings> getSettings() {
        TaskCompletionSource<ChaskifySettings> task = new TaskCompletionSource<>();
        mChaskifySession.getChaskifySettings(new ApiCallback<ChaskifySettings>() {
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

        return task.getTask();
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
        mChaskifySession.getTaskRestClient().taskByDate(date, new ApiCallback<List<ChaskifyTask>>() {
            @Override
            public void onSuccess(List<ChaskifyTask> info) {
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

        return task
                .getTask()
                .onSuccessTask(value -> {
                    if (!value.getResult().isEmpty())
                        mTaskCache.put(TaskDataMapper.transform(value.getResult()));
                    return null;
                });
    }

    public Task<Void> getTaskDetails(String taskId) {
        TaskCompletionSource<ChaskifyTask> task = new TaskCompletionSource<>();

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
        return task.getTask()
                .onSuccessTask(value -> {
                    mProfileCache.getByDriverIdAsObservable(mChaskifySession
                            .getCredentials()
                            .getDriverId()
                    )
                            .firstOrError()
                            .blockingGet()
                            .ifPresent(realmProfile -> mProfileCache.put(realmProfile
                                    .setDriverPicture(value.getResult())));
                    return null;
                });
    }

    /**
     * update settings
     *
     * @param enable
     * @return
     */
    public Task<Void> updateSettings(boolean enable) {
        TaskCompletionSource<Void> task = new TaskCompletionSource<>();
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


        return task.getTask()
                ;
    }

    public Task<Void> acceptTask(String id) {
        TaskCompletionSource<ChaskifyTask> task = new TaskCompletionSource<>();
        mChaskifySession.getTaskRestClient().changeTaskStatus(id, ChaskifyTask.STATUS.ACCEPTED, "", "0", "0", new ApiCallback<ChaskifyTask>() {
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
        return task.getTask()
                .onSuccessTask(
                        task1 -> {
                            mTaskCache.put(TaskDataMapper.transform(task1.getResult()));
                            return null;
                        }
                );
    }

    public Task<Void> startTask(String id) {
        TaskCompletionSource<ChaskifyTask> task = new TaskCompletionSource<>();
        mChaskifySession.getTaskRestClient().changeTaskStatus(id, ChaskifyTask.STATUS.IN_ROUTE, "", "0", "0", new ApiCallback<ChaskifyTask>() {
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
        return task.getTask()
                .onSuccessTask(
                        task1 -> {
                            mTaskCache.put(TaskDataMapper.transform(task1.getResult()));
                            return null;
                        }
                );
    }

    public Task<Void> arrivedTask(String id) {
        TaskCompletionSource<ChaskifyTask> task = new TaskCompletionSource<>();
        mChaskifySession.getTaskRestClient().changeTaskStatus(id, ChaskifyTask.STATUS.ARRIVED, "", "0", "0", new ApiCallback<ChaskifyTask>() {
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
        return task.getTask()
                .onSuccessTask(
                        task1 -> {
                            mTaskCache.put(TaskDataMapper.transform(task1.getResult()));
                            return null;
                        }
                );
    }

    public Task<Void> successfulTask(String id) {
        TaskCompletionSource<ChaskifyTask> task = new TaskCompletionSource<>();
        mChaskifySession.getTaskRestClient().changeTaskStatus(id, ChaskifyTask.STATUS.SUCCESSFUL, "", "0", "0", new ApiCallback<ChaskifyTask>() {
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
        return task.getTask()
                .onSuccessTask(
                        task1 -> {
                            mTaskCache.put(TaskDataMapper.transform(task1.getResult()));
                            return null;
                        }
                );
    }


    public Task<Void> declineTask(String id) {
        TaskCompletionSource<ChaskifyTask> wayPoint = new TaskCompletionSource<>();

        mChaskifySession.getTaskRestClient().changeTaskStatus(id, ChaskifyTask.STATUS.DECLINED, "", "", "", new ApiCallback<ChaskifyTask>() {
            @Override
            public void onNetworkError(Exception e) {
                wayPoint.trySetError(e);
            }

            @Override
            public void onChaskifyError(Exception e) {
                wayPoint.trySetError(e);
            }

            @Override
            public void onUnexpectedError(Exception e) {
                wayPoint.trySetError(e);
            }

            @Override
            public void onSuccess(ChaskifyTask info) {
                wayPoint.trySetResult(info);
            }
        });

        return wayPoint.getTask()
                .onSuccessTask(task -> {
                    mTaskCache.put(TaskDataMapper.transform(task.getResult()));
                    return null;
                });
    }

    public Task<Void> cancelTask(String taskId, String reason) {
        TaskCompletionSource<ChaskifyTask> wayPoint = new TaskCompletionSource<>();

        mChaskifySession.getTaskRestClient().changeTaskStatus(taskId, ChaskifyTask.STATUS.CANCELED, reason, "", "", new ApiCallback<ChaskifyTask>() {
            @Override
            public void onNetworkError(Exception e) {
                wayPoint.trySetError(e);
            }

            @Override
            public void onChaskifyError(Exception e) {
                wayPoint.trySetError(e);
            }

            @Override
            public void onUnexpectedError(Exception e) {
                wayPoint.trySetError(e);
            }

            @Override
            public void onSuccess(ChaskifyTask info) {
                wayPoint.trySetResult(info);
            }
        });

        return wayPoint.getTask()
                .onSuccessTask(task -> {
                    mTaskCache.put(TaskDataMapper.transform(task.getResult()));
                    return null;
                });
    }

    public Task<Void> successfulTaskWayPoint(String id) {
        TaskCompletionSource<ChaskifyTaskWayPoint> wayPoint = new TaskCompletionSource<>();

        mChaskifySession.getTaskWayPointRestClient().changeTaskWayPointStatus(id, ChaskifyTaskWayPoint.STATUS.COMPLETED, "", "", new ApiCallback<ChaskifyTaskWayPoint>() {
            @Override
            public void onNetworkError(Exception e) {
                wayPoint.trySetError(e);
            }

            @Override
            public void onChaskifyError(Exception e) {
                wayPoint.trySetError(e);
            }

            @Override
            public void onUnexpectedError(Exception e) {
                wayPoint.trySetError(e);
            }

            @Override
            public void onSuccess(ChaskifyTaskWayPoint info) {
                wayPoint.trySetResult(info);
            }
        });

        return wayPoint.getTask()
                .onSuccessTask(task -> {
                    mTaskWayPointCache.put(TaskWaypointDataMapper.transform(task.getResult()));
                    return null;
                });
    }

    public Task<Void> startTaskWayPoint(String id) {

        TaskCompletionSource<ChaskifyTaskWayPoint> wayPoint = new TaskCompletionSource<>();

        mChaskifySession.getTaskWayPointRestClient().changeTaskWayPointStatus(id, ChaskifyTaskWayPoint.STATUS.IN_ROUTE, "", "", new ApiCallback<ChaskifyTaskWayPoint>() {
            @Override
            public void onNetworkError(Exception e) {
                wayPoint.trySetError(e);
            }

            @Override
            public void onChaskifyError(Exception e) {
                wayPoint.trySetError(e);
            }

            @Override
            public void onUnexpectedError(Exception e) {
                wayPoint.trySetError(e);
            }

            @Override
            public void onSuccess(ChaskifyTaskWayPoint info) {
                wayPoint.trySetResult(info);
            }
        });

        return wayPoint.getTask()
                .onSuccessTask(task -> {
                    mTaskWayPointCache.put(TaskWaypointDataMapper.transform(task.getResult()));
                    return null;
                });
    }
}
