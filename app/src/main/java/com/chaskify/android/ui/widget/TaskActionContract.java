package com.chaskify.android.ui.widget;

import com.chaskify.android.shared.BaseContract;

/**
 * Created by alberto on 14/01/18.
 */

public interface TaskActionContract {
    interface View extends BaseContract.View {
        void renderActions(TaskActionWidget.TaskActionModel mTaskActionModel);
    }

    interface Presenter extends BaseContract.Presenter<TaskActionContract.View> {

        void accept(String id);

        void arrived(String id);

        void start(String id);

        void successful(String id);

        void decline(String id);
    }
}
