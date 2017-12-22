package com.chaskify.android.ui.widget;

import android.support.annotation.NonNull;

import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.android.ui.model.TaskModel;
import com.chaskify.domain.interactors.TaskInteractor;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by alberto on 20/12/17.
 */

public class TaskDialogPresenter extends BasePresenter<TaskDialogContract.View>
        implements TaskDialogContract.Presenter {

    private TaskInteractor taskInteractor;

    public TaskDialogPresenter(TaskInteractor taskInteractor) {
        Timber.tag(this.getClass().getSimpleName());
        this.taskInteractor = taskInteractor;
    }

    @Override
    public void bindView(@NonNull TaskDialogContract.View view) {
        super.bindView(view);
    }

    @Override
    public void taskById(String driver_id, String task_id) {
        Timber.d("::taskById " + "Driver Id: " + driver_id + " Task Id: " + task_id + "::");
        addSubscription(taskInteractor.taskById(driver_id, task_id)
                .flatMap(taskOptional -> taskOptional.isPresent() ? Single.just(taskOptional.get()) : Single.error(new Exception("Elemento no encontrado")))
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> view.showProgress())
                .doFinally(() -> view.hideProgress())
                .subscribe(task -> view.renderTask(new TaskModel()
                        .setTaskId(task.getTask_id())
                        .setCustomerName(task.getCustomer_name())
                        .setDeliveryAddress(task.getDelivery_address())
                        .setDeliveryDate(task.getDelivery_date())
                        .setStatus(task.getStatus())
                        .setTransType(task.getTrans_type())
                ), throwable -> view.showError(throwable)));
    }
}
