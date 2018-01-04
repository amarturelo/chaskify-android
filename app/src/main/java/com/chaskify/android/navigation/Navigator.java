package com.chaskify.android.navigation;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;

import com.chaskify.android.ui.activities.MainActivity;
import com.chaskify.android.ui.activities.settings.ProfileActivity;
import com.chaskify.android.ui.activities.settings.VehicleActivity;
import com.chaskify.android.ui.widget.TaskViewDialogFragment;
import com.chaskify.android.ui.widget.TaskWaypointViewDialogFragment;

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
            Intent intentToLaunch = ProfileActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public static void goToVehicleSettings(Context context) {
        if (context != null) {
            Intent intentToLaunch = VehicleActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public static void showTaskDetails(FragmentManager fragmentManager, String driver_id, String task_id) {
        if (fragmentManager != null) {
            TaskViewDialogFragment taskDialogFragment = TaskViewDialogFragment.getCalling(driver_id, task_id);
            taskDialogFragment.show(fragmentManager, taskDialogFragment.getTag());
        }
    }

    public static void showTaskWaypointDetails(FragmentManager fragmentManager, String driver_id, String task_id) {
        if (fragmentManager != null) {
            TaskWaypointViewDialogFragment taskDialogFragment = TaskWaypointViewDialogFragment.getCalling(driver_id, task_id);
            taskDialogFragment.show(fragmentManager, taskDialogFragment.getTag());
        }
    }

}
