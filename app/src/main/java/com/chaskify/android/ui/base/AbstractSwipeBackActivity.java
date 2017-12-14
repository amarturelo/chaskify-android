package com.chaskify.android.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by alberto on 14/12/17.
 */

public abstract class AbstractSwipeBackActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSwipeBackEnable(true);
    }
}
