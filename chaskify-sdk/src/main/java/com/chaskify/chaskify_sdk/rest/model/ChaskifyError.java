package com.chaskify.chaskify_sdk.rest.model;

/**
 * Created by alberto on 7/12/17.
 */

public class ChaskifyError {
    private String error;

    public ChaskifyError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
