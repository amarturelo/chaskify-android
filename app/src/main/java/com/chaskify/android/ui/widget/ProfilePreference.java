package com.chaskify.android.ui.widget;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chaskify.android.R;

/**
 * Created by alberto on 12/12/17.
 */

public class ProfilePreference extends Preference {

    public ProfilePreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public ProfilePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ProfilePreference(Context context) {
        super(context);
        init(context, null, 0);
    }


    @Override
    protected View onCreateView(ViewGroup parent) {
        super.onCreateView(parent);
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_preference_widget, parent, false);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        setWidgetLayoutResource(R.layout.profile_preference_widget);
    }

}
