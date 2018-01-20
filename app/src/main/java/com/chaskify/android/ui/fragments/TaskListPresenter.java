package com.chaskify.android.ui.fragments;

import com.annimon.stream.Stream;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.android.ui.model.TaskItemModel;
import com.chaskify.domain.filter.Filter;
import com.chaskify.domain.interactors.TaskInteractor;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by alberto on 14/12/17.
 */

public class TaskListPresenter extends BasePresenter<TaskListContract.View>
        implements TaskListContract.Presenter {

    private TaskInteractor mTaskInteractor;

    public TaskListPresenter(TaskInteractor taskInteractor) {
        Timber.tag(this.getClass().getSimpleName());
        this.mTaskInteractor = taskInteractor;
    }

    @Override
    public void tasks(List<Filter> filters) {
        addSubscription(mTaskInteractor.tasks(filters)
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .unsubscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tasks -> view.renderTaskListView(Stream.of(tasks)
                        .map(task -> new TaskItemModel()
                                .setTask_id(task.getTaskId())
                                .setTrans_type(task.getTrans_type())
                                .setStatus(task.getStatus())
                                .setDelivery_address(task.getDelivery_address())
                                .setDelivery_date(task.getDelivery_date())
                                .setCustomer_name(task.getCustomer_name()))
                        .toList()), throwable -> view.showError(throwable)));
    }
}
