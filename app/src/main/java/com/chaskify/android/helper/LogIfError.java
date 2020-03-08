package com.chaskify.android.helper;

import bolts.Continuation;
import bolts.Task;
import timber.log.Timber;

/**
 * Bolts-Task continuation for just logging if error occurred.
 */
public class LogIfError implements Continuation {
    @Override
    public Object then(Task task) throws Exception {
        if (task.isFaulted()) {
            Timber.d(task.getError());
        }
        return task;
    }
}