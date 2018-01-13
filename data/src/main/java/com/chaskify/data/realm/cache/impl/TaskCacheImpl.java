package com.chaskify.data.realm.cache.impl;

import android.os.Looper;
import android.util.Pair;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Consumer;
import com.chaskify.data.realm.model.RealmProfile;
import com.chaskify.data.realm.model.RealmTask;
import com.chaskify.data.realm.cache.TaskCache;
import com.chaskify.domain.filter.DateFilter;
import com.chaskify.domain.filter.DriverFilter;
import com.chaskify.domain.filter.Filter;
import com.chaskify.domain.filter.TaskIdFilter;

import org.reactivestreams.Publisher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Function;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import timber.log.Timber;

/**
 * Created by alberto on 14/12/17.
 */

public class TaskCacheImpl extends RealmCache implements TaskCache {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", /*Locale.getDefault()*/Locale.getDefault());


    public TaskCacheImpl() {
        Timber.tag(this.getClass().getSimpleName());
    }

    @Override
    public Single<List<RealmTask>> findAll(String driverId) {
        return Single.create(emitter -> {
            final Realm observableRealm = Realm.getDefaultInstance();
            final RealmResults<RealmTask> results = observableRealm.where(RealmTask.class)
                    .equalTo(RealmTask.DRIVER_ID, driverId)
                    .findAll();

            if (results.isLoaded() && results.isValid())
                emitter.onSuccess(results);

            emitter.setDisposable(new Disposable() {
                @Override
                public void dispose() {
                    Disposables.fromRunnable(observableRealm::close);
                }

                @Override
                public boolean isDisposed() {
                    return false;
                }
            });
        });
    }

    @Override
    public Flowable<List<RealmTask>> getTaskAsObservable(List<Filter> filters) {
        return Flowable.defer(() -> Flowable.using(() -> new Pair<>(Realm.getDefaultInstance(), Looper.myLooper()), pair -> {
                    RealmQuery<RealmTask> query = pair.first.where(RealmTask.class);
                    Stream.of(filters)
                            .forEach(filter -> {
                                if (filter instanceof DriverFilter)
                                    query.equalTo(RealmTask.DRIVER_ID, ((DriverFilter) filter).getDriver());
                                if (filter instanceof TaskIdFilter)
                                    query.equalTo(RealmTask.TASK_ID, ((TaskIdFilter) filter).getTaskId());
                            });

                    RealmResults<RealmTask> realmProfiles = query
                            .findAll();

                    return realmProfiles.<RealmTask>asFlowable()
                            .map(pair.first::copyFromRealm)
                            ;
                }
                , pair -> close(pair.first, pair.second))
                .unsubscribeOn(AndroidSchedulers.from(Looper.myLooper())))
                ;
    }

    @Override
    public Single<RealmTask> findById(String driverId, String taskId) {
        return Single.create(emitter -> {
            final Realm observableRealm = Realm.getDefaultInstance();
            final RealmResults<RealmTask> results = observableRealm.where(RealmTask.class)
                    .equalTo(RealmTask.DRIVER_ID, driverId)
                    .equalTo(RealmTask.TASK_ID, taskId)
                    .findAll();

            if (results.isLoaded() && results.isValid() && results.first() != null)
                emitter.onSuccess(results.first());

            emitter.setDisposable(new Disposable() {
                @Override
                public void dispose() {
                    Disposables.fromRunnable(observableRealm::close);
                }

                @Override
                public boolean isDisposed() {
                    return false;
                }
            });
        });
    }

    @Override
    public void put(List<RealmTask> realmTasks) {
        Timber.d("::put array " + realmTasks.toString() + " ::");
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(realmTasks);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void put(RealmTask realmTask) {
        Timber.d("::put " + realmTask.toString() + " ::");
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(realmTask);
        realm.commitTransaction();
        realm.close();
    }
}
