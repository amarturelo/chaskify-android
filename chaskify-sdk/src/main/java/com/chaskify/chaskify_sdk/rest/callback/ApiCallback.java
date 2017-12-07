package com.chaskify.chaskify_sdk.rest.callback;

public interface ApiCallback<T> extends ApiFailureCallback {

    /**
     * Called if the API call is successful.
     *
     * @param info the returned information
     */
    void onSuccess(T info);
}