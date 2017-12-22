package com.chaskify.android.navigation;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;

import com.chaskify.android.ui.activities.MainActivity;
import com.chaskify.android.ui.activities.settings.SettingsProfileActivity;
import com.chaskify.android.ui.activities.settings.SettingsVehicleActivity;
import com.chaskify.android.ui.widget.TaskDialogFragment;

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

    public static void showTaskDetails(FragmentManager fragmentManager, String driver_id, String task_id) {
        if (fragmentManager != null) {
            TaskDialogFragment taskDialogFragment = TaskDialogFragment.getCalling(driver_id, task_id);
            taskDialogFragment.show(fragmentManager, taskDialogFragment.getTag());
        }
    }

}
