package com.chaskify.android.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation_swipeback.*;

/**
 * Created by alberto on 9/12/17.
 */

public abstract class BaseActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        setSwipeBackEnable(getShippable());
    }

    protected abstract int getLayout();

    protected boolean getShippable() {
        return false;
    }
}
