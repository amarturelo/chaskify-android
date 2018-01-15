package com.chaskify.android.ui.widget;

import com.chaskify.android.shared.BaseContract;
import com.chaskify.android.ui.model.TaskItemActionModel;

/**
 * Created by alberto on 14/01/18.
 */

public interface TaskActionContract {
    interface View extends BaseContract.View {
        void renderActions(TaskItemActionModel taskItemActionModel);
    }

    interface Presenter extends BaseContract.Presenter<TaskActionContract.View> {
        void getTask(String id);

        void accept(String id);

        void arrived(String id);

        void start(String id);

        void successful(String id);

        void decline(String id);
    }
}
