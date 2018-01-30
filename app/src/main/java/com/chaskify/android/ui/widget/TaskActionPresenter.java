package com.chaskify.android.ui.widget;

import com.chaskify.android.helper.MethodCallHelper;
import com.chaskify.android.helper.LogIfError;
import com.chaskify.android.shared.BasePresenter;

/**
 * Created by alberto on 14/01/18.
 */

public class TaskActionPresenter extends BasePresenter<TaskActionContract.View>
        implements TaskActionContract.Presenter {


    private MethodCallHelper mMethodCallHelper;

    public TaskActionPresenter(MethodCallHelper mMethodCallHelper) {
        this.mMethodCallHelper = mMethodCallHelper;
    }

    @Override
    public void acceptTask(String id) {
        view.showProgress();
        mMethodCallHelper.acceptTask(id)
                .continueWith(task ->
                {
                    new LogIfError();
                    view.hideProgress();
                    return null;
                });
    }

    @Override
    public void arrivedTask(String id) {
        view.showProgress();
        mMethodCallHelper.arrivedTask(id)
                .continueWith(task ->
                {
                    new LogIfError();
                    view.hideProgress();
                    return null;
                });
    }

    @Override
    public void startTask(String id) {
        view.showProgress();
        mMethodCallHelper.startTask(id)
                .continueWith(task ->
                {
                    new LogIfError();
                    view.hideProgress();
                    return null;
                });
    }

    @Override
    public void successful(String id) {
        view.showProgress();
        mMethodCallHelper.successfulTask(id)
                .continueWith(task ->
                {
                    new LogIfError();
                    view.hideProgress();
                    return null;
                });
    }

    @Override
    public void cancelTask(String taskId, String reason) {
        view.showProgress();
        mMethodCallHelper.cancelTask(taskId, reason)
                .continueWith(task ->
                {
                    new LogIfError();
                    view.hideProgress();
                    return null;
                });
    }

    @Override
    public void declineTask(String id) {
        view.showProgress();
        mMethodCallHelper.declineTask(id)
                .continueWith(task ->
                {
                    new LogIfError();
                    view.hideProgress();
                    return null;
                });
    }


}
