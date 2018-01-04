package com.chaskify.android.ui.widget;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chaskify.android.R;
import com.chaskify.android.ui.model.ProfileModel;

/**
 * Created by alberto on 12/12/17.
 */

public class ProfilePreferenceWidget extends Preference {

    private ProfileModel mProfileWidgetModel;

    public ProfilePreferenceWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //setWidgetLayoutResource(R.layout.widget_profile_preference);
    }

    public ProfilePreferenceWidget(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProfilePreferenceWidget(Context context) {
        this(context, null, 0);
    }

    public void setProfileWidgetModel(ProfileModel profileWidgetModel) {
        this.mProfileWidgetModel = profileWidgetModel;
    }

    @Override
    public View getView(View convertView, ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return li.inflate(R.layout.widget_profile_preference, parent, false);
    }

}
