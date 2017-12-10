package com.chaskify.android.ui.fragments.launch;

import com.chaskify.android.shared.BaseContract;

/**
 * Created by alberto on 10/12/17.
 */

public class LoginContract {
    interface View extends BaseContract.View {


    }

    interface Presenter extends BaseContract.Presenter<LoginContract.View> {
    }
}
