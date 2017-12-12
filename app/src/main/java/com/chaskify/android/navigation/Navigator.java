package com.chaskify.android.navigation;

import android.content.Context;
import android.content.Intent;

import com.chaskify.android.ui.activities.MainActivity;
import com.chaskify.android.ui.activities.settings.SettingsProfileActivity;
import com.chaskify.android.ui.activities.settings.SettingsVehicleActivity;

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

    public static void goToProfileSettings(Context context) {
        if (context != null) {
            Intent intentToLaunch = SettingsProfileActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public static void goToVehicleSettings(Context context) {
        if (context != null) {
            Intent intentToLaunch = SettingsVehicleActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

}
