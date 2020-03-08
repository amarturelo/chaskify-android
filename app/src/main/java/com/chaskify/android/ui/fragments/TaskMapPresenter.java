package com.chaskify.android.ui.fragments;

import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.android.ui.model.mapper.TaskSnapItemModelDataMapper;
import com.chaskify.domain.filter.Filter;
import com.chaskify.domain.interactors.TaskInteractor;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by alberto on 14/12/17.
 */

public class TaskMapPresenter extends BasePresenter<TaskMapContract.View>
        implements TaskMapContract.Presenter {

    private TaskInteractor mTaskInteractor;

    public TaskMapPresenter(TaskInteractor mTaskInteractor) {
        Timber.tag(this.getClass().getSimpleName());
        this.mTaskInteractor = mTaskInteractor;
    }

    @Override
    public void tasks(List<Filter> filters) {
        clearSubscriptions();

        addSubscription(mTaskInteractor.tasks(filters)
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .unsubscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tasks -> view.renderTaskListView(TaskSnapItemModelDataMapper.transform(tasks))
                        , throwable -> view.showError(throwable)));

    }
}
