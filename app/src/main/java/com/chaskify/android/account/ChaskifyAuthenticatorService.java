package com.chaskify.android.account;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created with IntelliJ IDEA.
 * User: Udini
 * Date: 19/03/13
 * Time: 19:10
 */
public class ChaskifyAuthenticatorService extends Service {
    @Override
    public IBinder onBind(Intent intent) {

        ChaskifyAuthenticator authenticator = new ChaskifyAuthenticator(this);
        return authenticator.getIBinder();
    }
}
