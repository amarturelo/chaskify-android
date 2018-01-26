package com.chaskify.android.ui.widget;

import com.chaskify.android.helper.LogIfError;
import com.chaskify.android.helper.MethodCallHelper;
import com.chaskify.android.shared.BasePresenter;

/**
 * Created by alberto on 14/01/18.
 */

public class TaskWayPointActionPresenter extends BasePresenter<TaskWayPointActionContract.View>
        implements TaskWayPointActionContract.Presenter {


    private MethodCallHelper mMethodCallHelper;

    public TaskWayPointActionPresenter(MethodCallHelper mMethodCallHelper) {
        this.mMethodCallHelper = mMethodCallHelper;
    }

    @Override
    public void startTaskWayPoint(String id) {
        mMethodCallHelper.startTaskWayPoint(id)
                .continueWith(new LogIfError());
    }

    @Override
    public void successfulTaskWayPoint(String id) {
        mMethodCallHelper.successfulTaskWayPoint(id)
                .continueWith(new LogIfError());
    }
}
