package com.chaskify.android.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.chaskify.android.R;
import com.chaskify.android.ui.model.ProfileModel;

/**
 * Created by alberto on 12/12/17.
 */

public class ProfilePreferenceWidget extends FrameLayout {

    private ProfileModel mProfileWidgetModel;

    public ProfilePreferenceWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public ProfilePreferenceWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ProfilePreferenceWidget(Context context) {
        super(context);
        init(context, null, 0);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        inflate(context, R.layout.profile_preference_widget, this);
    }

    public void setProfileWidgetModel(ProfileModel profileWidgetModel) {
        this.mProfileWidgetModel = profileWidgetModel;

        render();
    }

    private void render() {

    }

}
