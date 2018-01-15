package com.chaskify.android.ui.fragments;

import com.chaskify.android.shared.BaseContract;
import com.chaskify.android.ui.model.TaskWaypointModel;
import com.chaskify.domain.filter.Filter;

import java.util.List;

/**
 * Created by alberto on 28/12/17.
 */

public class TaskWaypointViewDialogContract {
    interface View extends BaseContract.View {

        void showProgress();

        void hideProgress();

        void showError(Throwable throwable);

        void renderWayPoint(TaskWaypointModel waypointModel);
    }

    interface Presenter extends BaseContract.Presenter<TaskWaypointViewDialogContract.View> {
        void wayPointById(List<Filter> filters);
    }
}
