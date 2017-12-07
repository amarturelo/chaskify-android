package com.chaskify.chaskify_sdk.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alberto on 7/12/17.
 */

public class BaseResponse {
    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("msg")
    @Expose
    private String msg;
    /*@SerializedName("details")
    @Expose
    private DETAILS details;*/

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /*public DETAILS getDetails() {
        return details;
    }

    public void setDetails(DETAILS details) {
        this.details = details;
    }*/
}
