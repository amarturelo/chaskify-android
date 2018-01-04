package com.chaskify.android.ui.fragments.settings;

import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.os.Bundle;

import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.navigation.Navigator;
import com.chaskify.android.ui.model.ProfileModel;
import com.chaskify.data.realm.cache.impl.ProfileCacheImpl;
import com.chaskify.data.repositories.ProfileRepositoryImpl;
import com.chaskify.data.repositories.datasource.cloud.CloudProfileDataStore;
import com.chaskify.data.repositories.datasource.disk.DiskProfileDataStore;
import com.chaskify.domain.interactors.ProfileInteractor;

import timber.log.Timber;

/**
 * A placeholder fragment containing a simple view.
 */
public class SettingsProfileFragment extends PreferenceFragment implements SettingsProfileContract.View {

    public SettingsProfileFragment() {
    }

    private SettingsProfilePresenter presenter;

    private Preference mPreferenceEmail;
    private Preference mPreferenceContact;
    private Preference mPreferenceVehicle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initComponents();

        presenter = new SettingsProfilePresenter(
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

        addPreferencesFromResource(R.xml.preference_profile_preferences);

        initFragment(savedInstanceState);
    }

    private void initFragment(Bundle savedInstanceState) {
        presenter.profile(Chaskify.getInstance()
                .getDefaultSession()
                .get()
                .getCredentials()
                .getDriverId());
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

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(Throwable throwable) {
        Timber.d(throwable);
    }

    @Override
    public void renderProfile(ProfileModel profileModel) {

    }
}
