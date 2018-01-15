package com.chaskify.android.ui.widget;

import com.chaskify.android.MethodCallHelper;
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
    public void accept(String id) {
        mMethodCallHelper.acceptTask(id)
                .continueWith(new LogIfError());
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

    @Override
    public void decline(String id) {

    }


}
