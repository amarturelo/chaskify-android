package com.chaskify.android.helper;

import android.content.Context;
import android.view.Gravity;

import com.fxn.cue.Cue;
import com.fxn.cue.enums.Duration;
import com.fxn.cue.enums.Type;

/**
 * Created by alberto on 31/01/18.
 */

public class ToastIfSuccessful {
    public static void show(Context context, String message) {
        Cue cue = Cue.init()
                .with(context)
                .setType(Type.SUCCESS)
                .setDuration(Duration.LONG)
                .setGravity(Gravity.BOTTOM);
        cue.setMessage(message);
        cue.show();
    }
}
