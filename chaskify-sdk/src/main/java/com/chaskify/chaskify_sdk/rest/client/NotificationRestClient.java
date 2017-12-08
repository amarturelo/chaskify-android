package com.chaskify.chaskify_sdk.rest.client;

import com.chaskify.chaskify_sdk.HomeServerConnectionConfig;
import com.chaskify.chaskify_sdk.RestClient;
import com.chaskify.chaskify_sdk.rest.api.NotificationApi;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.exceptions.TokenNotFoundException;
import com.chaskify.chaskify_sdk.rest.model.Notification;

import java.util.List;

/**
 * Created by alberto on 7/12/17.
 */

public class NotificationRestClient extends RestClient<NotificationApi> {
    public NotificationRestClient(HomeServerConnectionConfig hsConfig) {
        super(hsConfig, NotificationApi.class);
    }

    public void getNotifications(ApiCallback<List<Notification>> callback) throws TokenNotFoundException {
        if (mCredentials != null)
            mApi.notifications(mHsConfig.getTimeZone(), mHsConfig.getLang_id(), mCredentials.accessToken);
        else
            throw new TokenNotFoundException();
    }
}
