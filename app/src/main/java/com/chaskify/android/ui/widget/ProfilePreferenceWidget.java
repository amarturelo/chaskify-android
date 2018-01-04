package com.chaskify.android.ui.widget;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chaskify.android.R;
import com.chaskify.android.ui.model.ProfileModel;

/**
 * Created by alberto on 12/12/17.
 */

public class ProfilePreferenceWidget extends Preference {

    private ProfileModel mProfileWidgetModel;

    private ImageView mProfileImage;

    private TextView mProfileName;

    private TextView mProfileTeam;

    public ProfilePreferenceWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ProfilePreferenceWidget(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProfilePreferenceWidget(Context context) {
        this(context, null, 0);
    }

    public void setProfileWidgetModel(ProfileModel profileWidgetModel) {
        this.mProfileWidgetModel = profileWidgetModel;
        notifyChanged();
    }


    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        mProfileImage = view.findViewById(R.id.profile_image);
        mProfileName = view.findViewById(R.id.profile_name);
        mProfileTeam = view.findViewById(R.id.profile_team);

        if (mProfileWidgetModel != null) {
            mProfileName.setText(mProfileWidgetModel.getUsername());
            mProfileTeam.setText(mProfileWidgetModel.getTeamName());

            Glide.with(getContext())
                    .load(mProfileWidgetModel.getDriverPicture())
                    .into(mProfileImage);
        }

    }
}
