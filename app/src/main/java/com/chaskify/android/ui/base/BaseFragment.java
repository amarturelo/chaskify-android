package com.chaskify.android.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by alberto on 9/12/17.
 */

public abstract class BaseFragment extends SwipeBackFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        setSwipeBackEnable(getShippable());
        return view;
    }

    protected boolean getShippable() {
        return false;
    }

    protected abstract int getLayout();

}
