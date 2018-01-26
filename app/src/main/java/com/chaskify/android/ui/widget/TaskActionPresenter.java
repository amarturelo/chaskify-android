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
        mMethodCallHelper.acceptTask(id)
                .continueWith(new LogIfError());
    }

    @Override
    public void arrivedTask(String id) {
        mMethodCallHelper.arrivedTask(id)
                .continueWith(new LogIfError());
    }

    @Override
    public void startTask(String id) {
        mMethodCallHelper.startTask(id)
                .continueWith(new LogIfError());
    }

    @Override
    public void successful(String id) {
        mMethodCallHelper.successfulTask(id)
                .continueWith(new LogIfError());
    }

    @Override
    public void cancelTask(String taskId, String reason) {
        mMethodCallHelper.cancelTask(taskId, reason)
                .continueWith(new LogIfError());
    }

    @Override
    public void declineTask(String id) {
        mMethodCallHelper.declineTask(id)
                .continueWith(new LogIfError());
    }


}
