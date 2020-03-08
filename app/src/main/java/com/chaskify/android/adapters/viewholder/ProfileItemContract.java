package com.chaskify.android.adapters.viewholder;

import com.chaskify.android.shared.BaseContract;
import com.chaskify.android.ui.model.ProfileItemModel;

/**
 * Created by alberto on 8/01/18.
 */

public interface ProfileItemContract {
    interface View extends BaseContract.View {

        void showProgress();

        void hideProgress();

        void showError(Throwable throwable);

        void renderProfile(ProfileItemModel profileItemModel);
    }

    interface Presenter extends BaseContract.Presenter<ProfileItemContract.View> {
        void profile(String driverId);
        //void changePassword();
    }
}
