package com.chaskify.chaskify_sdk.rest.callback;

import com.chaskify.chaskify_sdk.rest.model.ChaskifyError;

public interface ApiFailureCallback {

    /**
     * Called if there is a network error.
     *
     * @param e the exception
     */
    void onNetworkError(Exception e);

    /**
     * Called in case of a Matrix error.
     *
     * @param e the Matrix error
     */
    void onChaskifyError(Exception e);

    /**
     * Called for some other type of error.
     *
     * @param e the exception
     */
    void onUnexpectedError(Exception e);
}