package com.chaskify.android.ui.widget;

import android.support.annotation.NonNull;

import com.chaskify.android.shared.BasePresenter;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallbackSuccess;

/**
 * Created by alberto on 23/01/18.
 */

public class DutyPresenter extends BasePresenter<DutyContract.View>
        implements DutyContract.Presenter, ApiCallbackSuccess {

    private ChaskifySession mChaskifySession;

    public DutyPresenter(ChaskifySession mChaskifySession) {
        this.mChaskifySession = mChaskifySession;
    }

    @Override
    public void bindView(@NonNull DutyContract.View view) {
        super.bindView(view);
        view.renderDutyStatus(mChaskifySession.getState() == ChaskifySession.STATE.ON_DUTY);
    }

    @Override
    public void onDuty() {
        view.onDuty();
        mChaskifySession.onDuty(this);
    }

    @Override
    public void offDuty() {
        view.offDuty();
        mChaskifySession.offDuty(this);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onNetworkError(Exception e) {
        view.renderDutyStatus(mChaskifySession.getState() == ChaskifySession.STATE.ON_DUTY);
    }

    @Override
    public void onChaskifyError(Exception e) {
        view.renderDutyStatus(mChaskifySession.getState() == ChaskifySession.STATE.ON_DUTY);
    }

    @Override
    public void onUnexpectedError(Exception e) {
        view.renderDutyStatus(mChaskifySession.getState() == ChaskifySession.STATE.ON_DUTY);
    }

}
