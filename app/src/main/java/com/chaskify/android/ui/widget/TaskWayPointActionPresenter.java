package com.chaskify.android.ui.widget;

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

    }

    @Override
    public void successfulTaskWayPoint(String id) {

    }
}
