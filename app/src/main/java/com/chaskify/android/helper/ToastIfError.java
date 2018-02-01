package com.chaskify.android.helper;

import android.content.Context;
import android.view.Gravity;

import com.chaskify.android.R;
import com.fxn.cue.Cue;
import com.fxn.cue.enums.Duration;
import com.fxn.cue.enums.Type;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * Created by alberto on 31/01/18.
 */

public class ToastIfError {
    public static void showError(Context context, Exception e) {
        Cue cue = Cue.init()
                .with(context)
                .setType(Type.DANGER)
                .setDuration(Duration.LONG)
                .setGravity(Gravity.BOTTOM);
        if (e instanceof ConnectException)
            cue.setMessage(context.getResources().getString(R.string.error_failed_to_connect));
        else if (e instanceof SocketTimeoutException)
            cue.setMessage(context.getResources().getString(R.string.error_time_out));
        else
            cue.setMessage(e.getMessage());
        cue.show();
    }
}
