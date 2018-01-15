package com.chaskify.android.ui.widget;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.android.ui.model.TaskItemActionModel;
import com.chaskify.domain.filter.Filter;
import com.chaskify.domain.filter.TaskIdFilter;
import com.chaskify.domain.interactors.TaskInteractor;
import com.chaskify.domain.model.Task;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by alberto on 14/01/18.
 */

public class TaskActionPresenter extends BasePresenter<TaskActionContract.View>
        implements TaskActionContract.Presenter {

    private TaskInteractor mTaskInteractor;

    @Override
    public void accept(String id) {

    }

    @Override
    public void arrived(String id) {

    }

    @Override
    public void start(String id) {

    }

    @Override
    public void successful(String id) {

    }

    public TaskActionPresenter(TaskInteractor mTaskInteractor) {
        Timber.tag(this.getClass().getSimpleName());
        this.mTaskInteractor = mTaskInteractor;
    }

    private TaskItemActionModel toModel(Task task) {
        return new TaskItemActionModel()
                .setId(task.getTaskId())
                .setStatus(task.getStatus());
    }

    @Override
    public void getTask(String id) {
        clearSubscriptions();

        List<Filter> filters = new ArrayList<>();
        filters.add(new TaskIdFilter()
                .setTaskId(id));

        addSubscription(mTaskInteractor.tasks(filters)
                .map(tasks -> Stream.of(tasks).findFirst())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        task -> view.renderActions(toModel(task))
                        , Timber::d));
    }
}
