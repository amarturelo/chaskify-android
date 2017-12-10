package com.chaskify.android.navigation;

import android.content.Context;
import android.content.Intent;

import com.chaskify.android.ui.activities.MainActivity;

/**
 * Created by Alberto on 31/8/2017.
 */

public class Navigator {

    public static void goToMainActivity(Context context) {
        if (context != null) {
            Intent intentToLaunch = MainActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

}
