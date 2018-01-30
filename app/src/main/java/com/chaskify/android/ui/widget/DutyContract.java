package com.chaskify.android.ui.widget;

import com.chaskify.android.shared.BaseContract;

/**
 * Created by alberto on 23/01/18.
 */

public interface DutyContract {
    interface View extends BaseContract.View {


        void renderDutyStatus(boolean isDuty);

        void showError(Throwable throwable);
    }

    interface Presenter extends BaseContract.Presenter<DutyContract.View> {
        void onDuty();

        void offDuty();
    }
}
