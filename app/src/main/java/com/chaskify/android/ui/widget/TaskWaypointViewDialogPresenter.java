package com.chaskify.android.ui.widget;

import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.android.ui.model.TaskWaypointModel;
import com.chaskify.domain.interactors.TaskWaypointInteractor;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by alberto on 28/12/17.
 */

public class TaskWaypointViewDialogPresenter extends BasePresenter<TaskWaypointViewDialogContract.View>
        implements TaskWaypointViewDialogContract.Presenter {

    private TaskWaypointInteractor taskWaypointInteractor;

    public TaskWaypointViewDialogPresenter(TaskWaypointInteractor taskWaypointInteractor) {
        this.taskWaypointInteractor = taskWaypointInteractor;
    }

    @Override
    public void wayPointById(String driver_id, String task_id) {
        addSubscription(taskWaypointInteractor.wayPointById(driver_id, task_id)
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> view.showProgress())
                .doFinally(() -> view.hideProgress())
                .subscribe(taskWaypoint -> view.renderWayPoint(new TaskWaypointModel()
                                .setId(taskWaypoint.getId())
                                .setTaskId(taskWaypoint.getTaskId())
                                .setContactNumber(taskWaypoint.getContactNumber())
                                .setCustomerName(taskWaypoint.getCustomerName())
                                .setDateCreated(taskWaypoint.getDateCreated())
                                .setDeliveryAddress(taskWaypoint.getDeliveryAddress())
                                .setEmailAddress(taskWaypoint.getEmailAddress())
                                .setStatus(taskWaypoint.getStatus())
                                .setTaskStatus(taskWaypoint.getTaskStatus())
                                .setType(taskWaypoint.getType())
                                .setWaypointDescription(taskWaypoint.getWaypointDescription()))
                        , throwable -> view.showError(throwable)));
    }
}
