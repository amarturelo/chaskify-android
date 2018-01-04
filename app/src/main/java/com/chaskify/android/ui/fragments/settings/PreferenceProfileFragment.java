package com.chaskify.android.ui.fragments.settings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.navigation.Navigator;
import com.chaskify.android.ui.model.ProfileModel;
import com.chaskify.data.realm.cache.impl.ProfileCacheImpl;
import com.chaskify.data.repositories.ProfileRepositoryImpl;
import com.chaskify.data.repositories.datasource.cloud.CloudProfileDataStore;
import com.chaskify.data.repositories.datasource.disk.DiskProfileDataStore;
import com.chaskify.domain.interactors.ProfileInteractor;

/**
 * Created by alberto on 3/01/18.
 */

public class PreferenceProfileFragment extends PreferenceFragment implements PreferenceProfileContract.View {


    private static final String ARG_PROFILE = "arg_profile";
    private ProfileModel mProfileModel;

    private PreferenceProfilePresenter presenter;

    private Preference mPreferenceEmail;
    private Preference mPreferenceContact;
    private Preference mPreferenceVehicle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponents();
        presenter = new PreferenceProfilePresenter(
                new ProfileInteractor(
                        new ProfileRepositoryImpl(
                                new DiskProfileDataStore(
                                        new ProfileCacheImpl()
                                )
                                , new CloudProfileDataStore(
                                Chaskify.getInstance()
                                        .getDefaultSession()
                                        .get().getProfileRestClient()
                                , new ProfileCacheImpl()
                        )
                        )
                )
        );

        presenter.bindView(this);

        addPreferencesFromResource(R.xml.profile_preferences);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(Throwable throwable) {

    }

    @Override
    public void renderProfile(ProfileModel profileModel) {
        mPreferenceEmail.setSummary(mProfileModel.getEmail());
        mPreferenceContact.setSummary(mProfileModel.getPhone());
        mPreferenceVehicle.setSummary(mProfileModel.getTransportTypeId());
    }

    private void initComponents() {
        mPreferenceEmail = findPreference(getString(R.string.key_preference_driver_mail));
        mPreferenceContact = findPreference(getString(R.string.key_preference_driver_phone));
        mPreferenceVehicle = findPreference(getString(R.string.key_preference_driver_vehicle));

        mPreferenceVehicle
                .setOnPreferenceClickListener(preference -> {
                    Navigator.goToVehicleSettings(getActivity());
                    return true;
                });
    }

}
